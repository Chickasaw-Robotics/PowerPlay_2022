package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.robot.Hardware;

public class navigate {
    static final double COUNTS_PER_INCH = 24.9; // Counts Previous 49.8
    static final double STRAFE_COUNTS_PER_INCH = 31.1;
    static final double POSITION_THRESHOLD = 1.0;   // Base travel
    static final double ANGLE_THRESHOLD = 4.0;     // Degrees
    Hardware robot;
    static double leftFMotorCmd, rightFMotorCmd, leftBMotorCmd, rightBMotorCmd, linearError;

    public static boolean driveToPos(double targetPosInches, double targetAngleDeg, double currentBasePosCounts, double currentAngleDeg,
                                     double linearSpeedCounts, double maxPower) {
        double angleErrorDegrees = targetAngleDeg - currentAngleDeg;
        double currentPosInches = (currentBasePosCounts / COUNTS_PER_INCH);
        double linearSpeedInches = linearSpeedCounts / COUNTS_PER_INCH;
        double angleOffset;
        linearError = targetPosInches - currentPosInches;
        double angularError = targetAngleDeg - currentAngleDeg;
        double motorCmd = PD.getMotorCmd(0.02, 0.07, linearError, linearSpeedInches);

        // Determine the baseline motor speed command, but limit it to leave room for the turn offset
        if (maxPower == -999) {
            motorCmd = Range.clip(motorCmd, -0.6, 0.6);
        } else {
            motorCmd = Range.clip(motorCmd, -0.6, 0.6);
            motorCmd = Range.clip(motorCmd, -maxPower, maxPower);
        }


        // Determine and add the angle offset
        angleOffset = PD.getMotorCmd(0.02, 0.001, angularError, 0);
        leftFMotorCmd = motorCmd - angleOffset;
        rightFMotorCmd = motorCmd + angleOffset;
        leftFMotorCmd = Range.clip(leftFMotorCmd, -1.0, 1.0);
        rightFMotorCmd = Range.clip(rightFMotorCmd, -1.0, 1.0);

        // Limit the max motor command for gentle motion
        if (maxPower == -999) {
            leftFMotorCmd = Range.clip(leftFMotorCmd, -.25, .25);
            rightFMotorCmd = Range.clip(rightFMotorCmd, -.25, .25);
        }
        leftBMotorCmd = leftFMotorCmd;
        rightBMotorCmd = rightFMotorCmd;

        // True if navigated to position
        return (Math.abs(linearError) < POSITION_THRESHOLD) && (Math.abs(angleErrorDegrees) < ANGLE_THRESHOLD);
    }

    public static void stopBase ()
    {
        leftFMotorCmd = 0;
        rightFMotorCmd = 0;
        leftBMotorCmd = 0;
        rightBMotorCmd = 0;
    }

    public static double getRightFMotorCmd ()
    {
        return rightFMotorCmd;
    }

    public static double getRightBMotorCmd ()
    {
        return rightBMotorCmd;
    }

    public static double getLeftFMotorCmd ()
    {
        return leftFMotorCmd;
    }

    public static double getLeftBMotorCmd ()
    {
        return leftBMotorCmd;
    }

    public static double getLinearError () {
        return linearError;
    }
}
