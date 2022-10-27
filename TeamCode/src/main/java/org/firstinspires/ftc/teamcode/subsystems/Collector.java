package org.firstinspires.ftc.teamcode.subsystems;

// Import Team Specific Libraries
import org.firstinspires.ftc.teamcode.utils.Constants;

public class Collector extends Subsystem {
    // Set the collector to open position
    public static void open() {
        collector_servo_target = Constants.COLLECTOR_OPEN_POS;
    } 

    // Set the collector to close position
    public static void close() {
        collector_servo_target = Constants.COLLECTOR_CLOSE_POS;
    } 
}