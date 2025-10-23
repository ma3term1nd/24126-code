package org.firstinspires.ftc.teamcode.main;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class AprilTag {
    private AprilTagProcessor aprilTag;

    public AprilTag() {
        aprilTag = new AprilTagProcessor.Builder()
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)
                .build();
    }

    public AprilTagProcessor getAprilTag() {
        return aprilTag;
    }

    public boolean isAprilTag() {
        List<AprilTagDetection> detections = aprilTag.getDetections();
        return detections != null && !detections.isEmpty();
    }

    public double getRange() {
        List<AprilTagDetection> d = aprilTag.getDetections();
        return (d != null && !d.isEmpty()) ? d.get(0).ftcPose.range : 0;
    }

    public double getAngle() {
        List<AprilTagDetection> d = aprilTag.getDetections();
        return (d != null && !d.isEmpty()) ? d.get(0).ftcPose.bearing : 0;
    }

    public double getHeight() {
        List<AprilTagDetection> d = aprilTag.getDetections();
        return (d != null && !d.isEmpty()) ? d.get(0).ftcPose.elevation : 0;
    }

    public double getID() {
        List<AprilTagDetection> d = aprilTag.getDetections();
        return (d != null && !d.isEmpty()) ? d.get(0).id : -1;
    }

    public void setOffset(float i) {
        aprilTag.setDecimation(i);
    }
}
