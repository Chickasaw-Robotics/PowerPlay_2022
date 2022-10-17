package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.team.utils.Constants;

// PID calculations contained within this class
public abstract class PID {
    public static double getDriveTrainPosCmd(double error, double time, double velocity) {
        double proportionalCmd = Constants.drivetrain_Kp * error;
        double integralCmd = Constants.drivetrain_Ki * time;
        double derivativeCmd = Constants.drivetrain_Kd * velocity;

        double motorCmd = proportionalCmd + integralCmd - derivativeCmd;
        motorCmd = Range.clip(motorCmd, -1.0, 1.0);

        return motorCmd;
    }

    public static double getDriveTrainAngleCmd(double error, double time, double velocity) {
        double proportionalCmd = Constants.drivetrainAngle_Kp * error;
        double integralCmd = Constants.drivetrainAngle_Ki * time;
        double derivativeCmd = Constants.drivetrainAngle_Kd * velocity;

        double motorCmd = proportionalCmd + integralCmd - derivativeCmd;
        motorCmd = Range.clip(motorCmd, -1.0, 1.0);

        return motorCmd;
    }
}
