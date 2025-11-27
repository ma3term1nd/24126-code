package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDriveAuto;
import org.firstinspires.ftc.teamcode.SubsystemsForNow.IntakeForNow;
import org.firstinspires.ftc.teamcode.SubsystemsForNow.ShooterForNow;


@TeleOp(name = "TeleOpV2")
public class TeleOpV2 extends OpMode {

    //flywheel variables
    boolean flywheelToggle = false;
    boolean lastFlywheelButtonState = false;
    boolean lastOutakeButtonState = false;

    //kicker variables
    boolean kickerToggle = false;
    boolean lastKickerButtonState = false;

    //shooter variables
    double shooterVelocity = 100;
    double shooterMaxVelocity = 300;

    //intake variables
    int intakePower = 1;

    //changes in voltage
    double currentTuningVoltage = 0;


    /*enum DriveState {
        normalDrive,
        roadRunnerDrive;
    }
     */

    //DriveState state = DriveState.normalDrive;
    ShooterForNow shoot;
    IntakeForNow intake;
    ElapsedTime timer = new ElapsedTime(100);

    //ftc dashboard
    FtcDashboard dashboard = FtcDashboard.getInstance();
    Telemetry dashboardTelemetry = dashboard.getTelemetry();

    MecanumDriveAuto drive;
    public void init(){

    intake = new IntakeForNow(hardwareMap, 1);
    shoot = new ShooterForNow(hardwareMap, shooterVelocity, shooterMaxVelocity);
    //shoot.kickerIsFlat();
    drive = new MecanumDriveAuto(hardwareMap, new Pose2d(1,2,5));

    }//end of init method

    public void loop(){
        drive.localizer.update();

        Pose2d posePosition = drive.localizer.getPose();
        double x = posePosition.position.x;
        double y = posePosition.position.y;
        //testingForNow
        //shoot.findMaximumVelocity();

        //shoot.shooterOn();
        //shoot.shooterOn();
        /*
        boolean currentFlywheelButton = gamepad2.a;
        if(currentFlywheelButton && !lastFlywheelButtonState){
            flywheelToggle= !flywheelToggle;
            if(flywheelToggle){
                shoot.shooterOn();
            }
            else {
                shoot.shooterOff();
            }
        }
        lastFlywheelButtonState = currentFlywheelButton;



        boolean outakeButton = gamepad2.b;
        if(outakeButton && !lastOutakeButtonState){
            timer.reset();
            kickerToggle = !kickerToggle;
        }
lastOutakeButtonState = outakeButton;
        if(kickerToggle) {

            //value has to be greater than last one so kicker can return to flat
            kickerSequence();
        }
        else {

            shoot.kickerIsFlat();

        }

        */

        dashboardTelemetry.addData("input: reference velocity", shoot.referenceVelocity);
       dashboardTelemetry.addData("output: current velocity", shoot.currentVelocity);
        telemetry.addData("button:", kickerToggle);
        telemetry.addData("x", x);
        telemetry.addData("y",y);
        //telemetry

        telemetry.addData("MaxVelocity", shoot.maximumVelocity);
        dashboardTelemetry.update();


    } //end of loop
    //Methods
    public void kickerSequence(){
        if(timer.time() > 0 && timer.time() < 0.2){ //change 0s
            shoot.kickerIsUp();
        }

        else if (timer.time()> 0.2 && timer.time() < 0.5){
            shoot.kickerIsFlat();
        }
        /*
        else if (timer.time()> 0 && timer.time() < 0){
            shoot.kickerIsUp();
        }
        else if (timer.time()> 0 && timer.time() < 0){
            shoot.kickerIsFlat();
        }
        else if (timer.time()> 0 && timer.time() < 0){
            shoot.kickerIsUp();
        }
        */
        else if (timer.time() > 0.5){
            shoot.kickerIsUp();
            kickerToggle = false;
        }

    }//end of method


}//end of class
/*
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

            if(timer.time() < 0) { //value has to be greater than last one so kicker can return to flat
                kickerSequence();
            }
        }
        else {

            shoot.kickerIsFlat();

        }

 */


