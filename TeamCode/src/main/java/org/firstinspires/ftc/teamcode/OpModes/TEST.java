package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SubsystemsForNow.IntakeForNow;
import org.firstinspires.ftc.teamcode.SubsystemsForNow.ShooterForNow;

@Disabled
@TeleOp(name = "TeleOpV2")
public class TEST extends OpMode {

    //flywheel variables
    boolean flywheelToggle = false;
    boolean lastFlywheelButtonState = false;

    //kicker variables
    boolean kickerToggle = false;
    boolean lastKickerButtonState = false;

    //shooter variables
    double shooterVelocity = 0;
    double shooterMaxVelocity = 0;

    //intake variables
    int intakePower = 1;

    //changes in voltage
    double currentTuningVoltage = 0;

    enum DriveState {
        normalDrive,
        roadRunnerDrive;
    }

    DriveState state = DriveState.normalDrive;
    ShooterForNow shoot;
    IntakeForNow intake;
    ElapsedTime timer = new ElapsedTime(100);

    //ftc dashboard
    FtcDashboard dashboard = FtcDashboard.getInstance();
    Telemetry dashboardTelemetry = dashboard.getTelemetry();

    MecanumClass drive = new MecanumClass();

    public void init(){

        //drive.init(hardwareMap);

        shoot = new ShooterForNow(hardwareMap, shooterVelocity, shooterMaxVelocity);
        shoot.kickerIsFlat();

        intake = new IntakeForNow(hardwareMap, intakePower);

    }//end of init method

    public void loop(){

        //SWITCH STATEMENT
        switch(state) { //
            case normalDrive:

                //if you are on normal drive, pressing A returns to roadrunnerDrive
                if(gamepad1.a){
                    state = DriveState.roadRunnerDrive;
                }

                //intake - Driver 2

                if(gamepad2.y){
                    intake.intakeIn();
                }
                else if(gamepad2.x){
                    intake.intakeOut();
                }
                else {
                    intake.intakeOff();
                }
                //end of intake

                break; //end of normalDrive code

            case roadRunnerDrive:

                //if you are on roadrunner drive, clicking B returns to normal drive
                if(gamepad1.b){
                    state = DriveState.normalDrive;
                }

                break; //end of roadRunnerDrive Code
        }//end of switch statement

        //flywheel

        boolean currentFlywheelButton = gamepad2.a;
        if(currentFlywheelButton && !lastFlywheelButtonState){
            flywheelToggle= !flywheelToggle;
        }
        lastFlywheelButtonState = currentFlywheelButton;

        if(flywheelToggle){
            shoot.shooterOn();
        }
        else {
            shoot.shooterOff();
        }


        //kicker
        if(gamepad2.b){
            timer.reset();
            kickerToggle = !kickerToggle;
        }

        if(kickerToggle) {

            //value has to be greater than last one so kicker can return to flat
                kickerSequence();
        }
        else {

            shoot.kickerIsFlat();

        }




        //dashboard
        dashboardTelemetry.addData("input: reference velocity", shoot.referenceVelocity);
        dashboardTelemetry.addData("output: current velocity", shoot.currentVelocity);

        //telemetry
        telemetry.addData("MaxVelocity", shoot.maximumVelocity);
        dashboardTelemetry.update();


    } //end of loop
    //Methods
    public void kickerSequence(){
        if(timer.time() > 0 && timer.time() < 0){ //change 0s
            shoot.kickerIsUp();
        }

        /*
        else if (timer.time()> 0 && timer.time() < 0){
            shoot.kickerIsFlat();
        }
        else if (timer.time()> 0 && timer.time() < 0){
            shoot.kickerIsUp();
        }
        else if (timer.time()> 0 && timer.time() < 0){
            shoot.kickerIsFlat();
        }
        else if (timer.time()> 0 && timer.time() < 0){
            shoot.kickerIsUp();
        }
        else if (timer.time() > 0){
            shoot.kickerIsUp();
            kickerToggle = false;
        }

         */
    }//end of method


}//end of class
