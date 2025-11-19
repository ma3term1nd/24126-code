package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
public class Transfer {
    CRServo leftTransferServo, rightTransferServo, middleTransferServo;
    double transferPower = 1;

    public Transfer(HardwareMap hwMap) {
        middleTransferServo = hwMap.get(CRServo.class, "firstTServo");
        leftTransferServo = hwMap.get(CRServo.class, "leftTServo");
        rightTransferServo = hwMap.get(CRServo.class,"rightTServo");

        //rotation of CRServo
        //firstTransferServo.setDirection(CRServo.Direction.REVERSE);
        //secondTransferServo.setDirection(CRServo.Direction.REVERSE);
    }

    public void RampToShooter() {

        middleTransferServo.setPower(transferPower);
        leftTransferServo.setPower(transferPower);
        rightTransferServo.setPower(transferPower);

    } //end of method

    public void ShooterToRamp() {

        middleTransferServo.setPower(-transferPower);
        leftTransferServo.setPower(-transferPower);
        rightTransferServo.setPower(-transferPower);

    } //end of method




} //end of class
