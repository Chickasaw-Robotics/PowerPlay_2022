package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;


public class Hardware {
    //Declaring the variables in the class.
    public DcMotor leftMotor;
    public DcMotor rightMotor;
    public DcMotor liftMotor;

    public void init (HardwareMap map) {
        //Defining variables to the physical location
        leftMotor = map.dcMotor.get("port0");
        rightMotor = map.dcMotor.get("port1");
        liftMotor = map.dcMotor.get("port2");

        //Calls configureMotors() method
        configureMotors();
    }

    //Initializing all the motors' settings.
    private void configureMotors() {
        //Setting the motors to run using the encoders.
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Setting the direction of the motors when running.
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);

        //Setting motors to brake if not running.
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}