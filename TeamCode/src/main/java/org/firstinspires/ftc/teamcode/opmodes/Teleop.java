package org.firstinspires.ftc.teamcode.opmodes;

// Import FIRST Libraries
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// Import Team Specific Libraries
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Collector;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.robot.Controls;

@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends Controls {
    public void init() {
        // Initialize hardware
        Subsystem.robot.init(hardwareMap);

        // Update telemetry
        telemetry.addData("Status:", "Press Play to Start");
        telemetry.update();
    }

    public void loop() {
        // Driver 1 Controls
        DriveTrain.drive(Math.pow(getDriverLeftJoystickY(), 3), Math.pow(getDriverLeftJoystickY(), 3));
        if(getDriverRightJoystickX() < 0) {
            DriveTrain.drive(Math.pow(getDriverRightJoystickX(), 3), 0);
        }
        if(getDriverRightJoystickX() > 0) {
            DriveTrain.drive(0, Math.pow(getDriverRightJoystickX(), 3));
        }

        // Driver 2 Controls
        Lift.move(getDriver2LeftJoystick());
        if(getDriver2RightBumper())
            Collector.close();
        if(getDriver2LeftBumper())
            Collector.open();
        if(getDriver2A())
            Lift.liftToBot();
        if(getDriver2B())
            Lift.liftToLow();
        if(getDriver2LeftTrigger() < 0)
            Collector.turnLeft();
        if(getDriver2RightTrigger() < 0)
            Collector.turnRight();

        // Update telemetry
        telemetry.addData("Status", "Teleop is running");
        telemetry.addData("Collector Servo Target", Subsystem.robot.collectorServo.getPosition());
        telemetry.addData("Turn Collector Servo Target", Subsystem.robot.turnCollectorServo.getPosition());
        telemetry.addData("Right Motor Command", Subsystem.right_motor_command);
        telemetry.addData("Left Motor Command", Subsystem.left_motor_command);
        telemetry.addData("Lift Motor Command", Subsystem.lift_motor_command);
        telemetry.addData("Collector Servo Pos: ", Subsystem.robot.collectorServo.getPosition());
        telemetry.addData("Right Motor Pos: ", Subsystem.robot.rightMotor.getCurrentPosition());
        telemetry.addData("Left Motor Pos: ", Subsystem.robot.leftMotor.getCurrentPosition());
        telemetry.addData("Lift Motor Pos: ", Subsystem.robot.liftMotor.getCurrentPosition());
        telemetry.update();

        // Update hardware with new commands and positions
        Subsystem.set_motor_powers();
    }

    public void stop() {
        // Stop all subsystems
        DriveTrain.stop();
        Lift.stop();

        // Update telemetry
        telemetry.addData("Status:", "Teleop is stopped");
        telemetry.update();
    }
}
