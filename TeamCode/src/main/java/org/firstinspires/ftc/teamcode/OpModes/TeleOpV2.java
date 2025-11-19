package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.OpModes.MecanumClass;
@TeleOp(name = "teleopV2")
public class TeleOpV2 extends OpMode {
    CRServo trasnfertester;
    enum DriveState {
        normalDrive,
        roadRunnerDrive;
    }
    MecanumClass drive = new MecanumClass();
    public void init(){

        trasnfertester = hardwareMap.get(CRServo.class, "transfer1");
        trasnfertester.setDirection(DcMotorSimple.Direction.FORWARD);
        trasnfertester.setPower(0);

    }
    public void loop(){ //start of loop
        if(gamepad1.b){
           trasnfertester.setPower(1);
        }
        /*
        DriveState state = DriveState.normalDrive;
        switch(state){
            case normalDrive:

                if(gamepad1.a){
                    state = DriveState.roadRunnerDrive; //if press a button, switches to roadrunner mode
                }


                break;

            case roadRunnerDrive:

                if(gamepad1.b){
                    state = DriveState.normalDrive; //if press b button, switches to normal mode
                }
                break;
        }
        */

    } //end of loop
}//end of class
