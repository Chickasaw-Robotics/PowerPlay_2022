package org.firstinspires.ftc.teamcode.robot;

// Import FIRST Libraries
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class Controls extends OpMode {
    public double getDriverLeftJoystick() {
        return -gamepad1.left_stick_y;
    }

    public double getDriverRightJoystick() {
        return -gamepad1.right_stick_y;
    }

    public double getDriver2LeftJoystick() {
        return -gamepad2.left_stick_y;
    }

    public boolean getDriver2LeftBumper() {
        return gamepad2.left_bumper;
    }

    public boolean getDriver2RightBumper() {
        return gamepad2.right_bumper;
    }

    public boolean getDriver2A() {
        return gamepad2.a;
    }

    public boolean getDriver2B() {
        return gamepad2.b;
    }

    public boolean getDriver2Y() {
        return gamepad2.y;
    }

    public float getDriver2LeftTrigger() {return gamepad2.left_trigger;}

    public float getDriver2RightTrigger() {return gamepad2.right_trigger;}
}
