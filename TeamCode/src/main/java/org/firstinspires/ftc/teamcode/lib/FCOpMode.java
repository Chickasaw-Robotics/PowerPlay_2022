package org.firstinspires.ftc.teamcode.lib;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsystems.Hardware;

public abstract class FCOpMode extends OpMode {
    protected Hardware hardware;

    @Override
    public void internalPreInit() {
        super.internalPreInit();

        // cool
        hardware = new Hardware(hardwareMap);
    }
}
