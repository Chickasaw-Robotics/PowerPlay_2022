package org.firstinspires.ftc.teamcode.subsystems;

// Import FIRST Libraries
import com.qualcomm.robotcore.hardware.DcMotor;

// Import Team Specific Libraries
import org.firstinspires.ftc.teamcode.utils.Constants;

public class Lift extends Subsystem {
    // Automatically move the lift to the high position
    public static boolean liftToHigh() {
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.liftMotor.setTargetPosition(Constants.LIFT_HIGH_POS);
        return robot.liftMotor.isBusy();
    }

    // Automatically move the lift to the mid position
    public static boolean liftToMid() {
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.liftMotor.setTargetPosition(Constants.LIFT_MID_POS);
        return robot.liftMotor.isBusy();
    }

    // Automatically move the lift to the low position
    public static boolean liftToLow() {
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.liftMotor.setTargetPosition(Constants.LIFT_LOW_POS);
        return robot.liftMotor.isBusy();
    }
    // Automatically move the lift to the bottom position
    public static boolean liftToBot() {
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.liftMotor.setTargetPosition(Constants.LIFT_BOT_POS);
        return robot.liftMotor.isBusy();
    }

    // Set motor command to a specified power [-1.0, 1.0]
    public static void move(double command) {
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift_motor_command = command;
    }

    // Set motor command to 0
    public static void stop() {
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift_motor_command = 0;
    }
}
