package org.firstinspires.ftc.teamcode.main;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous(name = "Auto2026")
public class Auto2026 extends LinearOpMode {
    MecanumDrive drive = new MecanumDrive();
    @Override
    //init
    public void runOpMode() throws InterruptedException {
    drive.init(hardwareMap);
    }

    @Override
    public void waitForStart() {

    }

    //Drive Methods

    public void forwardAndBackward (double power) {
        drive.frontRightMotor.setPower(power);
        drive.frontLeftMotor.setPower(power);
        drive.backRightMotor.setPower(power);
        drive.backLeftMotor.setPower(power);
    }
    public void strafeLeftAndRight (double power){
        drive.frontLeftMotor.setPower(power);
        drive.backLeftMotor.setPower(-power);
        drive.frontRightMotor.setPower(-power);
        drive.frontLeftMotor.setPower(power);
    }
    public void turnLeftAndRight (double power){
        drive.frontLeftMotor.setPower(power);
        drive.backLeftMotor.setPower(power);
        drive.frontRightMotor.setPower(-power);
        drive.backRightMotor.setPower(-power);
    }
}
