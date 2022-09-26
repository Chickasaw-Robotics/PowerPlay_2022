package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class Controls extends OpMode {
    public double getDriverLeftJoystick() {
        return gamepad1.left_stick_y;
    }

    public double getDriverRightJoystick() {
        return gamepad1.right_stick_y;
    }
}
