package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/* UTILITY CLASS */

public class Calibrate {
    public static ElapsedTime timer = new ElapsedTime(100);

    public static void motorSetPos(DcMotor motor, int pos, double speed) {
        motor.setTargetPosition(pos);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(speed);
    }

    public static void servoTest(Servo servo) {
        if (timer.milliseconds() >= 0 && timer.milliseconds() < 1000) {
            servo.setPosition(0);
        } else if (timer.milliseconds() >= 1000 && timer.milliseconds() < 2000) {
            servo.setPosition(1);
        }
    }

    public static void motorTest(DcMotor motor) {
        if (timer.seconds() >= 0 && timer.seconds() < 1) {
            motor.setPower(1);
        } else if (timer.seconds() >= 1 && timer.seconds() < 2) {
            motor.setPower(0);
        }
    }

}
