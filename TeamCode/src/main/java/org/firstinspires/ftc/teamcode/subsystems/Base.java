package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.lib.Subsystem;

public class Base extends Subsystem {
    public Base(Hardware hardware) {
        super(hardware);
    }

    public void move(double power) {
        this.hardware.testMotor.setPower(power);
    }
}
