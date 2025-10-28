package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="TeleOP2026")

public class TeleOP2026 extends OpMode { //adb connect 192.168.43.1:5555
    MecanumDrive drive = new MecanumDrive();
    //ColorSensor pColorSensor = new ColorSensor("purple");
    //ColorSensor gColorSensor = new ColorSensor("green");
    //AprilTag aprilTag = new AprilTag();
    double strafe, forward, rotate;
    boolean lastButtonState = false;
    ElapsedTime timer = new ElapsedTime(100);
    DcMotor belt1, belt2, shooter;

    public void init() {
        drive.init(hardwareMap);
        drive.imu.resetYaw();
        //camera = VisionPortal.easyCreateWithDefaults(
                //hardwareMap.get(WebcamName.class, "webcam"));
        //belt1 = hardwareMap.get(DcMotor.class, "belt1");
        //belt2 = hardwareMap.get(DcMotor.class, "belt2");
        //shooter = hardwareMap.get(DcMotor.class, "shooter");
    }

    public void loop() {
        /* APRIL-TAG-SENSOR */
        /*if (aprilTag.isAprilTag()) {
            telemetry.addLine("ID: " + aprilTag.getID());
            telemetry.addLine("Range: " + aprilTag.getRange());
            telemetry.addLine("Height: " + aprilTag.getHeight());
            telemetry.addLine("Angle: " + aprilTag.getAngle());
        } else {
            telemetry.addLine("nothing detected");
        }*/

        /* COLOR-SENSOR */
        /*if () {
        
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
        
        /* DRIVER 2 CONTROLS */
        /*if (gamepad2.x) { //for both belts
            belt1.setPower(-1);
            belt2.setPower(-1);
        }

        if (gamepad2.a) {
            belt1.setPower(-1);
        }

        if (gamepad2.a) {
            belt2.setPower(-1);
        }*/

        /* OUT-TAKE */
        /*double firstTime = 1.0; //shoots the ball and waits
        double secondTime = 2.0; //moves the next ball into place
        if (gamepad2.y) {
            timer.reset();
        }

        if (timer.time() >= 0 && timer.time() < firstTime) {
            shooter.setPower(-1);
        } else if (timer.time() >= firstTime && timer.time() < secondTime) {
            shooter.setPower(0);
            //move belt1
        }*/

        /* TELEMETRY */
        telemetry.addData("speed: ", drive.maxSpeed);
    }
}
