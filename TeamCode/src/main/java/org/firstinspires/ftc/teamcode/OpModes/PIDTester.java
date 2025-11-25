package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SubsystemsForNow.PIDControl;
import org.firstinspires.ftc.teamcode.SubsystemsForNow.ShooterForNow;

@TeleOp (name = "testerTeleop")
public class PIDTester extends OpMode {
    DcMotorEx motor;
    int x = 1;
    FtcDashboard dashboard = FtcDashboard.getInstance();
    Telemetry dashboardTelemetry = dashboard.getTelemetry();
    ShooterForNow shoot;

    public void init() {

    shoot = new ShooterForNow(hardwareMap,1000,3100);

    }
    public void loop (){
        shoot.shooterOn();
        dashboardTelemetry.addData("input: reference velocity", shoot.referenceVelocity);

        dashboardTelemetry.addData("output: current velocity", shoot.currentVelocity);
        telemetry.addData("currentVelocity", shoot.currentVelocity);
        telemetry.addData("update","new");
        dashboardTelemetry.update();
    }



}
