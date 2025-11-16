package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
@TeleOp(name="TeleOP2026")

public class TeleOP2026 extends OpMode { //adb connect 192.168.43.1:5555
    MecanumDrive drive = new MecanumDrive();
    //VisionPortal camera = new VisionPortal();
    //ColorSensor pColorSensor = new ColorSensor("purple");
    //ColorSensor gColorSensor = new ColorSensor("green");
    //AprilTag aprilTag = new AprilTag();
    double strafe, forward, rotate;
    boolean lastButtonState = false;
    boolean outakeToggleState = false;
    boolean lastIntakeButtonState = false;
    boolean intakeToggle = false;
    ElapsedTime timer = new ElapsedTime(100);
    ElapsedTime timer1 = new ElapsedTime(100);
    DcMotor belt, shooter;
    Servo transfer;
    double servoUpPosition = 0.06;
    double servoDownPosition = 0.0;

    public void init() {
        drive.init(hardwareMap);
        drive.imu.resetYaw();
        //camera = new VisionPortal.Builder()
                //.addProcessors(aprilTag.getAprilTag(), pColorSensor.getColorSensor(), gColorSensor.getColorSensor())
                //.setCamera(hardwareMap.get(WebcamName.class, "webcam"))
                //.build();

        transfer = hardwareMap.get(Servo.class, "transfer");
        shooter = hardwareMap.get(DcMotor.class, "shooter");

        belt = hardwareMap.get(DcMotor.class, "belt");
        belt.setDirection(DcMotor.Direction.REVERSE);
        belt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    public void  loop() {
        /* APRIL-TAG-SENSOR */
        /*if (aprilTag.isAprilTag()) {
            telemetry.addLine("ID: " + aprilTag.getID());
            telemetry.addLine("Range: " + aprilTag.getRange());
            telemetry.addLine("Height: " + aprilTag.getHeight());
            telemetry.addLine("Angle: " + aprilTag.getAngle());
        } else {
            telemetry.addLine("no april tags detected");
        }*/

        /* COLOR-SENSOR */
        /*if (pColorSensor.getLargestBlob() > 0) {
            telemetry.addLine("pDistance: " pColorSensor.getX());    
        } else {
            telemetry.addLine("no purple artifacts detected");
        }
        
        if (gColorSensor.getLargestBlob() > 0) {
            telemetry.addLine("gDistance: " gColorSensor.getX());    
        } else {
            telemetry.addLine("no green artifacts detected");
        }*/

        /* SLOW-MODE */
        boolean curButtonState = gamepad1.left_bumper;
        if (curButtonState && !lastButtonState) {
            if (drive.maxSpeed == 1.0) {
                drive.maxSpeed = 0.5;
            } else {
                drive.maxSpeed = 1.0;
            }
        }

        lastButtonState = curButtonState;

        /* RESET-DRIVE */
        if (gamepad1.right_bumper) {
            drive.imu.resetYaw();
        }
////////////*-/
        /* DRIVETRAIN */
        forward = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        drive.driveFieldRelative(forward, strafe, rotate);

        /* INTAKE */

        boolean currentIntakeButtonState = gamepad2.a;
        if (currentIntakeButtonState && !lastIntakeButtonState){
            intakeToggle = !intakeToggle;
            if (intakeToggle){
                belt.setPower(-1);
            }
            else {
                belt.setPower(0);
            }
        }
        lastIntakeButtonState = currentIntakeButtonState;
        
        /* OUT-TAKE */

        if (gamepad2.y) {
            timer.reset();
            outakeToggleState = true;
        }

        if (gamepad2.b){
            outakeToggleState = false;
        }
        if (timer.time() <= 6.00 && outakeToggleState) { //total time
            transfer();
        } else { //turn off everything
            shooter.setPower(0);
            transfer.setPosition(servoDownPosition);
        }

        /* TELEMETRY */
        telemetry.addData("name:", "the goonbot");
        telemetry.addData("speed: ", drive.maxSpeed);
        telemetry.addData("imu: ", drive.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));

    }
    /*


     */
    public void transfer() {
        //turns on shooter
        shooter.setPower(-1);
        // type

        //0.5 seconds up position
        if (timer.time() >= 1.25 && timer.time() < 1.75){
            transfer.setPosition(servoUpPosition);}
        //1.5 seconds down position
        else if (timer.time() >= 1.75 && timer.time() < 3.25){
            transfer.setPosition(servoDownPosition);}
        //0.5 seconds up position
        else if (timer.time() >= 3.25 && timer.time() < 3.75){
            transfer.setPosition(servoUpPosition);
        }
        //1.75 seconds down position
        else if (timer.time() >= 3.5 && timer.time() < 5.25){
            transfer.setPosition(servoDownPosition);
        }
        //0.5 seconds up position
        else if (timer.time() > 5.25 && timer.time() <= 5.75) {
            transfer.setPosition(servoUpPosition);
        }



    }

}
