package org.firstinspires.ftc.teamcode.subsystems;

public class Lift extends Subsystem {
    //Move Lift
    public static void moveLift(double command) {
        lift_motor_command = command;
    }

    //TODO Lift to Position

    // Stop the Lift
    public static void stopLift() {
        lift_motor_command = 0;
    }
}
