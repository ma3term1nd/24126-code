package org.firstinspires.ftc.teamcode.main;

import android.graphics.Color;
import org.firstinspires.ftc.vision.opencv.Circle;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.firstinspires.ftc.vision.opencv.ColorRange;
import org.firstinspires.ftc.vision.opencv.ImageRegion;

import java.util.List;

public class ColorSensor {

    private ColorBlobLocatorProcessor colorLocator;

    public ColorSensor(String color) {
        ColorRange range = color.equals("purple") ?
                ColorRange.ARTIFACT_PURPLE : ColorRange.ARTIFACT_GREEN;

        colorLocator = new ColorBlobLocatorProcessor.Builder()
                .setTargetColorRange(range)
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
                .setRoi(ImageRegion.entireFrame())
                .setCircleFitColor(Color.rgb(255, 255, 0))
                .setBlurSize(5)
                .setDilateSize(15)
                .setErodeSize(15)
                .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)
                .build();
    }

    public ColorBlobLocatorProcessor getColorSensor() {
        return colorLocator;
    }

    private ColorBlobLocatorProcessor.Blob getLargestBlob() {
        List<ColorBlobLocatorProcessor.Blob> blobs = colorLocator.getBlobs();
        if (blobs.isEmpty()) return null;

        ColorBlobLocatorProcessor.Blob largest = blobs.get(0);
        for (ColorBlobLocatorProcessor.Blob b : blobs) {
            if (b.getCircle().getRadius() > largest.getCircle().getRadius()) {
                largest = b;
            }
        }
        return largest;
    }

    public double getX() {
        var b = getLargestBlob();
        return (b != null) ? b.getCircle().getX() : 0.0;
    }

    public double getY() {
        var b = getLargestBlob();
        return (b != null) ? b.getCircle().getY() : 0.0;
    }

    public double getRadius() {
        var b = getLargestBlob();
        return (b != null) ? b.getCircle().getRadius() : 0.0;
    }
}
