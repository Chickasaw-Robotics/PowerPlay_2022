package org.firstinspires.ftc.teamcode.robot;

// Import FIRST Libraries
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    // Declare hardware variables
    public Servo collectorServo;
    public DcMotor rightMotor;
    public DcMotor leftMotor;
    public DcMotor liftMotor;

    public void init (HardwareMap map) {
        // Tie hardware variables to their ports
//        collectorServo = map.servo.get("port3");
        rightMotor = map.dcMotor.get("port1");
        leftMotor = map.dcMotor.get("port0");
//        liftMotor = map.dcMotor.get("port2");

        // Configure robot hardware
        configureMotors();
//        configureServos();
    }

    // Initialize all the motor settings.
    private void configureMotors() {
        // Reset encoder counts
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set the motors to run using the encoders
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Set the direction of the motors
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
//        liftMotor.setDirection(DcMotor.Direction.FORWARD);

        // Set motors to brake if not running
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // Initialize all the servo settings
    private void configureServos() {
        // Set the direction of the servo
//        collectorServo.setDirection(Servo.Direction.FORWARD);
    }
}