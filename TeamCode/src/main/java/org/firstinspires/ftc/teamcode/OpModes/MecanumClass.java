package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


/* UTILITY CLASS */


public class MecanumClass {
    /* SET UP ALL VARIABLES IN THE DRIVER STATION */
    public DcMotor leftFront, leftBack, rightBack, rightFront;
    public IMU imu;
    public double maxSpeed = 1.00;


    public void init (HardwareMap hwMap) {
        leftFront = hwMap.get(DcMotor.class, "leftFront");
        leftBack = hwMap.get(DcMotor.class, "leftBack");
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        rightBack = hwMap.get(DcMotor.class, "rightBack");


        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);


        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        imu = hwMap.get(IMU.class, "imu");


        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.RIGHT);


        imu.initialize(new IMU.Parameters(RevOrientation));
    }


    public void drive(double forward, double strafe, double rotate) {
        //ly = forward
        //lx = strafe
        //rx = rotate
        double frontLeftPower = forward + strafe + rotate;
        double backLeftPower = forward - strafe + rotate;
        double frontRightPower = forward - strafe - rotate;
        double backRightPower = forward + strafe - rotate;


        double maxPower = 1.00;


        maxPower = Math.max(maxPower, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));
        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));


        leftFront.setPower(maxSpeed * (frontLeftPower / maxPower));
        leftBack.setPower(maxSpeed * (backLeftPower / maxPower));
        rightFront.setPower(maxSpeed * (frontRightPower / maxPower));
        rightBack.setPower(maxSpeed * (backRightPower / maxPower));
    }
    public void driveFieldRelative(double inputForward, double inputStrafe, double inputRotate){
        double newForward, newStrafe, newRotate;
        double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);


        //changing input to field centric
        newForward = inputForward * Math.cos(heading) - inputStrafe * Math.sin(heading);
        newStrafe = inputStrafe * Math.cos(heading) + inputForward * Math.sin(heading);


        this.drive(newForward, newStrafe, inputRotate);
    }


}

