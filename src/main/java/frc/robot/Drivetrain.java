package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drivetrain
{
    public final double clickToCm = 10*3.14159263579/5;

    public double startPosLeft = 0;
    public double startPosRight = 0;

    public CANSparkMax motor1;
    public CANSparkMax motor2;
    public CANSparkMax motor3;
    public CANSparkMax motor4;
    
    public Drivetrain ()
    {
        motor1 = new CANSparkMax(1, MotorType.kBrushless);
        motor2 = new CANSparkMax(2, MotorType.kBrushless);
        motor3 = new CANSparkMax(3, MotorType.kBrushless);
        motor4 = new CANSparkMax(4, MotorType.kBrushless);
    }

    public void tankDrive(double leftPower, double rightPower)
    {
        motor1.set(leftPower);
        motor2.set(leftPower);
        motor3.set(rightPower);
        motor4.set(rightPower);
    }

    public void resetEncoders()
    {
        startPosLeft = (motor1.getEncoder().getPosition() + motor2.getEncoder().getPosition())/2;
        startPosRight = (motor3.getEncoder().getPosition() + motor4.getEncoder().getPosition())/2;
    }

    public double getLeftDist()
    {
        //10 cm wheel diameter
        double rawCount = (motor1.getEncoder().getPosition() + motor2.getEncoder().getPosition())/2 - startPosLeft;
        return clickToCm * rawCount;
    }

    public double getRightDist()
    {
        double rawCount = (motor3.getEncoder().getPosition() + motor4.getEncoder().getPosition())/2 - startPosRight;
        return clickToCm * rawCount;
    }
}