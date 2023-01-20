package org.firstinspires.ftc.teamcode.subsystems;

// Import Team Specific Libraries
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeServices;
import org.firstinspires.ftc.teamcode.opmodes.ScanAndPark;
import org.firstinspires.ftc.teamcode.utils.Constants;


public class DriveTrain extends Subsystem {
    public static boolean isMovingToPosition() {
        return (robot.leftMotor.isBusy() || robot.rightMotor.isBusy());
    }

    // Drive the robot a specified number of inches
    public static void driveToPosition(double power, double leftInches, double rightInches) {
        // Create target positions
        int rightTarget = robot.rightMotor.getCurrentPosition() + (int)(rightInches * Constants.DRIVE_COUNTS_PER_INCH);
        int leftTarget = robot.leftMotor.getCurrentPosition() + (int)(leftInches * Constants.DRIVE_COUNTS_PER_INCH);

        // Set target position
        robot.leftMotor.setTargetPosition(leftTarget);
        robot.rightMotor.setTargetPosition(rightTarget);

        // Run to position at the designated power
        robot.leftMotor.setPower(power);
        robot.rightMotor.setPower(power);

        Subsystem.robot.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Subsystem.robot.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    // Turn the robot a specified number of degrees
    public static void turnToPosition(double power, double degrees) {
        int rightTarget = robot.rightMotor.getCurrentPosition() + (int)(-degrees * Constants.DRIVE_COUNTS_PER_DEGREE);
        int leftTarget = robot.leftMotor.getCurrentPosition() + (int)(degrees * Constants.DRIVE_COUNTS_PER_DEGREE);

        // Set target position
        robot.leftMotor.setTargetPosition(leftTarget);
        robot.rightMotor.setTargetPosition(rightTarget);

        // Run to position at the designated power
        robot.leftMotor.setPower(power);
        robot.rightMotor.setPower(power);

        Subsystem.robot.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Subsystem.robot.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    // Set motor commands to a specified power [-1.0, 1.0]
    public static void drive(double left_command, double right_command) {
//        if (Math.abs(left_command) > Constants.DRIVE_SPEED) {
//            if (left_command > 0) {
//                left_motor_command = Constants.DRIVE_SPEED;
//            } else {
//                left_motor_command = -Constants.DRIVE_SPEED;
//            }
//        } else {
//            left_motor_command = left_command;
//        }
//         <condition1> ? <condition2> ? <condition2_true> : <condition2_false> : <condition1_false>

        // Sets maximum motor power to be bounded within the range defined by Constants.DRIVE_SPEED
        left_motor_command = Math.abs(left_command) > Constants.DRIVE_SPEED ? left_command > 0 ? Constants.DRIVE_SPEED : -Constants.DRIVE_SPEED : left_command;
        right_motor_command = Math.abs(right_command) > Constants.DRIVE_SPEED ? right_command > 0 ? Constants.DRIVE_SPEED : -Constants.DRIVE_SPEED : right_command;
    }

    // Set motor commands to 0
    public static void stop() {
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);
        left_motor_command = 0;
        right_motor_command = 0;
    }
}
