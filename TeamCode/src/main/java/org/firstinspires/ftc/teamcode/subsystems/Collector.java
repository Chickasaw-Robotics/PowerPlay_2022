package org.firstinspires.ftc.teamcode.subsystems;

// Import Team Specific Libraries
import org.firstinspires.ftc.teamcode.robot.Controls;
import org.firstinspires.ftc.teamcode.robot.Hardware;
import org.firstinspires.ftc.teamcode.utils.Constants;

public class Collector extends Subsystem {
    // Set the collector to open position
    public static void open() {
        robot.collectorServo.setPosition(Constants.COLLECTOR_OPEN_POS);
    } 

    // Set the collector to close position
    public static void close() {
         robot.collectorServo.setPosition(Constants.COLLECTOR_CLOSE_POS);
    }

    //get servo position, if going one direction add or subtract from it

    public static void turnLeft() { robot.turnCollectorServo.setPosition(robot.turnCollectorServo.getPosition() - Constants.TURN_SERVO_INCREMENT);}
    public static void turnRight() { robot.turnCollectorServo.setPosition(robot.turnCollectorServo.getPosition() + Constants.TURN_SERVO_INCREMENT);}
}
