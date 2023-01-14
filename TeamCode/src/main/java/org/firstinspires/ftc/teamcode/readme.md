# Fried Circuits 2022-23
Welcome to the Fried Circuits codebase for the 2022-23 FTC season, PowerPlay.
In the following sections our codebase is described in addition to an explanation of how to run and make additions to it.

## Design Philosophy
The codebase follows an object oriented implementation. All the code related to a given subsystem is
contained within the appropriate file within the **subsystems** module. All physical connections are
defined within the **robot** module. All the robot hardware is defined within the *hardware.java*
class and all the controls are defined with the *controls.java* class. The **utils** module contains
all the constants used throughout the code so that they can be edited and updated in a single
location as opposed to having to find all usages of them. The **opmodes** module contains all the
code for the autonomous and teleop programs which makes use of the subsystem files to allow user
control in a clear to see way.

## File Structure
The majority of the team's codebase is located in the TeamCode Module under the java subfolder.
Shown below is a diagram of the primary folder structure:
```
org.firstinspires.ftc.teamcode
├── opmodes
│   ├── ScanSignal.java
│   ├── ScanAndPark.java
│   ├── Teleop.java
├── robot
│   ├── Controls.java
│   ├── Hardware.java
├── subsystems
│   ├── Collector.java
│   ├── DriveTrain.java
│   ├── Lift.java
│   ├── Subsystem.java
├── utils
│   ├── Constants.java
└── readme.md
```
In addition to this, all TensorFlow models are stored inside the FtcRobotController Module under the assets subfolder.

## Controls

### Autonomous
| Command    | Action                                                                  |
|------------|-------------------------------------------------------------------------|
| ScanSignal | Scans our custom signal sleeve and parks in the appropriate signal zone |
Our custom signal sleeve can be seen here:
![Custom Signal Sleeve](https://imgur.com/gallery/aC97Cix)

### Teleop
| Command     | Action                                                                            |
|-------------|-----------------------------------------------------------------------------------|
| ScanAndPark | Used for testing image recognition by posting all recognized objects to telemetry |
| Teleop      | Allows for control of the robot based on driver input as defined below            |
For the teleoperated control period we make use of two [Logitech F310](https://www.logitechg.com/en-us/products/gamepads/f310-gamepad.html#940-000110 "Logitech Webpage") controllers in Ports 0 and 1 of our driver station
![Logitech F310 Controller](https://www.logitechg.com/content/dam/gaming/en/products/f310/f310-gallery-1.png)

#### Driver 1 Controls
| Control                 | Action                     |
|-------------------------|----------------------------|
| Left Joystick (Y Axis)  | Left Side Base Tank Drive  |
| Right Joystick (Y Axis) | Right Side Base Tank Drive |

#### Driver 2 Controls
| Control                 | Action                      |
|-------------------------|-----------------------------|
| Left Joystick (Y Axis)  | Manually Raise/Lower Lift   |
| Right Joystick (X Axis) | Rotate Collector Left/Right |
| A                       | Lift Preset Ground          |
| B                       | Lift Preset Low             |
| Y                       | Lift Preset Mid             |
| Left Bumper             | Open Collector              |
| Right Bumper            | Close Collector             |

## Robot Map

### Motors
| Motor             | Configuration Name | Port |
|-------------------|--------------------|------|
| Left Drive Train  | left               | 0    |
| Right Drive Train | right              | 1    |
| Lift              | lift               | 2    |

### Servos
| Servo            | Configuration Name | Port |
|------------------|--------------------|------|
| Collector Grab   | collector          | 0    |
| Collector Rotate | turn_collector     | 1    |

### USB Devices
| USB Device           | Configuration Name | Port    |
|----------------------|--------------------|---------|
| Logitech C270 Camera | Webcam 1           | USB 3.0 |
