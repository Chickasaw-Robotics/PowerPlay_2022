package org.firstinspires.ftc.teamcode.lib;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.Hardware;

public abstract class FCLinearOpMode extends com.qualcomm.robotcore.eventloop.opmode.LinearOpMode {
    protected Hardware hardware;

    @Override
    public void internalPreInit() {
        super.internalPreInit();

        hardware = new Hardware(hardwareMap);
    }
}
