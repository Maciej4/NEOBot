package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.util.*;

public class Robot extends TimedRobot {

  // Keyboard:
  // W / Up    : Axis 1 = 1
  // A / Left  : Axis 0 = -1
  // S / Down  : Axis 1 = -1
  // D / Right : Axis 0 = 1

  // Xbox Controller:
  // horizontal axis on left stick is rawAxis id 0, vertical axis is 1

  public Joystick joy = HardwareFactory.newJoystick(0);

  // Right side : 1, 2
  // Left side  : 3, 4

  public TalonFX rm1 = HardwareFactory.newTalonFX(1);
  public TalonFX rm2 = HardwareFactory.newTalonFX(2);
  public TalonFX lm1 = HardwareFactory.newTalonFX(3);
  public TalonFX lm2 = HardwareFactory.newTalonFX(4);

  @Override
  public void robotInit() {
    //t.start();
  }

  @Override
  public void robotPeriodic() {
    // Runs repeatedly while robot is on
  }

  @Override
  public void autonomousInit() {
    // Runs once when autonomous is enabled
  }

  @Override
  public void autonomousPeriodic() {
    // Runs repeatedly while robot is autonomous
  }

  @Override
  public void teleopInit() {
    //t.start();
  }

  @Override
  public void teleopPeriodic() {

    //rightPower : + is forward
    //leftPower  : - is forward

    //time = t.getS();
    
    double leftPower = -joy.getRawAxis(1) - joy.getRawAxis(0);
    double rightPower = joy.getRawAxis(1) - joy.getRawAxis(0);

    lm1.set(ControlMode.PercentOutput, leftPower);
    lm2.set(ControlMode.PercentOutput, leftPower);
    rm1.set(ControlMode.PercentOutput, rightPower);
    rm2.set(ControlMode.PercentOutput, rightPower);
  }
}
