package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.utils.Constants;

public class DriveTrain extends Subsystem {
    //Move Drive Train
    public static void moveDriveTrain(double left_command, double right_command) {
        left_motor_command = left_command;
        right_motor_command = right_command;
    }

    //TODO Turn Command

    //TODO Drive Direction


    //TODO Drive to Position
    public static boolean driveToPosition(double power, double leftInches, double rightInches) {
        int rightTarget;
        int leftTarget;

        // Create target positions
        rightTarget = Subsystem.robot.rightMotor.getCurrentPosition() + (int)(rightInches * Constants.DRIVE_COUNTS_PER_IN);
        leftTarget = Subsystem.robot.leftMotor.getCurrentPosition() + (int)(leftInches * Constants.DRIVE_COUNTS_PER_IN);

        // Set target position
        Subsystem.robot.leftMotor.setTargetPosition(leftTarget);
        Subsystem.robot.rightMotor.setTargetPosition(rightTarget);

        // Switch to run to position mode
        Subsystem.robot.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Subsystem.robot.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Run to position at the designated power
        Subsystem.robot.leftMotor.setPower(power);
        Subsystem.robot.rightMotor.setPower(power);

        // Wait until both motors are no longer busy running to position
        while ( (Subsystem.robot.leftMotor.isBusy() || Subsystem.robot.rightMotor.isBusy())) {
            System.out.println("Driving to " + leftInches + ", " + rightInches);
        }

        // Set motor power back to 0
        Subsystem.robot.leftMotor.setPower(0);
        Subsystem.robot.rightMotor.setPower(0);

        // Return true to indicate we are done
        return true;
    }

    // Stop the Drive Train
    public static void stopDriveTrain() {
        left_motor_command = 0;
        right_motor_command = 0;
    }
}
