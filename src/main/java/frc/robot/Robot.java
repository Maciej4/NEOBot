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
    double maxPower = 0.5;
    double maxTurn = 0.5;
    double linearPower = 0;
    double turnPower = 0;
    boolean WKey = Context.robotController.zmqServer.unityPacket.WKey;
    boolean AKey = Context.robotController.zmqServer.unityPacket.AKey;
    boolean SKey = Context.robotController.zmqServer.unityPacket.SKey;
    boolean DKey = Context.robotController.zmqServer.unityPacket.DKey;

    System.out.println(
      "W key state = " + WKey + " ;" +
      "A key state = " + AKey + " ;" +
      "S key state = " + SKey + " ;" +
      "D key state = " + DKey + " ;"
    );
    
    // Linear Power from Keys
    if(WKey)
    {
      linearPower = maxPower;
    }
    else if (SKey)
    {
      linearPower = -maxPower;
    }

    // Turn Power from Keys
    if(AKey)
    {
      turnPower = maxTurn;
    }
    else if (DKey)
    {
      turnPower = -maxTurn;
    }

    Context.robotController.drivetrain.arcadeDrive(linearPower, turnPower);
  }
}
