package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.util.*;

public class Robot extends TimedRobot {
  public Joystick joy = HardwareFactory.newJoystick(0);

  CANSparkMax lm1 = HardwareFactory.newCANSparkMax(0, MotorType.kBrushless);
  CANSparkMax lm2 = HardwareFactory.newCANSparkMax(1, MotorType.kBrushless);
  CANSparkMax rm1 = HardwareFactory.newCANSparkMax(2, MotorType.kBrushless);
  CANSparkMax rm2 = HardwareFactory.newCANSparkMax(3, MotorType.kBrushless);

  public double motorPower = 0.0;

  @Override
  public void robotInit() {
    
  }

  @Override
  public void robotPeriodic() {
    
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {
    
  }

  @Override
  public void teleopInit() {
    
  }

  @Override
  public void teleopPeriodic() {
    double leftPower = -joy.getRawAxis(0) + joy.getRawAxis(1);
    double rightPower = -joy.getRawAxis(0) - joy.getRawAxis(1);

    lm1.set(leftPower);
    lm2.set(leftPower);
    rm1.set(rightPower);
    rm2.set(rightPower);
  }
}
