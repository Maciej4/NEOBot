package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.controllers.*;
import frc.robot.util.*;

public class Robot extends TimedRobot {
  // public RobotController robotController;
  public Joystick joy = HardwareFactory.newJoystick(0);

  // public double origTime;
  // public double robotStartTime;

  CANSparkMax lm1 = HardwareFactory.newCANSparkMax(0, MotorType.kBrushless);
  CANSparkMax lm2 = HardwareFactory.newCANSparkMax(1, MotorType.kBrushless);
  CANSparkMax rm1 = HardwareFactory.newCANSparkMax(2, MotorType.kBrushless);
  CANSparkMax rm2 = HardwareFactory.newCANSparkMax(3, MotorType.kBrushless);

  public double motorPower = 0.0;

  @Override
  public void robotInit() {
    // robotController = new RobotController();
    // robotStartTime = System.currentTimeMillis()/1000.0;
  }

  @Override
  public void robotPeriodic() {
    // Context.robotController.autoDrive.loop(System.currentTimeMillis()/1000.0-robotStartTime);
  }

  // @Override
  // public void autonomousInit() {
  //   origTime = System.currentTimeMillis();
  //   Context.robotController.autoDrive.startSpline();
  // }

  // @Override
  // public void autonomousPeriodic() {
  //   Context.robotController.autoDrive.loop((System.currentTimeMillis() - origTime)/1000);
  // }

  @Override
  public void teleopInit() {
    // Context.robotController.drivetrain.resetEncoders();
  }

  @Override
  public void teleopPeriodic() {
    double leftPower = -joy.getRawAxis(0) + joy.getRawAxis(1);
    double rightPower = -joy.getRawAxis(0) - joy.getRawAxis(1);

    lm1.set(leftPower);
    lm2.set(leftPower);
    rm1.set(rightPower);
    rm2.set(rightPower);
    

    // Context.robotController.drivetrain.arcadeDrive(joy.getRawAxis(1), joy.getRawAxis(0));
  }
}
