package org.firstinspires.ftc.teamcode.subsystems;

// Import FIRST Libraries
import org.firstinspires.ftc.teamcode.robot.Hardware;

public class Subsystem {
    // Create a robot instance
    public static Hardware robot = new Hardware();

    // Declare motor and servo commands and target positions respectively
    public static double collector_servo_target;
    public static double right_motor_command;
    public static double left_motor_command;
    public static double lift_motor_command;

    // Set motor powers based on their respective commands
    public static void set_motor_powers() {
        robot.leftMotor.setPower(left_motor_command);
        robot.rightMotor.setPower(right_motor_command);
        robot.liftMotor.setPower(lift_motor_command);
    }

    // Set servo positions based on their respective targets
    public static void set_servo_positions() {
        robot.collectorServo.setPosition(collector_servo_target);
    }
}
