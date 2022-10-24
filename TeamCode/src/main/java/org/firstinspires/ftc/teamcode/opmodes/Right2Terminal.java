package org.firstinspires.ftc.teamcode.opmodes;

// Import FIRST Libraries
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

// Import Team Specific Libraries
import static org.firstinspires.ftc.teamcode.opmodes.Right2Terminal.state.*;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.utils.Constants;

// Class for the Autonomous Period of the Game Calling Methods from Subsystems in Sequence
@Autonomous(name="Right2Terminal", group="Autonomous")
public class Right2Terminal extends OpMode {
    // Define states for the switch statement
    public state current_state;
    enum state {
        initialize,
        drive_forward,
        turn_left,
        drive_forward2,
        drop_cone,
        drive_backward,
        turn_right,
        park,
        terminate
    }

    public void init() {
        // Initialize robot hardware and starting state
        Subsystem.robot.init(hardwareMap);
        current_state = initialize;
    }

    public void loop() {
        // Add telemetry
        telemetry.addData("Auto State: ", current_state);
        telemetry.addData("Left Power: ", Subsystem.robot.leftMotor.getPower());
        telemetry.addData("Right Power: ", Subsystem.robot.rightMotor.getPower());
        telemetry.update();

        // Switch statement for autonomous
        switch (current_state) {
            case initialize:
                current_state = drive_forward;
                break;

            case drive_forward:
                if (DriveTrain.driveToPosition(Constants.auto_speed, 6, 6))
                    current_state = turn_left;
                break;

            case turn_left:
                if (DriveTrain.driveToPosition(Constants.auto_speed, -5, 5))
                    current_state = drive_forward2;
                break;

            case drive_forward2:
                if (DriveTrain.driveToPosition(Constants.auto_speed, 16, 16))
                    current_state = drop_cone;
                break;

            case drop_cone:
                DriveTrain.stopDriveTrain();
//                Collector.openCollector();
                current_state = drive_backward;
                break;

            case drive_backward:
                if (DriveTrain.driveToPosition(Constants.auto_speed, -14, -14))
                    current_state = turn_right;
                break;

            case turn_right:
                if (DriveTrain.driveToPosition(Constants.auto_speed, 5, -5))
                    current_state = park;
                break;


            case park:
                if (DriveTrain.driveToPosition(Constants.auto_speed, -23, -23))
                    current_state = terminate;
                break;

            case terminate:
                DriveTrain.stopDriveTrain();
                break;
        }
    }
}