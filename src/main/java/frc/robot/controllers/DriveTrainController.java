package frc.robot.controllers;

import frc.robot.util.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

public class DriveTrainController {

    public TalonFX leftA;
    public TalonFX leftB;
    
    public TalonFX rightA;
    public TalonFX rightB;

    PID leftPID;
    PID rightPID;

    double leftDesired = 0;
    double rightDesired = 0;

    double leftSpeed = 0;
    double rightSpeed = 0;

    double lastTime = 0;
    double currentTime = 0;
    double dt = 0;

    final double kP = 0;
    final double kI = 0;
    final double kD = 0;

    boolean reverseDirection;

    final double frictionBound = 0.5; //minimum PWM input required to overcome friction
    final double powerToSpeedRatio = 0.5; //PWM input converted to corresponding final speed of motor

    public DriveTrainController(boolean reverseDirection_) {
        leftA = HardwareFactory.newTalonFX(Context.leftMotor1ID);
        leftB = HardwareFactory.newTalonFX(Context.leftMotor2ID);
        
        rightA = HardwareFactory.newTalonFX(Context.rightMotor1ID);
        rightB = HardwareFactory.newTalonFX(Context.rightMotor2ID);
        
        leftPID = new PID(kP, kI, kD);
        rightPID = new PID(kP, kI, kD);

        reverseDirection = reverseDirection_;

        lastTime = System.currentTimeMillis();
        currentTime = System.currentTimeMillis();

    }

    public void setLeftSpeed(double speed) {
        if(!reverseDirection) {
            speed = -speed;
        }

        leftDesired = speed;
    }

    public void setRightSpeed(double speed) {
        if(reverseDirection) {
            speed = -speed;
        }

        rightDesired = speed;
    }

    public void setDesired(double leftSpeed, double rightSpeed) {
        setLeftSpeed(leftSpeed);
        setRightSpeed(rightSpeed);
    }

    public void init() {
        //Resets changing objects to prevent time issues/PID jerking
        
        leftPID = new PID(kP, kI, kD);
        rightPID = new PID(kP, kI, kD);

        lastTime = System.currentTimeMillis();
        currentTime = System.currentTimeMillis();
    }

    public void loop() {
        leftSpeed = leftA.getSelectedSensorVelocity()/Context.TALONFX_CPR * Context.WHEEL_RADIUS * 10;
        rightSpeed = rightA.getSelectedSensorVelocity()/Context.TALONFX_CPR * Context.WHEEL_RADIUS * 10;

        lastTime = currentTime;
        currentTime = System.currentTimeMillis()/1000.0; //current time in seconds, double

        dt = currentTime - lastTime;

        double nextLeftSpeed = leftPID.update(leftDesired, leftSpeed, dt);
        double nextRightSpeed = rightPID.update(rightDesired, rightSpeed, dt);

        double leftPower = powerToSpeed(nextLeftSpeed);
        double rightPower = powerToSpeed(nextRightSpeed);

        leftA.set(ControlMode.PercentOutput, leftPower);
        leftB.set(ControlMode.PercentOutput, leftPower);

        rightA.set(ControlMode.PercentOutput, rightPower);
        rightB.set(ControlMode.PercentOutput, rightPower);
    }

    public double powerToSpeed(double speed) {
        return Math.signum(speed) * frictionBound + speed / powerToSpeedRatio;
    }

}