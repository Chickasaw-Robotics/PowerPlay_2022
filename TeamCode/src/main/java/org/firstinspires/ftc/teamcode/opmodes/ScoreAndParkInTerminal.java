package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.opmodes.ScoreAndParkInTerminal.state.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.subsystems.Collector;

// Class for the Autonomous Period of the Game Calling Methods from Subsystems in Sequence
@Autonomous(name="Right3Terminal", group="Autonomous")
public class ScoreAndParkInTerminal extends OpMode {
    public state current_state;
    double autoSpeed = 0.3;
    public enum state {
        initialize,
        drive_forward,
        turn_left,
        drive_backward,
        drop_cone,
        drive_backward2,
        turn_right,
        park,
        terminate
    }

    @Override
    public double getRuntime() {
        return super.getRuntime();
    }

    private double markedTime;

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
                markedTime = getRuntime();
                current_state = drive_forward;
                break;

            case drive_forward: 
                DriveTrain.moveDriveTrain(autoSpeed, autoSpeed);
                if ((getRuntime() - markedTime) > 3) {
                    markedTime = getRuntime();
                    current_state = turn_left;
                }
                break;

            case turn_left:
                DriveTrain.moveDriveTrain(-autoSpeed, autoSpeed);
                if ((getRuntime() - markedTime) > 2) {
                    markedTime = getRuntime();
                    current_state = drive_backward;
                }
                break;

            case drive_backward:
                DriveTrain.moveDriveTrain(-autoSpeed, -autoSpeed);
                if ((getRuntime() - markedTime) > 1) {
                    markedTime = getRuntime();
                    current_state = drop_cone;
                }
                break;

            case drop_cone:
                DriveTrain.stopDriveTrain();
                Collector.openCollector();
                markedTime = getRuntime();
                current_state = drive_backward2;
                break;

            case drive_backward2:
                DriveTrain.moveDriveTrain(-autoSpeed, -autoSpeed);
                if ((getRuntime() - markedTime) > 0.5) {
                    markedTime = getRuntime();
                    current_state = turn_right;
                }
                break;

            case turn_right:
                DriveTrain.moveDriveTrain(autoSpeed, -autoSpeed);
                if ((getRuntime() - markedTime) > 2) {
                    markedTime = getRuntime();
                    current_state = park;
                }
                break;

            case park:
                DriveTrain.moveDriveTrain(-autoSpeed, -autoSpeed);
                if ((getRuntime() - markedTime) > 2) {
                    markedTime = getRuntime();
                    current_state = terminate;
                }
                break;

            case terminate:
                DriveTrain.stopDriveTrain();
                break;
        }

        
        Subsystem.set_motor_powers();
        Subsystem.set_servo_positions();
    }
}