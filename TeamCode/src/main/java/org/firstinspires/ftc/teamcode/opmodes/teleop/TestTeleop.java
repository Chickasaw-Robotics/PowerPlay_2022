package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.lib.FCOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Base;

/**
 * @TeleOp tells the driver station that this class is a teleop mode and to list it on the driver station.
 */
@TeleOp(name = "Test Tele-op")
public class TestTeleop extends FCOpMode {
    Base base;

    /**
     * Everything in the init method is executed when the driver presses the init button on the driver station.
     *
     * The init state always comes before the start state which is handled by the loop method.
     */
    @Override
    public void init() {
        base = new Base(hardware);
    }

    /**
     * Loop runs when start is selected on the driver station and runs repeatedly until the driver presses the stop button.
     */
    @Override
    public void loop() {
        base.move(1);
    }
}
