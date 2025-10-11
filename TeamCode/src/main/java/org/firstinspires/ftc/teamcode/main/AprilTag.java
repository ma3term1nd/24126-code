package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

/* UTILITY CLASS */

public class AprilTag {

    private static boolean USE_WEBCAM = true;  // true for webcam, false for phone camera
    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;

    public void init (HardwareMap hwMap, boolean isWebcam) {
        aprilTag = new AprilTagProcessor //sensor
                .Builder()
                .setDrawTagOutline(true)
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)
                .setLensIntrinsics(578.272, 578.272, 402.145, 221.506)
                .build();

        VisionPortal.Builder builder = new VisionPortal.Builder(); //camera
        builder.addProcessor(aprilTag); //enable the sensor
        visionPortal = builder.build(); //build the visionPortal

        USE_WEBCAM = isWebcam;
        if (USE_WEBCAM) {
            builder.setCamera(hwMap.get(WebcamName.class, "webcam")); //make sure to name it "webcam"
        } else {
            builder.setCamera(BuiltinCameraDirection.BACK); //sets it to phone camera
        }
    }

    public boolean isAprilTag () {
        if (this.aprilTag.getDetections() != null) {
            return true;
        } else {
            return false;
        }
    }

    public double getRange() {
        List<AprilTagDetection> currentDetections = this.aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            return detection.ftcPose.range;
        } {
            return 0;
        }
    }

    public double getAngle() {
        List<AprilTagDetection> currentDetections = this.aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            return detection.ftcPose.bearing;
        } {
            return 0;
        }
    }

    public double getHeight() {
        List<AprilTagDetection> currentDetections = this.aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            return detection.ftcPose.elevation;
        } {
            return 0;
        }
    }

    public double getID() {
        List<AprilTagDetection> currentDetections = this.aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            return detection.id;
        } {
            return 0;
        }
    }

    public void setOffset(float i) {
        aprilTag.setDecimation(i);
    }

}
