package org.firstinspires.ftc.teamcode.main;

import android.graphics.Color;

import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.Circle;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.firstinspires.ftc.vision.opencv.ColorRange;
import org.firstinspires.ftc.vision.opencv.ImageRegion;

import java.util.List;

public class ColorSensor {

    public ColorBlobLocatorProcessor colorLocator;
    public VisionPortal visionPortal;
    List<ColorBlobLocatorProcessor.Blob> blobs = colorLocator.getBlobs();

    //circleFit.getX() circleFit.getY() circleFit.getRadius() getCircularity()
    /* METHODS */

    public ColorBlobLocatorProcessor setColorGreen() {
        colorLocator = new ColorBlobLocatorProcessor.Builder()
                .setTargetColorRange(ColorRange.ARTIFACT_GREEN)   // default color
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
                .setRoi(ImageRegion.entireFrame())
                .setCircleFitColor(Color.rgb(255, 255, 0)) // Draw a circle
                .setBlurSize(5)          // Smooth the transitions between different colors in image
                .setDilateSize(15)       // Expand blobs to fill any divots on the edges
                .setErodeSize(15)        // Shrink blobs back to original size
                .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)
                .build();
        return colorLocator;
    }

    public ColorBlobLocatorProcessor setColorPurple() {
        colorLocator = new ColorBlobLocatorProcessor.Builder()
                .setTargetColorRange(ColorRange.ARTIFACT_PURPLE)   // default color
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
                .setRoi(ImageRegion.entireFrame())
                .setCircleFitColor(Color.rgb(255, 255, 0)) // Draw a circle
                .setBlurSize(5)          // Smooth the transitions between different colors in image
                .setDilateSize(15)       // Expand blobs to fill any divots on the edges
                .setErodeSize(15)        // Shrink blobs back to original size
                .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)
                .build();
        return colorLocator;
    }

    public double getX() {
        for (ColorBlobLocatorProcessor.Blob b : blobs) {
            Circle circleFit = b.getCircle();
            return circleFit.getX();
        } {return 0.0;}
    }

    public double getY() {
        for (ColorBlobLocatorProcessor.Blob b : blobs) {
            Circle circleFit = b.getCircle();
            return circleFit.getY();
        } {return 0.0;}
    }

    public double getRadius() {
        for (ColorBlobLocatorProcessor.Blob b : blobs) {
            Circle circleFit = b.getCircle();
            return circleFit.getRadius();
        } {return 0.0;}
    }
}
