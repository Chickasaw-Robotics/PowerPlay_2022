package org.firstinspires.ftc.teamcode.subsystems;

// Import Team Specific Libraries
import org.firstinspires.ftc.teamcode.utils.Constants;

public class DriveTrain extends Subsystem {
    // Drive the robot a specified number of inches
    public static boolean driveToPosition(double power, double leftInches, double rightInches) {
        // Create target positions
        int rightTarget = robot.rightMotor.getCurrentPosition() + (int)(rightInches * Constants.DRIVE_COUNTS_PER_INCH);
        int leftTarget = robot.leftMotor.getCurrentPosition() + (int)(leftInches * Constants.DRIVE_COUNTS_PER_INCH);

        // Set target position
        robot.leftMotor.setTargetPosition(leftTarget);
        robot.rightMotor.setTargetPosition(rightTarget);

        // Run to position at the designated power
        robot.leftMotor.setPower(power);
        robot.rightMotor.setPower(power);

        // Wait until both motors are no longer busy running to position
        while (robot.leftMotor.isBusy() || robot.rightMotor.isBusy())
            System.out.println("Driving to " + leftInches + ", " + rightInches);

        // Set motor power back to 0
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);

        // Return true to indicate success
        return true;
    }

    // Turn the robot a specified number of degrees
    public static boolean turnToPosition(double power, double degrees) {
        int rightTarget = robot.rightMotor.getCurrentPosition() + (int)(-degrees * Constants.DRIVE_COUNTS_PER_DEGREE);
        int leftTarget = robot.leftMotor.getCurrentPosition() + (int)(degrees * Constants.DRIVE_COUNTS_PER_DEGREE);

        // Set target position
        robot.leftMotor.setTargetPosition(leftTarget);
        robot.rightMotor.setTargetPosition(rightTarget);

        // Run to position at the designated power
        robot.leftMotor.setPower(power);
        robot.rightMotor.setPower(power);

        // Wait until both motors are no longer busy running to position
        while (robot.leftMotor.isBusy() || robot.rightMotor.isBusy())
            System.out.println("Turning to " + degrees);

        // Set motor power back to 0
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);

        // Return true to indicate success
        return true;
    }

    // Set motor commands to a specified power [-1.0, 1.0]
    public static void drive(double left_command, double right_command) {
        left_motor_command = left_command;
        right_motor_command = right_command;
    }

    // Set motor commands to 0
    public static void stop() {
        left_motor_command = 0;
        right_motor_command = 0;
    }
}
