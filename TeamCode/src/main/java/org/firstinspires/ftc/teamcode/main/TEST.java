package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import android.util.Size;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;

@TeleOp(name="TEST")
public class TEST extends OpMode {
    public VisionPortal visionPortal;
    AprilTag april = new AprilTag();
    ColorSensor colorSensor = new ColorSensor();

    public void init() {
        visionPortal = new VisionPortal.Builder()
                .addProcessors(april.init(), colorSensor.setColorGreen(), colorSensor.setColorPurple())
                .setCameraResolution(new Size(320, 240))
                .setCamera(hardwareMap.get(WebcamName.class, "webcam"))
                .build();
        visionPortal.setProcessorEnabled(april.init(), false);
        visionPortal.setProcessorEnabled(colorSensor.setColorGreen(), false);
        visionPortal.setProcessorEnabled(colorSensor.setColorPurple(), false);
    }

    public void loop() {

        telemetry.setMsTransmissionInterval(100);   // Speed up telemetry updates for debugging.
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE);

        /* APRIL-TAG-SENSOR */
        //april.startAprilTag(visionPortal);
        if (april.isAprilTag()) {
            telemetry.addLine("ID: " + april.getID());
            telemetry.addLine("Range: " + april.getRange());
            telemetry.addLine("Height: " + april.getHeight());
            telemetry.addLine("Angle: " + april.getAngle());
        } else {
            telemetry.addLine("nothing detected");
        }

        /* COLOR-SENSOR */
        visionPortal.setProcessorEnabled(colorSensor.setColorGreen(), true);
        if (colorSensor.getX() > 0) {
            telemetry.addLine("X: " + colorSensor.getX());
        }
        if (colorSensor.getY() > 0) {
            telemetry.addLine("Y: " + colorSensor.getY());
        }

    }
}
