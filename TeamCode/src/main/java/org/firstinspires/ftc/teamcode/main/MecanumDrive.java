package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/* UTILITY CLASS */

public class MecanumDrive {
    /* SET UP ALL VARIABLES IN THE DRIVER STATION */
    public DcMotor frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor;
    public IMU imu;
    public double maxSpeed = 1.00;

    public void init (HardwareMap hwMap) {
        frontLeftMotor = hwMap.get(DcMotor.class, "FLMOTOR");
        backLeftMotor = hwMap.get(DcMotor.class, "BLMOTOR");
        frontRightMotor = hwMap.get(DcMotor.class, "FRMOTOR");
        backRightMotor = hwMap.get(DcMotor.class, "BRMOTOR");

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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

        frontLeftMotor.setPower(maxSpeed * (frontLeftPower / maxPower));
        backLeftMotor.setPower(maxSpeed * (backLeftPower / maxPower));
        frontRightMotor.setPower(maxSpeed * (frontRightPower / maxPower));
        backRightMotor.setPower(maxSpeed * (backRightPower / maxPower));
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
