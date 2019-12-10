package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
// import com.acmerobotics.roadrunner.path.Path;
// import com.acmerobotics.roadrunner.profile.MotionProfile;
// import com.acmerobotics.roadrunner.profile.MotionProfileGenerator;
// import com.acmerobotics.roadrunner.profile.MotionState;
// import com.acmerobotics.roadrunner.util.Angle;

public class Robot extends TimedRobot {
  Drivetrain drivetrain = new Drivetrain();
  public Joystick joy = new Joystick(0);
  //public PID leftPID = new PID(0.03,0,0.001);
  //public PID rightPID = new PID(0.03,0,0.001);
  public double stepSizeMaster = 0.05;
  public LinearAccelerator leftLinAccel = new LinearAccelerator(stepSizeMaster);
  public LinearAccelerator rightLinAccel = new LinearAccelerator(stepSizeMaster);
  public double origTime;
  // public AutoDrive autodrive = new AutoDrive();
  //public RoadRunnerSandbox game = new RoadRunnerSandbox();

  @Override
  public void robotInit() {

  }

  @Override
  public void autonomousInit() {
    origTime = System.currentTimeMillis();
  }

  @Override
  public void autonomousPeriodic() {
    // autodrive.loop((origTime - System.currentTimeMillis())/5);
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
    double leftGoal = handleDeadband(joy.getRawAxis(4), 0.05);
    double rightGoal = handleDeadband(joy.getRawAxis(1), 0.05);

    //System.out.println("Gamer");

    //double leftPower = leftLinAccel.update(leftGoal);
    //double rightPower = rightLinAccel.update(rightGoal);

    //double leftPower = 20*leftPID.Update(leftGoal, leftActual, 0.01);
    //double rightPowerTime = 20*rightPID.Update(rightGoal, rightActual, 0.01);
    //double rightPower = 0;

    //System.out.println("-----------");
    //System.out.println("LeftGoal: " + leftGoal + " LeftVel:" + leftActual + " LeftPower" + leftPower);
    //System.out.println("RightGoal: " + leftGoal + "RightVel:" + rightActual + " RightPower" + rightPower);
    
    // drivetrain.tankDrivePID(leftGoal, rightGoal);

    drivetrain.arcadeDrive(leftGoal, rightGoal);

    //System.out.println("Left Pos: " + drivetrain.getLeftDist() + "; Right Pos: " + drivetrain.getRightDist());
  }
}
