package org.firstinspires.ftc.teamcode.OpModes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDriveAuto;

//rr imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

@Config
@Autonomous(name="AutoRedGoalSide")
public class Auto2025 extends LinearOpMode {


    public void runOpMode() throws InterruptedException {
        //initializing motors and setting (x,y, angle of robot)
        Pose2d beginPosition = new Pose2d(new Vector2d(60,-12),Math.toRadians(180));

        //creates rr obj
        MecanumDriveAuto drive = new MecanumDriveAuto(hardwareMap,beginPosition);
        waitForStart();
        Action testerPath = drive.actionBuilder(beginPosition)
                .splineToLinearHeading(new Pose2d(-16,7,Math.toRadians(135)),Math.toRadians(135))
                .build();

        Actions.runBlocking(new SequentialAction(testerPath));


    }
}
