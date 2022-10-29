package org.firstinspires.ftc.teamcode.opmodes;

// Import FIRST Libraries
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

// Import Team Specific Libraries
import static org.firstinspires.ftc.teamcode.opmodes.RedLeft2Substation.state.*;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.subsystems.Collector;
import org.firstinspires.ftc.teamcode.utils.Constants;

// Class for the Autonomous period of the game calling methods from subsystems in sequence
@Autonomous(name="RedLeft2Substation", group="Autonomous")
public class RedLeft2Substation extends OpMode {
    // Declare states for the switch statement
    public state current_state;

    public enum state {
        initialize,
        drive_forward,
        turn_right,
        drive_forward2,
        drop_cone,
        drive_backward,
        turn_left,
        park,
        terminate
    }

    public void init() {
        // Initialize robot hardware
        Subsystem.robot.init(hardwareMap);
        Subsystem.robot.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Subsystem.robot.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set the starting state
        current_state = initialize;
    }

    public void loop() {
        // Update telemetry for debugging
        telemetry.addData("Auto State: ", current_state);
        telemetry.addData("Collector Servo Target", Subsystem.collector_servo_target);
        telemetry.addData("Right Motor Command", Subsystem.right_motor_command);
        telemetry.addData("Left Motor Command", Subsystem.left_motor_command);
        telemetry.addData("Lift Motor Command", Subsystem.lift_motor_command);
        telemetry.addData("Right Motor Counts: ", Subsystem.robot.rightMotor.getCurrentPosition());
        telemetry.addData("Left Motor Counts: ", Subsystem.robot.leftMotor.getCurrentPosition());
        telemetry.update();

        // Switch state for handling autonomous sequencing
        switch (current_state) {
            case initialize:
                current_state = drive_forward;
                break;

            case drive_forward:
                if (DriveTrain.driveToPosition(Constants.AUTO_SPEED, 6, 6))
                    current_state = turn_right;
                break;

            case turn_right:
                if (DriveTrain.driveToPosition(Constants.AUTO_SPEED, 5, -5))
                    current_state = drive_forward2;
                break;

            case drive_forward2:
                if (DriveTrain.driveToPosition(Constants.AUTO_SPEED, 14, 14))
                    current_state = drop_cone;
                break;

            case drop_cone:
                DriveTrain.stop();
//                Collector.open();
                current_state = drive_backward;
                break;

            case drive_backward:
                if (DriveTrain.driveToPosition(Constants.AUTO_SPEED, -15, -15))
                    current_state = turn_left;
                break;

            case turn_left:
                if (DriveTrain.driveToPosition(Constants.AUTO_SPEED, -5, 5))
                    current_state = park;
                break;

            case park:
                if (DriveTrain.driveToPosition(Constants.AUTO_SPEED, -13, -13))
                    current_state = terminate;
                break;

            case terminate:
                DriveTrain.stop();
                break;
        }
    }
}

