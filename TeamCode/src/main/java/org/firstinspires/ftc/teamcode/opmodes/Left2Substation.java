package org.firstinspires.ftc.teamcode.opmodes;

// Import FIRST Libraries
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

// Import Team Specific Libraries
import static org.firstinspires.ftc.teamcode.opmodes.Left2Substation.state.*;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.utils.Constants;

// Class for the Autonomous Period of the Game Calling Methods from Subsystems in Sequence
@Autonomous(name="Left2Substation", group="Autonomous")
public class Left2Substation extends OpMode {
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
        // Initialize Robot Hardware
        Subsystem.robot.init(hardwareMap);
        current_state = initialize;
    }
    public void loop() {
        // Center Auto Switch Statement
        telemetry.addData("Auto State: ", current_state);
        telemetry.addData("Left Power: ", Subsystem.robot.leftMotor.getPower());
        telemetry.addData("Right Power: ", Subsystem.robot.rightMotor.getPower());
        telemetry.update();

        switch (current_state) {
            case initialize:
                current_state = drive_forward;
                break;

            case drive_forward:
                if (DriveTrain.driveToPosition(Constants.auto_speed, 10, 10))
                    current_state = turn_right;
                break;

            case turn_right:
                if (DriveTrain.driveToPosition(Constants.auto_speed, 5, -5))
                    current_state = drive_forward2;
                break;

            case drive_forward2:
                if (DriveTrain.driveToPosition(Constants.auto_speed, 10, 10))
                    current_state = drop_cone;
                break;

            case drop_cone:
                DriveTrain.stopDriveTrain();
//                Collector.openCollector();
                current_state = drive_backward;
                break; 

            case drive_backward:
                if (DriveTrain.driveToPosition(Constants.auto_speed, -10, -10))
                    current_state = turn_left;
                break;

            case turn_left:
                if (DriveTrain.driveToPosition(Constants.auto_speed, -5, 5))
                    current_state = park;
                break;

            case park:
                if (DriveTrain.driveToPosition(Constants.auto_speed, -10, -10))
                    current_state = terminate;
                break;

            case terminate:
                DriveTrain.stopDriveTrain();
                break;
        }
    }
}