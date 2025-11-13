package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="TeleOP2026")

public class TeleOP2026 extends OpMode { //adb connect 192.168.43.1:5555
    MecanumDrive drive = new MecanumDrive();
    //VisionPortal camera = new VisionPortal();
    //ColorSensor pColorSensor = new ColorSensor("purple");
    //ColorSensor gColorSensor = new ColorSensor("green");
    //AprilTag aprilTag = new AprilTag();
    double strafe, forward, rotate;
    boolean lastButtonState = false;
    ElapsedTime timer = new ElapsedTime(100);
    DcMotor belt, shooter;
    Servo transfer;

    public void init() {
        drive.init(hardwareMap);
        drive.imu.resetYaw();
        //camera = new VisionPortal.Builder()
                //.addProcessors(aprilTag.getAprilTag(), pColorSensor.getColorSensor(), gColorSensor.getColorSensor())
                //.setCamera(hardwareMap.get(WebcamName.class, "webcam"))
                //.build();
        //belt = hardwareMap.get(DcMotor.class, "belt");
        transfer = hardwareMap.get(Servo.class, "transfer");
        shooter = hardwareMap.get(DcMotor.class, "shooter");
    }

    public void loop() {
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

        /* DRIVETRAIN */
        forward = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = -gamepad1.right_stick_x;

        drive.driveFieldRelative(forward, strafe, rotate);
        
        /* INTAKE */
        /*if (gamepad2.a) {
            belt.setPower(-1);
        }
        */
        
        /* OUT-TAKE */
        double firstTime = 0.5; //shoots the ball and waits
        double secondTime = 1.0; //moves the next ball into place
        if (gamepad2.y) {
            timer.reset();
        }
        
        if (timer.time() <= secondTime*3) { //total time
            shooter.setPower(-1);
            for (int i=0; i<3; i++) { //loops thrice
                int x = secondTime*i;
                if (timer.time() >= 0 && timer.time() < firstTime+x) { //opens transfer to shoot
                    transfer.setPosition(0.03);
                } else if (timer.time() >= firstTime && timer.time() < secondTime+x) {//closes transfer to queue a ball
                    transfer.setPosition(0.0);
                }
            }
        } else { //turn off everything
            shooter.setPower(0);
            transfer.setPosition(0);
        }
        
        /* TELEMETRY */
        telemetry.addData("speed: ", drive.maxSpeed);
        telemetry.addData("time: ", timer.time());
    }
}
