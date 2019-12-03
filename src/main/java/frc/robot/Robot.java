package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
  Drivetrain drivetrain = new Drivetrain();
  public Joystick joy = new Joystick(0);
  //public PID leftPID = new PID(0.03,0,0.001);
  //public PID rightPID = new PID(0.03,0,0.001);
  public double stepSizeMaster = 0.05;
  public LinearAccelerator leftLinAccel = new LinearAccelerator(stepSizeMaster);
  public LinearAccelerator rightLinAccel = new LinearAccelerator(stepSizeMaster);
  //public RoadRunnerSandbox game = new RoadRunnerSandbox();

  @Override
  public void robotInit() {

  }

  @Override
  public void autonomousInit() {
  
  }

  @Override
  public void autonomousPeriodic() {
  
  }

  @Override
  public void teleopInit() {
    drivetrain.resetEncoders();
  }

  public double handleDeadband(double input, double limit) {
    if(Math.abs(input) < limit)
    {
      input = 0;
    }
    return input;
  }

  @Override
  public void teleopPeriodic() {
    double leftGoal = handleDeadband(-joy.getRawAxis(1), 0.05);
    double rightGoal = handleDeadband(joy.getRawAxis(5), 0.05);
    //double leftActual = ((motor1.getEncoder().getVelocity() + motor2.getEncoder().getVelocity())/2)/5000;
    //double rightActual = ((motor3.getEncoder().getVelocity() + motor4.getEncoder().getVelocity())/2)/5000;
    double leftPower = leftLinAccel.update(leftGoal);
    double rightPower = rightLinAccel.update(rightGoal);
    //double leftPower = 20*leftPID.Update(leftGoal, leftActual, 0.01);
    //double rightPower = 20*rightPID.Update(rightGoal, rightActual, 0.01);
    //double rightPower = 0;

    //System.out.println("-----------");
    //System.out.println("LeftGoal: " + leftGoal + " LeftVel:" + leftActual + " LeftPower" + leftPower);
    //System.out.println("RightGoal: " + leftGoal + "RightVel:" + rightActual + " RightPower" + rightPower);
    drivetrain.tankDrive(leftPower, rightPower);

    System.out.println("Left Pos: " + drivetrain.getLeftDist() + "; Right Pos: " + drivetrain.getRightDist());
  }
}
