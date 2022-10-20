package org.firstinspires.ftc.teamcode.subsystems;

public class Lift extends Subsystem {
    //Move Lift
    public static void moveLift(double command) {
        lift_motor_command = command;
    }

    //TODO Lift to Position
    public static void liftToGround {
        robot.liftMotor.setTargetPosition(0);
    }
    
    public static void liftToLow {
        robot.liftMotor.setTargetPosition(20);
    }

    public static void liftToMedium {
        robot.liftMotor.setTargetPosition(30);
    }

    public static void liftToHigh {
        robot.liftMotor.setTargetPosition(40);
    }

    // Stop the Lift
    public static void stopLift() {
        lift_motor_command = 0;
    }
}
