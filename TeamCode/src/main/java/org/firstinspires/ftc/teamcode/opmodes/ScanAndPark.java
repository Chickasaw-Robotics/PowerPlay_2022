package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.opmodes.ScanAndPark.state.*;
import static org.firstinspires.ftc.teamcode.subsystems.Subsystem.robot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.utils.Constants;

import java.util.List;

@Autonomous(name="ScanAndPark", group="Autonomous")
public class ScanAndPark extends OpMode {
    // Load the pre-made tensorflow model for image detection
    private static final String TFOD_MODEL_ASSET = "FC_POWERPLAY_V3.tflite";

    // Define vuforia license key (to allow us to use vision handling)
    private static final String VUFORIA_KEY = "Ack9T2z/////AAABmTU1HN6i6UHMtnBk7fz1mPxcgPOSB6zIPE8iO090/+v8GshY5o1fxKkmYh5Lj28jRhD59uyd22bM8aVhTCf7F+vIwWK3pgEMUqvtiFbMxaI3LG7JJyMsLcw+Ez2Iwc8LL+VsoquP2nlyUnAIsJQxQJ2PcAIbCQPHDuLuaUku/PajvFiLifVQXpMYTYIeINxZK8OW/bidHE8GHAuxDPoxNeqBe3oMzsOYn1VLm3eDrTO4KmvU6YMDkn10TAfiywR0Sa3/lNO75zKN8QD9fPLTBFBGE7aSAZhh96VxI93NGATd6pm8ZbS92KSbaK1OcUR9OJg8EeGo3jKzO3m408Pe02gq6xj+pbt5jWKjrhAJt88n";

    // Used to store localized vuforia engine
    private VuforiaLocalizer vuforia;

    // Used to store tensorflow object detection engine
    private TFObjectDetector tfod;

    // Create the labels for image detection
    private static final String[] LABELS = {
        "Bolt",
        "Circuit",
        "Thingy"
    };

    // Declare states for the switch statement
    public state current_state;
    String label = "";
    public enum state {
        initialize,
        scan,
        z1_drive1,
        z1_turn1,
        z1_drive2,
        z1_turn2,
        z1_park,
        z2_park,
        z3_drive1,
        z3_turn1,
        z3_drive2,
        z3_turn2,
        z3_park,
        terminate
    }

    public void init() {
        // Initialize robot hardware
        robot.init(hardwareMap);

        // Initialize vision engine
        initVuforia();
        initTfod();

        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can increase the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 16/9).
            tfod.setZoom(1.0, 16.0/9.0);
        }

        // Set the starting state
        current_state = initialize;
    }

    public void loop() {
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                telemetry.addData("# Objects Detected", updatedRecognitions.size());

                // step through the list of recognitions and display image position/size information for each one
                // Note: "Image number" refers to the randomized image orientation/number
                for (Recognition recognition : updatedRecognitions) {
                    label = recognition.getLabel();

                    telemetry.addData(""," ");
                    telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100 );
                }
                telemetry.addData("State:", current_state);
                telemetry.addData("Label:", label);
                telemetry.update();
            }
        }


        switch (current_state) {
            case initialize:
                current_state = scan;
                break;

            case scan:
                switch (label) {
                    case "Circuit":
                        telemetry.addLine("CIRCUIT LABEL CHOSEN");
                        telemetry.update();
                        current_state = z1_drive1;
                        break;
                    case "Thingy":
                        telemetry.addLine("THINGY LABEL CHOSEN");
                        telemetry.update();
                        current_state = z2_park;
                        break;
                    case "Bolt":
                        telemetry.addLine("BOLT LABEL CHOSEN");
                        telemetry.update();
                        current_state = z3_drive1;
                        break;
                }
                break;

            case z1_drive1:
                DriveTrain.driveToPosition(Constants.AUTO_SPEED, 4, 4);
                if(!DriveTrain.isMovingToPosition()) {
                    robot.leftMotor.setPower(0);
                    robot.rightMotor.setPower(0);
                    current_state = z1_turn1;
                }
                break;

            case z1_turn1:
                DriveTrain.turnToPosition(Constants.AUTO_SPEED,-90);
                if(!DriveTrain.isMovingToPosition()) {
                    robot.leftMotor.setPower(0);
                    robot.rightMotor.setPower(0);
                    current_state = z1_drive2;
                }
                break;

            case z1_drive2:
                DriveTrain.driveToPosition(Constants.AUTO_SPEED,19,19);
                if(!DriveTrain.isMovingToPosition()) {
                    robot.leftMotor.setPower(0);
                    robot.rightMotor.setPower(0);
                    current_state = z1_turn2;
                }
                break;

            case z1_turn2:
                DriveTrain.turnToPosition(Constants.AUTO_SPEED,90);
                if(!DriveTrain.isMovingToPosition()) {
                    robot.leftMotor.setPower(0);
                    robot.rightMotor.setPower(0);
                    current_state = z1_park;
                }
                break;

            case z1_park:

            case z3_park:
                DriveTrain.driveToPosition(Constants.AUTO_SPEED, 24, 24);
                if(!DriveTrain.isMovingToPosition()) {
                    robot.leftMotor.setPower(0);
                    robot.rightMotor.setPower(0);
                    current_state = terminate;
                }
                break;

            case z2_park:
                DriveTrain.driveToPosition(Constants.AUTO_SPEED, 27, 27);
                if(!DriveTrain.isMovingToPosition()) {
                    robot.leftMotor.setPower(0);
                    robot.rightMotor.setPower(0);
                    current_state = terminate;
                }
                break;

            case z3_drive1:
                DriveTrain.driveToPosition(Constants.AUTO_SPEED, 4, 4);
                if(!DriveTrain.isMovingToPosition()) {
                    robot.leftMotor.setPower(0);
                    robot.rightMotor.setPower(0);
                    current_state = z3_turn1;
                }
                break;

            case z3_turn1:
                DriveTrain.turnToPosition(Constants.AUTO_SPEED, 110);
                if(!DriveTrain.isMovingToPosition()) {
                    robot.leftMotor.setPower(0);
                    robot.rightMotor.setPower(0);
                    current_state = z3_drive2;
                }
                break;

            case z3_drive2:
                DriveTrain.driveToPosition(Constants.AUTO_SPEED,23,23);
                if(!DriveTrain.isMovingToPosition()) {
                    robot.leftMotor.setPower(0);
                    robot.rightMotor.setPower(0);
                    current_state = z3_turn2;
                }
                break;

            case z3_turn2:
                DriveTrain.turnToPosition(Constants.AUTO_SPEED,-90);
                if(!DriveTrain.isMovingToPosition()) {
                    robot.leftMotor.setPower(0);
                    robot.rightMotor.setPower(0);
                    current_state = z3_park;
                }
                break;

            case terminate:
                DriveTrain.stop();
                break;
        }
    }

    public void stop() {
        // Stop all subsystems
        DriveTrain.stop();
        Lift.stop();

        // Update telemetry
        telemetry.addData("Status:", "Auto is stopped");
        telemetry.update();
    }

    private void initVuforia() {
        // Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

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

        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }
}
