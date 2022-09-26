package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.robot.Hardware;

public class Subsystem {
    public static Hardware robot = new Hardware();
    public static double left_motor_command;
    public static double right_motor_command;

    public static void set_motor_powers() {
        robot.leftMotor.setPower(left_motor_command);
        robot.rightMotor.setPower(right_motor_command);
    }
}
