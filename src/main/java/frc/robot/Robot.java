package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.controllers.*;
import frc.robot.util.*;

public class Robot extends TimedRobot {
  public RobotController robotController;
  public Joystick joy = new Joystick(0);

  public double origTime;

  @Override
  public void robotInit() {
    robotController = new RobotController();
  }

  @Override
  public void autonomousInit() {
    origTime = System.currentTimeMillis();
  }

  @Override
  public void autonomousPeriodic() {
    Context.robotController.autoDrive.loop((origTime - System.currentTimeMillis())/5);
  }

  @Override
  public void teleopInit() {
    Context.robotController.drivetrain.resetEncoders();
  }

  @Override
  public void teleopPeriodic() {
    double linearPower = Deadband.handle(joy.getRawAxis(1), 0.05);
    double turnPower = Deadband.handle(joy.getRawAxis(4), 0.05);

    Context.robotController.drivetrain.arcadeDrive(linearPower, turnPower);
  }
}
