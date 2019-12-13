package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.controllers.*;
import frc.robot.util.*;

public class Robot extends TimedRobot {
  public RobotController robotController;
  public Joystick joy = new Joystick(Context.joystickID);

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
    Context.robotController.autoDrive.loop((System.currentTimeMillis() - origTime)/1000);
  }

  @Override
  public void teleopInit() {
    Context.robotController.drivetrain.resetEncoders();
  }

  @Override
  public void teleopPeriodic() {
    double linearPower = Deadband.handle(joy.getRawAxis(1), Context.joystickMaxDeadband);
    double turnPower = Deadband.handle(joy.getRawAxis(4), Context.joystickMaxDeadband);
    
    // System.out.println("Heading: " + Context.robotController.navX.getHeading());

    Context.robotController.drivetrain.arcadeDrive(linearPower, turnPower);
  }
}
