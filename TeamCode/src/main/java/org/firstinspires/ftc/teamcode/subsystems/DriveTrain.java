package org.firstinspires.ftc.teamcode.subsystems;

public class DriveTrain extends Subsystem {
    //Move Drive Train
    public static void moveDriveTrain(double left_command, double right_command) {
        left_motor_command = left_command;
        right_motor_command = right_command;
    }

    //TODO Turn Command

    //TODO Drive Direction

    //TODO Drive to Position

    // Stop the Drive Train
    public static void stopDriveTrain() {
        left_motor_command = 0;
        right_motor_command = 0;
    }
}
