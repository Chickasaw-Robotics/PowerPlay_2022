package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Controls;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends Controls {
    public void init() {
        Subsystem.robot.init(hardwareMap);
        telemetry.addData("Status:", "Press Play to Start");
        telemetry.update();
    }

    public void loop() {
        DriveTrain.moveDriveTrain(getDriverLeftJoystick(), getDriverRightJoystick());
        Lift.moveLift(getDriver2LeftJoystick());

        // update telemetry
        telemetry.addData("Status", "Teleop is running");
        telemetry.addData("Left Motor Command", Subsystem.left_motor_command);
        telemetry.addData("Right Motor Command", Subsystem.right_motor_command);
        telemetry.addData("Lift Motor Command", Subsystem.lift_motor_command);
        telemetry.update();

        // set motor power
        Subsystem.set_motor_powers();
    }

    public void stop() {
        DriveTrain.stopDriveTrain();
        Lift.stopLift();

        telemetry.addData("Status:", "Teleop is stopped");
        telemetry.update();
    }
}
