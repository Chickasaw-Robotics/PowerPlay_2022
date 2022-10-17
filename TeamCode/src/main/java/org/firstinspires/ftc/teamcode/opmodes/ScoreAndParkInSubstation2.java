package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.robot.Controls;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.Collector;

// Class for the Autonomous Period of the Game Calling Methods from Subsystems in Sequence
@Autonomous(name="Left1Substation", group="Autonomous")
public class ScoreAndParkInSubstation2 extends OpMode{
    public state current_state;
    double autoSpeed = 0.3;
    public enum state {
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

    @Override
    public double getRuntime() {
        return super.getRuntime();
    }

    private ElapsedTime runtime = new ElapsedTime();
    private double markedTime;
    public Hardware robot = new Hardware();


    public void init() {
        // Initialize Robot Hardware
        current_state = initialize;
    }
    public void loop() {
        // Center Auto Switch Statement
        telemetry.addData("Auto State: ", current_state);
        telemetry.update();

        switch (current_state) {
            case initialize
                markedTime = runtime.milliseconds();
                current_state = drive_forward;
                break;

            case drive_forward: 
                DriveTrain.moveDriveTrain(autoSpeed, autoSpeed);
                if ((runtime.milliseconds() - markedTime) > 3000) {
                    markedTime = runtime.milliseconds();
                    current_state = turn_left;
                }
                break;

            case turn_left:
                DriveTrain.moveDriveTrain(-autoSpeed, autoSpeed);
                if ((runtime.milliseconds() - markedTime) > 1500) {
                    markedTime = runtime.milliseconds();
                    current_state = drive_forward2;
                }
                break;

            case drive_forward2:
                DriveTrain.moveDriveTrain(autoSpeed, autoSpeed);
                if ((runtime.milliseconds() - markedTime) > 2000) {
                    markedTime = runtime.milliseconds();
                    current_state = drop_cone;
                }
                break;

            case drop_cone:
                DriveTrain.stopDriveTrain();
                Collector.openCollector();
                markedTime = runtime.milliseconds();
                current_state = drive_backward;
                break;

            case drive_backward:
                DriveTrain.moveDriveTrain(-autoSpeed, -autoSpeed);
                if ((runtime.milliseconds() - markedTime) > 3000) {
                    markedTime = runtime.milliseconds();
                    current_state = turn_right;
                }
                break;

            case turn_right:
                DriveTrain.moveDriveTrain(autoSpeed, -autoSpeed);
                if ((runtime.milliseconds() - markedTime) > 1000) {
                    markedTime = runtime.milliseconds();
                    current_state = park;
                }
                break;

            case park:
                DriveTrain.moveDriveTrain(-autoSpeed, -autoSpeed);
                if ((runtime.milliseconds() - markedTime) > 3000) {
                    markedTime = runtime.milliseconds();
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