package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drivetrain
{
    public final double clickToCm = 2*Math.PI; // 10*3.14159263579/5

    public double startPosLeft = 0;
    public double startPosRight = 0;
    public double pastLeftDist = 0;
    public double pastRightDist = 0;
    public long pastTime;
    public CANSparkMax motor1;
    public CANSparkMax motor2;
    public CANSparkMax motor3;
    public CANSparkMax motor4;
    public PID leftDrivePID = new PID(0.09, 0, 0);
    public PID rightDrivePID = new PID(0.09, 0, 0);
    
    public Drivetrain ()
    {
        motor1 = new CANSparkMax(1, MotorType.kBrushless);
        motor2 = new CANSparkMax(2, MotorType.kBrushless);
        motor3 = new CANSparkMax(3, MotorType.kBrushless);
        motor4 = new CANSparkMax(4, MotorType.kBrushless);

        pastTime = System.currentTimeMillis();
    }

    public void tankDrivePID (double leftGoalPower, double rightGoalPower){
        double deltaTime = (double)(System.currentTimeMillis() - pastTime);

        double leftDistTraveled = getLeftDist() - pastLeftDist;
        double leftVelocity = leftDistTraveled/deltaTime;
        double leftPower = leftDrivePID.update(leftGoalPower, leftVelocity, deltaTime);

        double rightDistTraveled = getRightDist() - pastRightDist;
        double rightVelocity = rightDistTraveled/deltaTime;
        double rightPower = rightDrivePID.update(rightGoalPower, rightVelocity, deltaTime);

        System.out.println("Left Power: " + leftPower + " ; Right Power: " + rightPower);

        tankDrive(leftPower, rightPower);

        pastTime = System.currentTimeMillis();
        pastLeftDist = getLeftDist();
        pastRightDist = getRightDist();
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
        double rawCount2 = (motor3.getEncoder().getPosition() + motor4.getEncoder().getPosition())/2 - startPosRight;
        return clickToCm * rawCount2;
    }
}