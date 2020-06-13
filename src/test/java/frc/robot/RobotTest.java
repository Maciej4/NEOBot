package frc.robot;

import org.junit.*;
// import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
// import edu.wpi.first.wpilibj.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
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

    // Climber and telescope
    public CANSparkMax cm1 = mock(CANSparkMax.class);
    public CANSparkMax cm2 = mock(CANSparkMax.class);
    
    public CANEncoder cm1e = mock(CANEncoder.class);
    public CANEncoder cm2e = mock(CANEncoder.class);

    public CANSparkMax tel = mock(CANSparkMax.class);

    public CANEncoder tele = mock(CANEncoder.class);

    // Encoder interfaces
    public TalonSRX lei = mock(TalonSRX.class);
    public TalonSRX rei = mock(TalonSRX.class);

    // Indexing
    public TalonSRX itk = mock(TalonSRX.class);
    public CANSparkMax nmf = mock(CANSparkMax.class);
    public CANSparkMax omi = mock(CANSparkMax.class);

    public CANEncoder nmfe = mock(CANEncoder.class);
    public CANEncoder omie = mock(CANEncoder.class);

    // Shooter
    public TalonFX sm1 = mock(TalonFX.class);
    public TalonFX sm2 = mock(TalonFX.class);

    // Misc
    public NavX navX = mock(NavX.class);

    public Joystick joy = mock(Joystick.class);
    
    public ZMQServer zmqServer;

    @Before
    public void setup() {
        Context.robotController = mock(RobotController.class);
        

        // ---------- Drivetrain ----------
        doAnswer(invocation -> {
            zmqServer.robotPacket.leftDriveMotor1Power = invocation.getArgument(0, Double.class).doubleValue();
            return null;
        }).when(lm1).set(any(Double.class));

        doAnswer(invocation -> {
            return lm1e;
        }).when(lm1).getEncoder();

        doAnswer(invocation -> {
            return zmqServer.unityPacket.leftDriveMotor1Position;
        }).when(lm1e).getPosition();

        
        doAnswer(invocation -> {
            zmqServer.robotPacket.leftDriveMotor2Power = invocation.getArgument(0, Double.class).doubleValue();
            return null;
        }).when(lm2).set(any(Double.class));

        doAnswer(invocation -> {
            return lm2e;
        }).when(lm2).getEncoder();

        doAnswer(invocation -> {
            return zmqServer.unityPacket.leftDriveMotor2Position;
        }).when(lm2e).getPosition();


        doAnswer(invocation -> {
            zmqServer.robotPacket.rightDriveMotor1Power = invocation.getArgument(0, Double.class).doubleValue();
            return null;
        }).when(rm1).set(any(Double.class));

        doAnswer(invocation -> {
            return rm1e;
        }).when(rm1).getEncoder();

        doAnswer(invocation -> {
            return zmqServer.unityPacket.rightDriveMotor1Position;
        }).when(rm1e).getPosition();


        doAnswer(invocation -> {
            zmqServer.robotPacket.rightDriveMotor2Power = invocation.getArgument(0, Double.class).doubleValue();
            return null;
        }).when(rm2).set(any(Double.class));

        doAnswer(invocation -> {
            return rm2e;
        }).when(rm2).getEncoder();

        doAnswer(invocation -> {
            return zmqServer.unityPacket.rightDriveMotor2Position;
        }).when(rm2e).getPosition();


        Context.robotController.drivetrain = drivetrain;


        // ---------- Climber ----------
        doAnswer(invocation -> {
            zmqServer.robotPacket.coilMotor1Power = invocation.getArgument(0, Double.class).doubleValue();
            return null;
        }).when(cm1).set(any(Double.class));

        doAnswer(invocation -> {
            return cm1e;
        }).when(cm1).getEncoder();

        doAnswer(invocation -> {
            return zmqServer.unityPacket.coilMotor1Position;
        }).when(cm1e).getPosition();


        doAnswer(invocation -> {
            zmqServer.robotPacket.coilMotor2Power = invocation.getArgument(0, Double.class).doubleValue();
            return null;
        }).when(cm2).set(any(Double.class));

        doAnswer(invocation -> {
            return cm2e;
        }).when(cm2).getEncoder();

        doAnswer(invocation -> {
            return zmqServer.unityPacket.coilMotor2Position;
        }).when(cm2e).getPosition();
        

        doAnswer(invocation -> {
            zmqServer.robotPacket.telescopeMotorPower = invocation.getArgument(0, Double.class).doubleValue();
            return null;
        }).when(tel).set(any(Double.class));

        doAnswer(invocation -> {
            return tele;
        }).when(tel).getEncoder();

        doAnswer(invocation -> {
            return zmqServer.unityPacket.telescopeMotorPosition;
        }).when(tele).getPosition();


        // ---------- Encoder interfaces ----------
        doAnswer(invocation -> {
            zmqServer.robotPacket.leftDriveEncoderInterfacePower = invocation.getArgument(1, Double.class).doubleValue();
            return null;
        }).when(lei).set((ControlMode)any(), any(Double.class));

        doAnswer(invocation -> {
            return zmqServer.unityPacket.leftDriveEncoderInterfacePosition;
        }).when(lei).getSelectedSensorPosition();

        
        doAnswer(invocation -> {
            zmqServer.robotPacket.rightDriveEncoderInterfacePower = invocation.getArgument(1, Double.class).doubleValue();
            return null;
        }).when(rei).set((ControlMode)any(), any(Double.class));

        doAnswer(invocation -> {
            return zmqServer.unityPacket.rightDriveEncoderInterfacePosition;
        }).when(rei).getSelectedSensorPosition();


        // ---------- Indexing ----------
        doAnswer(invocation -> {
            zmqServer.robotPacket.intakeTalonPower = invocation.getArgument(1, Double.class).doubleValue();
            return null;
        }).when(itk).set((ControlMode)any(), any(Double.class));

        doAnswer(invocation -> {
            return zmqServer.unityPacket.intakeTalonPosition;
        }).when(itk).getSelectedSensorPosition();


        doAnswer(invocation -> {
            zmqServer.robotPacket.nmfNeoPower = invocation.getArgument(0, Double.class).doubleValue();
            return null;
        }).when(nmf).set(any(Double.class));

        doAnswer(invocation -> {
            return nmfe;
        }).when(nmf).getEncoder();

        doAnswer(invocation -> {
            return zmqServer.unityPacket.nmfNeoPosition;
        }).when(nmfe).getPosition();


        doAnswer(invocation -> {
            zmqServer.robotPacket.omniNeoPower = invocation.getArgument(0, Double.class).doubleValue();
            return null;
        }).when(omi).set(any(Double.class));

        doAnswer(invocation -> {
            return omie;
        }).when(omi).getEncoder();

        doAnswer(invocation -> {
            return zmqServer.unityPacket.omniNeoPosition;
        }).when(omie).getPosition();


        // ---------- Shooter ----------
        doAnswer(invocation -> {
            zmqServer.robotPacket.shooterPower1 = invocation.getArgument(1, Double.class).doubleValue();
            return null;
        }).when(sm1).set((ControlMode)any(), any(Double.class));

        doAnswer(invocation -> {
            return zmqServer.unityPacket.shooterPosition1;
        }).when(sm1).getSelectedSensorPosition();


        doAnswer(invocation -> {
            zmqServer.robotPacket.shooterPower2 = invocation.getArgument(1, Double.class).doubleValue();
            return null;
        }).when(sm2).set((ControlMode)any(), any(Double.class));

        doAnswer(invocation -> {
            return zmqServer.unityPacket.shooterPosition2;
        }).when(sm2).getSelectedSensorPosition();


        // ---------- Misc ----------
        doAnswer(invocation -> {
            return zmqServer.unityPacket.navXHeading;
        }).when(navX).getHeading();

        Context.robotController.navX = navX;

        
        doAnswer(invocation -> {
            int axis = invocation.getArgument(0, Integer.class);
            return zmqServer.unityPacket.joyAxisArray[axis];
        }).when(joy).getRawAxis(anyInt());

        doAnswer(invocation -> {
            int buttonId = invocation.getArgument(0, Integer.class);
            return zmqServer.unityPacket.joyButtonArray[buttonId];
        }).when(joy).getRawButton(anyInt());

        doAnswer(invocation -> {
            return zmqServer.unityPacket.joyPOV;
        }).when(joy).getPOV(anyInt());
    }

    @Test
    public void simulateRobot()
    {
        System.out.println("Testing testing 123");
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