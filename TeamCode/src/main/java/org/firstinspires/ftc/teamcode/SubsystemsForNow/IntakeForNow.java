package org.firstinspires.ftc.teamcode.SubsystemsForNow;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

public class IntakeForNow {
    DcMotor intakeMotor;

    private double intakeMotorPower;
    public IntakeForNow (HardwareMap hwMap, double intakeMotorServoPower){
        //instance variable
        this.intakeMotorPower = intakeMotorServoPower;

        intakeMotor = hwMap.get(DcMotor.class, "intake");
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //intakeServo.setDirection(CRServo.Direction.REVERSE);

        //intakeMotor.setDirection(DcMotor.Direction.REVERSE);
    }
    public void intakeIn(){

        intakeMotor.setPower(intakeMotorPower);

    }
    public void intakeOut(){

        intakeMotor.setPower(-intakeMotorPower);

    }
    public void intakeOff(){

        intakeMotor.setPower(0);

    }
}
