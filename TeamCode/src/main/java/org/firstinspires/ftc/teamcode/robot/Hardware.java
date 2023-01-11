package org.firstinspires.ftc.teamcode.robot;

// Import FIRST Libraries
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

// Import Team Specific Libraries
import org.firstinspires.ftc.teamcode.utils.Constants;

public class Hardware {
    // Declare hardware variables
    public Servo collectorServo;
    public Servo turnCollectorServo;
    public DcMotor rightMotor;
    public DcMotor leftMotor;
    public DcMotor liftMotor;

    public void init (HardwareMap map) {
        // Tie hardware variables to their ports
        turnCollectorServo = map.servo.get(Constants.TURN_COLLECTOR_SERVO);
        collectorServo = map.servo.get(Constants.COLLECTOR_SERVO_PORT);
        rightMotor = map.dcMotor.get(Constants.RIGHT_MOTOR_PORT);
        leftMotor = map.dcMotor.get(Constants.LEFT_MOTOR_PORT);
        liftMotor = map.dcMotor.get(Constants.LIFT_MOTOR_PORT);

        // Configure robot hardware
        configureMotors();
        configureServos();
    }

    // Initialize all the motor settings.
    private void configureMotors() {
        // Reset encoder counts
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set the motors to run using the encoders
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Set the direction of the motors
        rightMotor.setDirection(DcMotor.Direction.FORWARD); //Flipped because drivetrain changed and motors attached wrong
        leftMotor.setDirection(DcMotor.Direction.REVERSE); //Flipped because drivetrain changed and motors attached wrong
        liftMotor.setDirection(DcMotor.Direction.FORWARD);

        // Set motors to brake if not running
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // Initialize all the servo settings
    private void configureServos() {
        // Set the direction of the servo
        collectorServo.setDirection(Servo.Direction.FORWARD);
        turnCollectorServo.setDirection(Servo.Direction.FORWARD);
    }
}