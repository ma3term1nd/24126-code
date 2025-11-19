package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.OpModes.MecanumClass;
@TeleOp(name = "teleopV2")
public class TeleOpV2 extends OpMode {
    CRServo transer1;
    DcMotor transfer, shooter;
    enum DriveState {
        normalDrive,
        roadRunnerDrive;
    }
    MecanumClass drive = new MecanumClass();
    public void init(){

        drive.init(hardwareMap);

        shooter = hardwareMap.get(DcMotor.class, "shooter");


    }
    public void loop(){ //start of loop

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
    } //end of loop
}//end of class
