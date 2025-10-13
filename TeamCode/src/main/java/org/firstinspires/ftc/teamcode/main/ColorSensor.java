package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.*;

import java.util.ArrayList;
import java.util.List;

/* UTILITY CLASS */

public class ColorSensor extends OpenCvPipeline {

    public enum BallColor { PURPLE, GREEN }

    private OpenCvWebcam webcam;
    private BallColor targetColor = BallColor.PURPLE; // default color to track
    private Point ballCenter = null; // stores last detected ball center
    private boolean sensorEnabled = true; // toggle detection

    /* INIT CAMERA */
    public void init(HardwareMap hwMap) {
        int cameraMonitorViewId = hwMap.appContext
                .getResources()
                .getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());

        webcam = OpenCvCameraFactory.getInstance().createWebcam(
                hwMap.get(WebcamName.class, "webcam"),
                cameraMonitorViewId);

        webcam.setPipeline(this);
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {}
            @Override
            public void onError(int errorCode) {}
        });
    }

    @Override
    public Mat processFrame(Mat input) {
        if (!sensorEnabled) {
            // If detection is disabled, skip processing
            ballCenter = null;
            return input;
        }

        Mat hsv = new Mat();
        Mat mask = new Mat();

        // Convert RGB to HSV
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        // Create mask based on target color
        if (targetColor == BallColor.PURPLE) {
            Scalar lowerPurple = new Scalar(125, 50, 50);
            Scalar upperPurple = new Scalar(155, 255, 255);
            Core.inRange(hsv, lowerPurple, upperPurple, mask);
        } else if (targetColor == BallColor.GREEN) {
            Scalar lowerGreen = new Scalar(35, 50, 50);
            Scalar upperGreen = new Scalar(85, 255, 255);
            Core.inRange(hsv, lowerGreen, upperGreen, mask);
        }

        // Find contours
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(mask, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Reset center before searching
        ballCenter = null;
        double maxArea = 0;

        // Find largest contour and its center
        for (MatOfPoint contour : contours) {
            double area = Imgproc.contourArea(contour);
            if (area > 100 && area > maxArea) { // filter out noise, pick largest
                Moments m = Imgproc.moments(contour);
                ballCenter = new Point(m.m10 / m.m00, m.m01 / m.m00);
                maxArea = area;
            }
        }

        return input;
    }

    /* METHODS */

    public void setTargetColor(BallColor color) {
        targetColor = color;
    }

    public Point getBallCenter() {
        return ballCenter;
    }

    public void stopWebcam() {
        sensorEnabled = false;
    }

    public void startWebcam() {
        sensorEnabled = true;
    }
}
