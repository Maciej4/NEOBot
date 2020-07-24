package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.util.*;

public class Robot extends TimedRobot {
  public Joystick joy = HardwareFactory.newJoystick(0);

  public TalonFX lm1 = HardwareFactory.newTalonFX(1);
  public TalonFX lm2 = HardwareFactory.newTalonFX(2);
  public TalonFX rm1 = HardwareFactory.newTalonFX(3);
  public TalonFX rm2 = HardwareFactory.newTalonFX(4);
  // AHRS gyro = HardwareFactory.newAHRS();
  // public DoubleSolenoid piston = HardwareFactory.newDoubleSolenoid(0, 1);

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
    // System.out.println("");
  }

  @Override
  public void teleopPeriodic() {
    double leftPower = -joy.getRawAxis(0) + joy.getRawAxis(1);
    double rightPower = -joy.getRawAxis(0) - joy.getRawAxis(1);

    // System.out.println(gyro.getAngle());

    // if(joy.getRawButton(1)) {
    //   piston.set(Value.kForward);
    // } else {
    //   piston.set(Value.kReverse);
    // }

    lm1.set(ControlMode.PercentOutput, leftPower);
    lm2.set(ControlMode.PercentOutput, leftPower);
    rm1.set(ControlMode.PercentOutput, rightPower);
    rm2.set(ControlMode.PercentOutput, rightPower);
  }
}
