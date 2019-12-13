package frc.robot.util;

import frc.robot.controllers.RobotController;

public class Context {
    //----- Drivetrain Values -----
    public static final int leftMotor1ID = 1;
    public static final int leftMotor2ID = 2;
    public static final int rightMotor1ID = 3;
    public static final int rightMotor2ID = 4;

    // Diameter of wheel (10 cm) * Pi (3.14) * Clicks per rev (0.2)
    public static final double driveClickToCm = 2*Math.PI;
    
    //----- Human Input Device Values -----
    public static final int joystickID = 0;
    public static final double joystickMaxDeadband = 0.05;

    //----- Robotcontroller Static Reference -----
    public static RobotController robotController;
}