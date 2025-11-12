package org.firstinspires.ftc.teamcode.main;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

/* UTILITY CLASS */

public class AprilTag {
    private AprilTagProcessor aprilTag;

    public AprilTag() {
        aprilTag = new AprilTagProcessor.Builder()
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)
                .build();
    }

    /* METHODS */

    /* returns the AprilTag */
    public AprilTagProcessor getAprilTag() {
        return aprilTag;
    }

    /* returns true if a tag is detected */
    public boolean isAprilTag() {
        List<AprilTagDetection> detections = aprilTag.getDetections();
        return detections != null && !detections.isEmpty();
    }

    /* returns the distance from the tag */
    public double getRange() {
        List<AprilTagDetection> d = aprilTag.getDetections();
        return (d != null && !d.isEmpty()) ? d.get(0).ftcPose.range : 0;
    }

    /* returns the angle from the tag */
    public double getAngle() {
        List<AprilTagDetection> d = aprilTag.getDetections();
        return (d != null && !d.isEmpty()) ? d.get(0).ftcPose.bearing : 0;
    }

    /* returns the height(y) from the tag */
    public double getHeight() {
        List<AprilTagDetection> d = aprilTag.getDetections();
        return (d != null && !d.isEmpty()) ? d.get(0).ftcPose.elevation : 0;
    }

    /* returns the id of the tag found */
    public double getID() {
        List<AprilTagDetection> d = aprilTag.getDetections();
        return (d != null && !d.isEmpty()) ? d.get(0).id : -1;
    }

    /* sets an offset for the AprilTag sensor */
    public void setOffset(float i) {
        aprilTag.setDecimation(i);
    }
    
}
