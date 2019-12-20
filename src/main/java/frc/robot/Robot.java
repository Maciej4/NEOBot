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
    // Context.robotController.autoDrive.loop((System.currentTimeMillis() - origTime)/1000);
  }

  @Override
  public void teleopInit() {
    Context.robotController.drivetrain.resetEncoders();
  }

  @Override
  public void teleopPeriodic() {
    // double maxPower = 0.5;
    // double maxTurn = 0.5;

    // double linearPower = Deadband.handle(joy.getRawAxis(1), Context.joystickMaxDeadband);
    // double turnPower = Deadband.handle(joy.getRawAxis(4), Context.joystickMaxDeadband);
    // Context.robotController.antenna.loop();

    // boolean wKeyState = Context.robotController.antenna.getWKey();

    // System.out.println("W Key State: " + wKeyState);

    // System.out.println("Power: " + (maxPower * Context.robotController.antenna.getPower()) + " ; Turn: " + (maxTurn * Context.robotController.antenna.getTurn()));

    // double linearPower = maxPower * Context.robotController.antenna.getPower();
    // double turnPower = maxTurn * Context.robotController.antenna.getTurn();

    // if (wKeyState)
    // {
    //   turnPower = 0.5;
    // }
    
    // System.out.println("Heading: " + Context.robotController.navX.getHeading());

    // Context.robotController.drivetrain.arcadeDrive(linearPower, turnPower);

    Context.robotController.drivetrain.arcadeDrive(0, 0);
  }
}
