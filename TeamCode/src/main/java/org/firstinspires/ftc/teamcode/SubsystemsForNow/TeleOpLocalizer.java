package org.firstinspires.ftc.teamcode.SubsystemsForNow;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RoadRunner.PinpointLocalizer;

public class TeleOpLocalizer {
    PinpointLocalizer localizer;
    GoBildaPinpointDriver pinpoint;

    public TeleOpLocalizer (HardwareMap hwMap, Pose2d startingPos){

        pinpoint = hwMap.get(GoBildaPinpointDriver.class, "pinpoint");
        //localizer = new PinpointLocalizer(hwMap,startingPos);

    }
    public double calculateClosestZone(){

    }
}
