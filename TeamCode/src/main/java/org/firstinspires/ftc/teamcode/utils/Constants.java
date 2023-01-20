package org.firstinspires.ftc.teamcode.utils;

public class Constants {
    // Auto variables
    public static double DRIVE_COUNTS_PER_DEGREE = 11.48; //Changed since gears were changed
    public static double DRIVE_COUNTS_PER_INCH = 113.916; //Changed since gears were changed
    public static double LIFT_COUNTS_PER_INCH = 574.22;
    public static double AUTO_SPEED = 0.5; //Changed since gears were changed

    // Preset positions
    public static final double COLLECTOR_CLOSE_POS = 0.6;
    public static final double COLLECTOR_OPEN_POS = 1.0;
    public static final double LIFT_MAX_POS = 13.5;
    public static final double LIFT_LOW_POS = 13.5;
    public static final double LIFT_BOT_POS = 0;

    // Hardware ports
    public static String TURN_COLLECTOR_SERVO = "turn_collector";
    public static String COLLECTOR_SERVO_PORT = "collector";
    public static String RIGHT_MOTOR_PORT = "right";
    public static String LEFT_MOTOR_PORT = "left";
    public static String LIFT_MOTOR_PORT = "lift";

    public static double TURN_SERVO_INCREMENT = 0.1;
    public static double DRIVE_SPEED = 0.4;
    public static double LIFT_SPEED = 0.8;
}