package org.firstinspires.ftc.teamcode.SubsystemsForNow;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@Config
public class ShooterForNow {
    //PIDF Object and Variables
    public static double Kp = 0; //0.000555
    public static double Ki = 0;
    public static double Kd = 0;
    public static double Kf = 0; //0.000325
    public double currentVelocity;
    public double afterPidControl;
    PIDControl shooterPid = new PIDControl(Kp,Ki,Kd,Kf);

    //declaring motors & servos
    DcMotorEx shooter;
    Servo kicker;

    //kicker Positions
    double upPosition = 0.075;
    double downPosition = -0.05;

    //instance variables: shooter
    public double referenceVelocity;
    public double maximumVelocity;

    public ShooterForNow(HardwareMap hwMap, double referenceVelocity, double maxVelocity){
        //shooter instance variables
        this.referenceVelocity = referenceVelocity;
        this.maximumVelocity = maxVelocity;

        //initializing DcMotors
       shooter = hwMap.get(DcMotorEx.class, "shooter");
       shooter.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        //initializing Servo
        kicker = hwMap.get(Servo.class, "kicker");

    } //end of constructor

    //kicker methods
    public void kickerIsUp(){
        kicker.setPosition(upPosition);
    }
    public void kickerIsFlat(){
        kicker.setPosition(downPosition);
    }

    //shooter methods
    public void shooterOn(){ //constructor initializes hardware and sets power variable, calling shooterOn turns motor on with PID output

        shooterPid.changePIDCoefficients(Kp, Ki, Kd, Kf); //delete after

        //set motor on, but using PID control
        currentVelocity = shooter.getVelocity();
        shooter.setPower(convertToPIDFOutput(currentVelocity));

    }

    public double convertToPIDFOutput(double currentVelocity){
        //shoo
        afterPidControl = shooterPid.pidControl(referenceVelocity,currentVelocity);
        return afterPidControl;

    }

    public void shooterOff(){
        shooter.setPower(0);
    }
    public void findMaximumVelocity(){

        double currentVelocity = shooter.getVelocity();
        shooter.setPower(1);
        if(currentVelocity > maximumVelocity){
            maximumVelocity = currentVelocity;
        }
    }
} //end of class






    /*
    public void shoots (shooterZones zone){
        if(zone == shooterZones.zone1){

            shooterMotor.setPower(zoneOneShooterPower);
            shooterHood.setPosition(zoneOneHoodAngle);

        }
        else if(zone == shooterZones.zone2){

            shooterMotor.setPower(zoneTwoShooterPower);
            shooterHood.setPosition(zoneTwoHoodAngle);

        }
        else if(zone == shooterZones.zone3){

            shooterMotor.setPower(zoneThreeShooterPower);
            shooterHood.setPosition(zoneThreeHoodAngle);

        }
        else if(zone == shooterZones.zone4){

            shooterMotor.setPower(zoneFourShooterPower);
            shooterHood.setPosition(zoneFourHoodAngle);

        }
        else if(zone == shooterZones.noZone){
            shooterMotor.setPower(noZoneShooterPower);
            shooterHood.setPosition(noZoneHoodAngle);
        }


} //end of method

    public void setShooterBlockerClose (){

        shooterBlocker.setPosition(shooterBlockerClose);

    }
    public void setShooterBlockerOpen (){

        shooterBlocker.setPosition(shooterBlockerOpen);

    }
*/