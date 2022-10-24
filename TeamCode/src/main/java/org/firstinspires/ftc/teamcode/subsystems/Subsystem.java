package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.robot.Hardware;

public class Subsystem {
    public static Hardware robot = new Hardware();
    public static double left_motor_command;
    public static double right_motor_command;
//    public static double lift_motor_command;

//    public static double collector_servo_target_position;

    public static void set_motor_powers() {
        robot.leftMotor.setPower(left_motor_command);
        robot.rightMotor.setPower(right_motor_command);
//        robot.liftMotor.setPower(lift_motor_command);
    }

    public static double getLeftMotorPower() {
        return robot.leftMotor.getPower();
    }

    public static double getLeftMotorCommand() {
        return left_motor_command;
    }

    public static void set_servo_positions() {
//        robot.collectorServo.setPosition(collector_servo_target_position);
    }
}
