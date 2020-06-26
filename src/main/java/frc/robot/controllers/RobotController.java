package frc.robot.controllers;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.util.Context;
import frc.robot.util.HardwareFactory;

public class RobotController
{
    public Drivetrain drivetrain;
    // public AutoDrive autoDrive;
    public NavX navX;

    public CANSparkMax leftMotor1, leftMotor2, rightMotor1, rightMotor2;

    public RobotController () {
        leftMotor1 = HardwareFactory.newCANSparkMax(Context.leftMotor1ID, MotorType.kBrushless);
        leftMotor2 = HardwareFactory.newCANSparkMax(Context.leftMotor2ID, MotorType.kBrushless);
        rightMotor1 = HardwareFactory.newCANSparkMax(Context.rightMotor1ID, MotorType.kBrushless);
        rightMotor2 = HardwareFactory.newCANSparkMax(Context.rightMotor2ID, MotorType.kBrushless);
        
        drivetrain = new Drivetrain(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
        // autoDrive = new AutoDrive();
        navX = new NavX();
        
        Context.robotController = this;
    }
}