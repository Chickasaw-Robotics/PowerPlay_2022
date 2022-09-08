package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hardware {
    protected
    DcMotor testMotor;

    public Hardware(HardwareMap map) {
        testMotor = map.dcMotor.get("test");
    }
}
