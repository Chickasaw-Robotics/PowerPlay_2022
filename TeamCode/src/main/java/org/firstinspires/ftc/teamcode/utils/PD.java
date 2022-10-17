package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.util.Range;
public abstract class PD {
    public static double getMotorCmd (double Kp, double Kd, double error, double currentVel) {
        double motorCmd;

        motorCmd = (Kp * error) - (Kd * currentVel);
        motorCmd = Range.clip(motorCmd, -1.0, 1.0);

        return motorCmd;
    }
}
