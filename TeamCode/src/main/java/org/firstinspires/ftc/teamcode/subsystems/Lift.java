package org.firstinspires.ftc.teamcode.subsystems;

// Import FIRST Libraries
import com.qualcomm.robotcore.hardware.DcMotor;

// Import Team Specific Libraries
import org.firstinspires.ftc.teamcode.utils.Constants;

public class Lift extends Subsystem {
    // Automatically move the lift to the low position
    public static void liftToLow() {
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.liftMotor.setTargetPosition((int)(Constants.LIFT_LOW_POS * Constants.LIFT_COUNTS_PER_INCH));
        robot.liftMotor.setPower(Constants.LIFT_SPEED);
        while(robot.liftMotor.isBusy())
            System.out.println("Lifting to Low");
        robot.liftMotor.setPower(0);
    }

    // Automatically move the lift to the bottom position
    public static void liftToBot() {
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.liftMotor.setTargetPosition((int)(Constants.LIFT_BOT_POS * Constants.LIFT_COUNTS_PER_INCH));
        robot.liftMotor.setPower(Constants.LIFT_SPEED);
        while(robot.liftMotor.isBusy())
            System.out.println("Lifting to Bot");
        robot.liftMotor.setPower(0);
    }

    // Set motor command to a specified power [-1.0, 1.0]
    public static void move(double command) {
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if(robot.liftMotor.getCurrentPosition() > 8000)
            lift_motor_command = Math.min(0, command);
        else
            lift_motor_command = command;
    }

    // Set motor command to 0
    public static void stop() {
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift_motor_command = 0;
    }
}
