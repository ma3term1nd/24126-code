package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class Shooter {
    //declaring motors & servos
    DcMotor shooterMotor;
    Servo shooterHood, shooterBlocker;
    enum shooterZones {
        //find out what zones
        zone1,
        zone2,
        zone3,
        zone4,
        noZone;
    }

    //zone1 variables

    double zoneOneHoodAngle = 0;
    double zoneOneShooterPower = 0;
    //zone2 variables
    double zoneTwoHoodAngle = 0;
    double zoneTwoShooterPower = 0;

    //zone3 variables

    double zoneThreeHoodAngle = 0;
    double zoneThreeShooterPower = 0;

    //zone4 variables

    double zoneFourHoodAngle = 0;
    double zoneFourShooterPower = 0;
    //noZone variables
    double noZoneHoodAngle = 0;
    double noZoneShooterPower = 0;

    //shooterBlocker variables

    double shooterBlockerClose = 0;

    double shooterBlockerOpen = 0;
    ElapsedTime timer = new ElapsedTime();

    public Shooter (HardwareMap hwMap){
        //initializing DcMotors

        shooterMotor = hwMap.get(DcMotor.class, "shooter");

        //initializing Servos
        shooterHood = hwMap.get(Servo.class, "hoodServo");
        shooterBlocker = hwMap.get(Servo.class, "shooterBlockerServo");

    } //end of constructor

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

} //end of class