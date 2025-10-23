package org.firstinspires.ftc.teamcode.main;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

/* UTILITY CLASS */

public class AprilTag {
    private AprilTagProcessor aprilTag;

    public AprilTag(AprilTagProcessor aprilTag) {
        this.aprilTag = aprilTag;
        aprilTag = new AprilTagProcessor // sensor
                .Builder()
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)
                .build();
    }

    /* METHODS */

    public boolean isAprilTag() {
        List<AprilTagDetection> detections = aprilTag.getDetections();
        return detections != null && !detections.isEmpty();
    }

    public AprilTagProcessor getAprilTag() {
        return aprilTag;
    }

    public double getRange() {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        if (currentDetections != null && !currentDetections.isEmpty()) {
            return currentDetections.get(0).ftcPose.range;
        }
        return 0;
    }

    public double getAngle() {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        if (currentDetections != null && !currentDetections.isEmpty()) {
            return currentDetections.get(0).ftcPose.bearing;
        }
        return 0;
    }

    public double getHeight() {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        if (currentDetections != null && !currentDetections.isEmpty()) {
            return currentDetections.get(0).ftcPose.elevation;
        }
        return 0;
    }

    public double getID() {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        if (currentDetections != null && !currentDetections.isEmpty()) {
            return currentDetections.get(0).id;
        }
        return 0;
    }

    public void setOffset(float i) {aprilTag.setDecimation(i);}
}
