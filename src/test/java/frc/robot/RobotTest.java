package frc.robot;

import org.junit.*;
// import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
// import edu.wpi.first.wpilibj.*;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.controllers.Drivetrain;
import frc.robot.controllers.NavX;
import frc.robot.controllers.RobotController;
import frc.robot.controllers.ZMQServer;
import frc.robot.util.Context;

public class RobotTest
{
    // Declaration of variables used to store inputs to motors
    public double lm1Power = 0;
    public double lm2Power = 0;
    public double rm1Power = 0;
    public double rm2Power = 0;

    // Creation of mock sparks and putting them into drivetrain
    public CANSparkMax lm1 = mock(CANSparkMax.class);
    public CANSparkMax lm2 = mock(CANSparkMax.class);
    public CANSparkMax rm1 = mock(CANSparkMax.class);
    public CANSparkMax rm2 = mock(CANSparkMax.class);

    public CANEncoder lm1e = mock(CANEncoder.class);
    public CANEncoder lm2e = mock(CANEncoder.class);
    public CANEncoder rm1e = mock(CANEncoder.class);
    public CANEncoder rm2e = mock(CANEncoder.class);

    public Drivetrain drivetrain = new Drivetrain(lm1, lm2, rm1, rm2);

    public NavX navX = mock(NavX.class);

    public Joystick joy = mock(Joystick.class);
    
    public ZMQServer zmqServer;

    public double driveX = 0.0;
    public double driveY = 0.0;

    @Before
    public void setup() {
        Context.robotController = mock(RobotController.class);

        doAnswer(invocation -> {
            Double power = invocation.getArgument(0, Double.class);
            lm1Power = power.doubleValue();
            return null;
        }).when(lm1).set(any(Double.class));

        doAnswer(invocation -> {
            return lm1e;
        }).when(lm1).getEncoder();

        doAnswer(invocation -> {
            return 0.0;
        }).when(lm1e).getPosition();

        
        doAnswer(invocation -> {
            Double power = invocation.getArgument(0, Double.class);
            lm2Power = power.doubleValue();
            return null;
        }).when(lm2).set(any(Double.class));

        doAnswer(invocation -> {
            return lm2e;
        }).when(lm2).getEncoder();

        doAnswer(invocation -> {
            return 0.0;
        }).when(lm2e).getPosition();


        doAnswer(invocation -> {
            Double power = invocation.getArgument(0, Double.class);
            rm1Power = power.doubleValue();
            return null;
        }).when(rm1).set(any(Double.class));

        doAnswer(invocation -> {
            return rm1e;
        }).when(rm1).getEncoder();

        doAnswer(invocation -> {
            return 0.0;
        }).when(rm1e).getPosition();


        doAnswer(invocation -> {
            Double power = invocation.getArgument(0, Double.class);
            rm2Power = power.doubleValue();
            return null;
        }).when(rm2).set(any(Double.class));

        doAnswer(invocation -> {
            return rm2e;
        }).when(rm2).getEncoder();

        doAnswer(invocation -> {
            return 0.0;
        }).when(rm2e).getPosition();


        Context.robotController.drivetrain = drivetrain;


        doAnswer(invocation -> {
            return zmqServer.unityPacket.heading;
        }).when(navX).getHeading();

        Context.robotController.navX = navX;

        
        doAnswer(invocation -> {
            return zmqServer.unityPacket.driveX;
        }).when(joy).getRawAxis(0);

        doAnswer(invocation -> {
            return zmqServer.unityPacket.driveY;
        }).when(joy).getRawAxis(1);
    }

    @Test
    public void simulateRobot()
    {
        // System.out.println("Testing testing 123");
        Robot robot = new Robot();
        robot.joy = joy;
        robot.robotController = Context.robotController;

        zmqServer = new ZMQServer();
        System.out.println("Awaiting communication from Unity (ctrl c to kill)...");
        zmqServer.awaitComms();
        zmqServer.start();

        System.out.println("Starting teleop...");
        robot.teleopInit();
        
        System.out.println("Teleop started");
        long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis() < startTime + 20000) {
            long loopStartTime = System.currentTimeMillis();

            robot.teleopPeriodic();

            zmqServer.robotPacket.leftPower = lm1Power;
            zmqServer.robotPacket.rightPower = rm1Power;

            int loopLengthMillis = (int)(System.currentTimeMillis() - loopStartTime);
            if(loopLengthMillis < 20) {
                try {
					Thread.sleep(20 - loopLengthMillis);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            } else {
                System.out.println("Teleop loop time overrun by: " + (loopLengthMillis - 20) + " ms");
            }
        }
        System.out.println("End of teleop");

        System.out.println("Interrupting ZMQ thread...");
        zmqServer.interrupt();
        System.out.println("Finishing up...");
        robot.close();
    }
}