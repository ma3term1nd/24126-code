package org.firstinspires.ftc.teamcode.main;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "AutoV1")
public class Auto2026 extends LinearOpMode {
    MecanumDrive drive = new MecanumDrive();
    DcMotor shooter, belt;
    TeleOP2026 transferobj = new TeleOP2026();
    ElapsedTime timer = new ElapsedTime(100);
    Servo transfer;

    @Override
    //init
    public void runOpMode() throws InterruptedException {
    drive.init(hardwareMap);
    transfer = hardwareMap.get(Servo.class, "transfer");
    shooter = hardwareMap.get(DcMotor.class, "shooter");

    belt = hardwareMap.get(DcMotor.class, "belt");
    belt.setDirection(DcMotor.Direction.REVERSE);
    belt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
