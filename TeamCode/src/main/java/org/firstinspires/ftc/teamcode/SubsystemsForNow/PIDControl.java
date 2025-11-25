package org.firstinspires.ftc.teamcode.SubsystemsForNow;

import com.qualcomm.robotcore.util.ElapsedTime;


public class PIDControl {
    ElapsedTime timer = new ElapsedTime();
    private double integralSum = 0;
    private double lastError = 0;
    public double Kp;
    public double Ki;
    public double Kd;
    public double Kf;
    private boolean firstTimeInitialization = true;
    public PIDControl (double Kp, double Ki, double Kd, double Kf){
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
        this.Kf = Kf;
    }
    public double pidControl(double reference, double state){
        if(firstTimeInitialization){
            firstTimeInitialization = false;
            return reference;
        }
        double deltaTime = timer.seconds();
        timer.reset();
        double error = reference - state; //reference = input, state = output.
        integralSum += error * deltaTime;
        double derivative = (error - lastError)/deltaTime;
        lastError = error;

        double output = (error * Kp) + (derivative * Kd) + (integralSum * Ki) + (reference * Kf);

        return output;

    }
    public void changePIDCoefficients (double Kp, double Ki, double Kd, double Kf ){
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
        this.Kf = Kf;
    }
}
