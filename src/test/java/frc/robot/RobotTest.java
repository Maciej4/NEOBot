package frc.robot;

import org.junit.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;

import frc.robot.controllers.ZMQServer;
import frc.robot.util.HardwareFactory;

public class RobotTest {
    public ZMQServer zmqServer;

    private long timeoutTime = 2000;

    // private ArrayList<CANSparkMax> canSparkMaxList = new
    // ArrayList<CANSparkMax>();
    // private AHRS ahrsHolder;

    @Before
    public void setup() {
        HardwareFactory.hardwareFactory = mock(HardwareFactory.class);

        // ---------- CANSparkMax mocking ----------
        doAnswer(invocation -> {
            CANSparkMax canSparkMax = mock(CANSparkMax.class);
            CANEncoder canEncoder = mock(CANEncoder.class);

            int motorID = invocation.getArgument(0, Integer.class).intValue();

            doAnswer(invocation2 -> {
                zmqServer.robotPacket.motorPowers[motorID] = Math
                        .max(Math.min(invocation2.getArgument(0, Double.class).doubleValue(), 1.0), -1.0);
                return null;
            }).when(canSparkMax).set(any(Double.class));

            doAnswer(invocation2 -> {
                return canEncoder;
            }).when(canSparkMax).getEncoder();

            doAnswer(invocation2 -> {
                return zmqServer.unityPacket.motorPositions[motorID];
            }).when(canEncoder).getPosition();

            return canSparkMax;
        }).when(HardwareFactory.hardwareFactory).newCANSparkMax_(anyInt(), any(MotorType.class));

        // ---------- TalonFX mocking ----------
        doAnswer(invocation -> {
            TalonFX talonFX = mock(TalonFX.class);

            int motorID = invocation.getArgument(0, Integer.class).intValue();

            doAnswer(invocation2 -> {
                zmqServer.robotPacket.motorPowers[motorID] = Math
                        .max(Math.min(invocation2.getArgument(0, Double.class).doubleValue(), 1.0), -1.0);
                return null;
            }).when(talonFX).set(any(TalonFXControlMode.class), anyDouble());

            doAnswer(invocation2 -> {
                return zmqServer.unityPacket.motorPositions[motorID];
            }).when(talonFX).getSelectedSensorPosition();

            return talonFX;
        }).when(HardwareFactory.hardwareFactory).newTalonFX_(anyInt());

        // ---------- TalonSRX mocking ----------
        doAnswer(invocation -> {
            TalonSRX talonSRX = mock(TalonSRX.class);

            int motorID = invocation.getArgument(0, Integer.class).intValue();

            doAnswer(invocation2 -> {
                zmqServer.robotPacket.motorPowers[motorID] = Math
                        .max(Math.min(invocation2.getArgument(0, Double.class).doubleValue(), 1.0), -1.0);
                return null;
            }).when(talonSRX).set(any(TalonSRXControlMode.class), anyDouble());

            doAnswer(invocation2 -> {
                return zmqServer.unityPacket.motorPositions[motorID];
            }).when(talonSRX).getSelectedSensorPosition();

            return talonSRX;
        }).when(HardwareFactory.hardwareFactory).newTalonSRX_(anyInt());

        // ---------- AHRS mocking ----------
        doAnswer(invocation -> {
            AHRS ahrs = mock(AHRS.class);

            doAnswer(invocation2 -> {
                return zmqServer.unityPacket.navXHeading;
            }).when(ahrs).getAngle();

            return ahrs;
        }).when(HardwareFactory.hardwareFactory).newAHRS_();

        // ---------- Joystick mocking ----------
        doAnswer(invocation -> {
            Joystick joystick = mock(Joystick.class);

            // int joystickID = invocation.getArgument(0, Integer.class).intValue();

            doAnswer(invocation2 -> {
                int axis = invocation2.getArgument(0, Integer.class);
                return zmqServer.unityPacket.joyAxisArray[axis];
            }).when(joystick).getRawAxis(anyInt());

            doAnswer(invocation2 -> {
                int buttonId = invocation2.getArgument(0, Integer.class);
                return zmqServer.unityPacket.joyButtonArray[buttonId];
            }).when(joystick).getRawButton(anyInt());

            doAnswer(invocation2 -> {
                return zmqServer.unityPacket.joyPOV;
            }).when(joystick).getPOV(anyInt());

            return joystick;
        }).when(HardwareFactory.hardwareFactory).newJoystick_(anyInt());
    }

    @Test
    public void simulateRobot() {
        // System.out.println("Testing testing 123");
        Robot robot = new Robot();

        zmqServer = new ZMQServer();
        System.out.println("Awaiting communication from Unity (ctrl c to kill)...");
        zmqServer.awaitComms();
        zmqServer.start();

        System.out.println("Running robot init!");
        robot.robotInit();

        System.out.println("Awaiting Unity to start robot period...\n");
        
        while (zmqServer.unityPacket.robotMode != -1 && System.currentTimeMillis() - zmqServer.lastRecived < timeoutTime) {
            switch (zmqServer.unityPacket.robotMode) {
                case 0:
                    while (zmqServer.unityPacket.robotMode == 0 && System.currentTimeMillis() - zmqServer.lastRecived < timeoutTime) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            System.out.println("Thread Interrupted!");
                            e.printStackTrace();
                        }
                    }
                    break;
                
                case 1:
                    System.out.println("Starting teleop...");
                    robot.teleopInit();
                    
                    System.out.println("Teleop started");
                    while(zmqServer.unityPacket.robotMode == 1 && System.currentTimeMillis() - zmqServer.lastRecived < timeoutTime) {
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
                    System.out.println("End of teleop\n");
                    break;
                
                case 2:
                    System.out.println("Starting auto...");
                    robot.autonomousInit();
                    
                    System.out.println("Autonomous started");
                    while(zmqServer.unityPacket.robotMode == 2 && System.currentTimeMillis() - zmqServer.lastRecived < timeoutTime) {
                        long loopStartTime = System.currentTimeMillis();
            
                        robot.autonomousPeriodic();
            
                        int loopLengthMillis = (int)(System.currentTimeMillis() - loopStartTime);
                        if(loopLengthMillis < 20) {
                            try {
                                Thread.sleep(20 - loopLengthMillis);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("Autonomous loop time overrun by: " + (loopLengthMillis - 20) + " ms");
                        }
                    }
                    System.out.println("End of autonomous\n");
                    break;

                case 3:
                    System.out.println("Starting test...");
                    robot.testInit();
                    
                    System.out.println("Test period started");
                    while(zmqServer.unityPacket.robotMode == 3 && System.currentTimeMillis() - zmqServer.lastRecived < timeoutTime) {
                        long loopStartTime = System.currentTimeMillis();
            
                        robot.testPeriodic();
            
                        int loopLengthMillis = (int)(System.currentTimeMillis() - loopStartTime);
                        if(loopLengthMillis < 20) {
                            try {
                                Thread.sleep(20 - loopLengthMillis);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("Test period loop time overrun by: " + (loopLengthMillis - 20) + " ms");
                        }
                    }
                    System.out.println("End of test period\n");
                    break;
            
                default:
                    break;
            }
        }

        System.out.println("Interrupting ZMQ thread...");
        zmqServer.interrupt();
        System.out.println("Finishing up...");
        robot.close();
    }
}