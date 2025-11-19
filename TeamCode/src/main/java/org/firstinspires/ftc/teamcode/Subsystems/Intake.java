package org.firstinspires.ftc.teamcode.Subsystems;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class Intake {
    //declaring motors & servos
    CRServo rampServo, aboveRampServo;
    DcMotor rampMotor;

    //variables
    final static double intakePower = 1;
    public Intake (HardwareMap hwMap){

        //initializing
        rampMotor = hwMap.get(DcMotor.class, "rMotor");
        rampServo = hwMap.get(CRServo.class, "rServo");
        aboveRampServo = hwMap.get(CRServo.class, "ARServo");

        //settings for rampMotor
        //rampMotor.setDirection(DcMotor.Direction.REVERSE);
        rampMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //settings for rampServo
        //reverse? rampServo.setDirection(CRServo.Direction.REVERSE);

    }

    //modes of intake
    public void intakeIn (){
        rampMotor.setPower(intakePower);
        rampServo.setPower(intakePower);
        aboveRampServo.setPower(intakePower);
    }
    public void intakeOut(){
        rampMotor.setPower(-intakePower);
        rampServo.setPower(-intakePower);
    }

}//end of class
