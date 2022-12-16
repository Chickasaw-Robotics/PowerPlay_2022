package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.opmodes.ScanAndPark.state.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

import java.util.List;

@Autonomous(name="ScanAndPark", group="Autonomous")
public class ScanAndPark extends OpMode {
    // Load the pre-made tensorflow model for image detection
    private static final String TFOD_MODEL_ASSET = "PowerPlay.tflite";

    // Define vuforia license key (to allow us to use vision handling)
    private static final String VUFORIA_KEY = "Ack9T2z/////AAABmTU1HN6i6UHMtnBk7fz1mPxcgPOSB6zIPE8iO090/+v8GshY5o1fxKkmYh5Lj28jRhD59uyd22bM8aVhTCf7F+vIwWK3pgEMUqvtiFbMxaI3LG7JJyMsLcw+Ez2Iwc8LL+VsoquP2nlyUnAIsJQxQJ2PcAIbCQPHDuLuaUku/PajvFiLifVQXpMYTYIeINxZK8OW/bidHE8GHAuxDPoxNeqBe3oMzsOYn1VLm3eDrTO4KmvU6YMDkn10TAfiywR0Sa3/lNO75zKN8QD9fPLTBFBGE7aSAZhh96VxI93NGATd6pm8ZbS92KSbaK1OcUR9OJg8EeGo3jKzO3m408Pe02gq6xj+pbt5jWKjrhAJt88n";

    // Used to store localized vuforia engine
    private VuforiaLocalizer vuforia;

    // Used to store tensorflow object detection engine
    private TFObjectDetector tfod;

    // Create the labels for image detection
    private static final String[] LABELS = {
        "1 Bolt",
        "2 Bulb",
        "3 Panel"
    };

    // Declare states for the switch statement
    public state current_state;
    public int zone = 0;
    public enum state {
        initialize,
        scan,
        drive_forward,
        park1,
        park2,
        park3,
        terminate
    }

    public void init() {
        // Initialize robot hardware
        Subsystem.robot.init(hardwareMap);
        Subsystem.robot.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Subsystem.robot.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Initialize vision engine
        initVuforia();
        initTfod();

        // Set the starting state
        current_state = initialize;
    }

    public void loop() {
        // Update telemetry for debugging
        telemetry.addData("Auto State: ", current_state);
        telemetry.addData("Collector Servo Target", Subsystem.robot.collectorServo.getPosition());
        telemetry.addData("Right Motor Command", Subsystem.right_motor_command);
        telemetry.addData("Left Motor Command", Subsystem.left_motor_command);
        telemetry.addData("Lift Motor Command", Subsystem.lift_motor_command);
        telemetry.addData("Right Motor Counts: ", Subsystem.robot.rightMotor.getCurrentPosition());
        telemetry.addData("Left Motor Counts: ", Subsystem.robot.leftMotor.getCurrentPosition());
        telemetry.update();

        // Write objects detected to the telemetry
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                telemetry.addData("# Objects Detected", updatedRecognitions.size());

                // step through the list of recognitions and display image position/size information for each one
                // Note: "Image number" refers to the randomized image orientation/number
                for (Recognition recognition : updatedRecognitions) {
                    double col = (recognition.getLeft() + recognition.getRight()) / 2 ;
                    double row = (recognition.getTop()  + recognition.getBottom()) / 2 ;
                    double width  = Math.abs(recognition.getRight() - recognition.getLeft()) ;
                    double height = Math.abs(recognition.getTop()  - recognition.getBottom()) ;

                    telemetry.addData(""," ");
                    telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100 );
                    telemetry.addData("- Position (Row/Col)","%.0f / %.0f", row, col);
                    telemetry.addData("- Size (Width/Height)","%.0f / %.0f", width, height);
                }
                telemetry.update();
            }
        }

        // TODO: Fix the cases of the switch statement using this skeleton
        switch (current_state) {
            case initialize:
                current_state = scan;
                break;

            case scan:
                if (false /* Zone 1 */)
                    zone = 1;
                else if (false /* Zone 2 */)
                    zone = 2;
                else if (false /* Zone 3 */)
                    zone = 3;
                current_state = drive_forward;
                break;

            case drive_forward:
                if (zone == 1)
                    current_state = park1;
                else if (zone == 2)
                    current_state = park2;
                else if (zone == 3)
                    current_state = park3;

            case park1:
                current_state = terminate;
                break;

            case park2:
                current_state = terminate;
                break;

            case park3:
                current_state = terminate;

            case terminate:
                DriveTrain.stop();
                break;
        }
    }

    private void initVuforia() {
        // Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.75f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);

        // Use loadModelFromAsset() if the TF Model is built in as an asset by Android Studio
        // Use loadModelFromFile() if you have downloaded a custom team model to the Robot Controller's FLASH.
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
        // tfod.loadModelFromFile(TFOD_MODEL_FILE, LABELS);
    }
}
