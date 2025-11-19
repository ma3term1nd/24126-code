package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDriveAuto;
import org.firstinspires.ftc.teamcode.RoadRunner.PinpointLocalizer;

public class TeleOpLocalizer {
    PinpointLocalizer localizer;
    GoBildaPinpointDriver pinpoint;

    public TeleOpLocalizer (HardwareMap hwMap, Pose2d startingPos){

        pinpoint = hwMap.get(GoBildaPinpointDriver.class, "pinpoint");
        //localizer = new PinpointLocalizer(hwMap,startingPos);

    }
}
