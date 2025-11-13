package org.firstinspires.ftc.teamcode.main;

import android.graphics.Color;
import org.firstinspires.ftc.vision.opencv.Circle;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.firstinspires.ftc.vision.opencv.ColorRange;
import org.firstinspires.ftc.vision.opencv.ImageRegion;
import java.util.List;

/* UTILITY CLASS */

public class  ColorSensor {

    private final ColorBlobLocatorProcessor colorLocator;

    public ColorSensor(String color) {
        ColorRange range = color.equalsIgnoreCase("purple") //"purple" or "green"
                ? ColorRange.ARTIFACT_PURPLE
                : ColorRange.ARTIFACT_GREEN;
        colorLocator = new ColorBlobLocatorProcessor.Builder()
                .setTargetColorRange(range)
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
                .setRoi(ImageRegion.entireFrame())
                .setDrawContours(true)
                .setBoxFitColor(0)
                .setCircleFitColor(Color.rgb(255, 255, 0))
                .setBlurSize(5)
                .setDilateSize(15)
                .setErodeSize(15)
                .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)
                .build();
    }

    /* METHODS */

    /* gets blob circle radius safely */
    private double getBlobRadius(ColorBlobLocatorProcessor.Blob blob) {
        Circle c = blob.getCircle();
        return (c != null) ? c.getRadius() : 0.0;
    }

    /* gets blob circle X position */
    private double getBlobX(ColorBlobLocatorProcessor.Blob blob) {
        Circle c = blob.getCircle();
        return (c != null) ? c.getX() : 0.0;
    }

    /* gets blob circle Y position */
    private double getBlobY(ColorBlobLocatorProcessor.Blob blob) {
        Circle c = blob.getCircle();
        return (c != null) ? c.getY() : 0.0;
    }
    
    /* returns the color locator */
    public ColorBlobLocatorProcessor getColorSensor() {
        return colorLocator;
    }

    /* finds the largest blob by circle radius */
    private ColorBlobLocatorProcessor.Blob getLargestBlob() {
        List<ColorBlobLocatorProcessor.Blob> blobs = colorLocator.getBlobs();
        if (blobs == null || blobs.isEmpty()) return null;

        ColorBlobLocatorProcessor.Blob largest = blobs.get(0);
        double largestRadius = getBlobRadius(largest);

        for (ColorBlobLocatorProcessor.Blob b : blobs) {
            double radius = getBlobRadius(b);
            if (radius > largestRadius) {
                largest = b;
                largestRadius = radius;
            }
        }
        return largest;
    }

    /* returns the X position of the largest blob */
    public double getX() {
        ColorBlobLocatorProcessor.Blob b = getLargestBlob();
        return (b != null) ? getBlobX(b) : 0.0;
    }

    /* returns the Y position of the largest blob */
    public double getY() {
        ColorBlobLocatorProcessor.Blob b = getLargestBlob();
        return (b != null) ? getBlobY(b) : 0.0;
    }

    /* returns the radius of the largest blob */
    public double getRadius() {
        ColorBlobLocatorProcessor.Blob b = getLargestBlob();
        return (b != null) ? getBlobRadius(b) : 0.0;
    }
}
