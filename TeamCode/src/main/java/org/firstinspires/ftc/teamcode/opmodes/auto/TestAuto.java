package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.lib.FCLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Base;

/**
 * @Autonomous tells the driver station that this class is a auto mode and to list it on the driver station.
 */
@Autonomous(name = "Test Auto")
public class TestAuto extends FCLinearOpMode {
    Base base;

    /**
     * Everything before wait for start is executed when the driver presses the init button on the driver station.
     */
    @Override
    public void runOpMode() throws InterruptedException {
        base = new Base(this.hardware);

        /* This pauses the linear op-mode using a while loop until the driver selects play */
        waitForStart();

        /*
         * All this while loop is doing is it's calling the move method repeatedly from the base subsystem
         * until the driver presses stop.
         */
        while (opModeIsActive()) {
            base.move(1);
        }
    }
}
