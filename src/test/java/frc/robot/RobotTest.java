package frc.robot;

import org.junit.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import java.util.HashMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;

import frc.robot.controllers.ZMQServer;
import frc.robot.util.HardwareFactory;
import frc.robot.util.hardware.*;

public class RobotTest {
    public ZMQServer zmqServer;

    private final long timeoutTime = 2000;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    // private ArrayList<CANSparkMax> canSparkMaxList = new
    // ArrayList<CANSparkMax>();
    // private AHRS ahrsHolder;

    HashMap<String, Hardware> hardwareDictionary = new HashMap<String, Hardware>();

    @Before
    public void setup() {
        HardwareFactory.hardwareFactory = mock(HardwareFactory.class);

        // ---------- CANSparkMax mocking ----------
        doAnswer(invocation -> {
            CANSparkMax canSparkMax = mock(CANSparkMax.class);
            CANEncoder canEncoder = mock(CANEncoder.class);

            int motorID = invocation.getArgument(0, Integer.class).intValue();

            if (motorID < 0 || 63 < motorID) {
                System.out.println(
                        ANSI_RED + "ERROR: CANSparkMax ID " + motorID + " is out of bounds [0, 63]." + ANSI_RESET);
                throw new Exception("MotorIDOutOfBoundsException");
            }

            CommCANSparkMax matchedHardwareItem = (CommCANSparkMax) findMatchingHardware("CANSparkMax", motorID);

            doAnswer(invocation2 -> {
                double motorPower = Math.max(Math.min(invocation2.getArgument(0, Double.class).doubleValue(), 1.0),
                        -1.0);
                matchedHardwareItem.setPower(motorPower);
                return null;
            }).when(canSparkMax).set(any(Double.class));

            doAnswer(invocation2 -> {
                return canEncoder;
            }).when(canSparkMax).getEncoder();

            doAnswer(invocation2 -> {
                return matchedHardwareItem.getEncoderPos();
            }).when(canEncoder).getPosition();

            doAnswer(invocation2 -> {
                return motorID;
            }).when(canSparkMax).getDeviceId();

            return canSparkMax;
        }).when(HardwareFactory.hardwareFactory).newCANSparkMax_(anyInt(), any(MotorType.class));

        // ---------- TalonFX mocking ----------
        doAnswer(invocation -> {
            final TalonFX talonFX = mock(TalonFX.class);

            final int motorID = invocation.getArgument(0, Integer.class).intValue();

            if (motorID < 0 || 63 < motorID) {
                System.out
                        .println(ANSI_RED + "ERROR: TalonFX ID " + motorID + " is out of bounds [0, 63]." + ANSI_RESET);
                throw new Exception("MotorIDOutOfBoundsException");
            }

            final CommTalonFX matchedHardwareItem = (CommTalonFX) findMatchingHardware("TalonFX", motorID);

            doAnswer(invocation2 -> {
                double motorPower = Math.max(Math.min(invocation2.getArgument(1, Double.class).doubleValue(), 1.0),
                        -1.0);
                // zmqServer.robotPacket.motorPowers[motorID] = motorPower;
                matchedHardwareItem.setPower(motorPower);
                return null;
            }).when(talonFX).set(any(ControlMode.class), anyDouble());

            doAnswer(invocation2 -> {
                // return (int)(zmqServer.unityPacket.motorPositions[motorID]);
                return matchedHardwareItem.getEncoderPos();
            }).when(talonFX).getSelectedSensorPosition();

            // System.out.println(matchedHardwareItem.type);

            return talonFX;
        }).when(HardwareFactory.hardwareFactory).newTalonFX_(anyInt());

        // ---------- TalonSRX mocking ----------
        doAnswer(invocation -> {
            final TalonSRX talonSRX = mock(TalonSRX.class);

            final int motorID = invocation.getArgument(0, Integer.class).intValue();

            if (motorID < 0 || 63 < motorID) {
                System.out.println(
                        ANSI_RED + "ERROR: TalonSRX ID " + motorID + " is out of bounds [0, 63]." + ANSI_RESET);
                throw new Exception("MotorIDOutOfBoundsException");
            }

            final CommTalonSRX matchedHardwareItem = (CommTalonSRX) findMatchingHardware("TalonSRX", motorID);

            doAnswer(invocation2 -> {
                double motorPower = Math.max(Math.min(invocation2.getArgument(1, Double.class).doubleValue(), 1.0),
                        -1.0);
                // zmqServer.robotPacket.motorPowers[motorID] = motorPower;
                matchedHardwareItem.setPower(motorPower);
                return null;
            }).when(talonSRX).set(any(ControlMode.class), anyDouble());

            doAnswer(invocation2 -> {
                // return (int)(zmqServer.unityPacket.motorPositions[motorID]);
                return matchedHardwareItem.getEncoderPos();
            }).when(talonSRX).getSelectedSensorPosition();

            return talonSRX;
        }).when(HardwareFactory.hardwareFactory).newTalonSRX_(anyInt());

        // ---------- AHRS mocking ----------
        doAnswer(invocation -> {
            final AHRS ahrs = mock(AHRS.class);

            final int ahrsID = 0;

            final CommAHRS matchedHardwareItem = (CommAHRS) findMatchingHardware("AHRS", ahrsID);

            doAnswer(invocation2 -> {
                return matchedHardwareItem.doubles[0];
            }).when(ahrs).getAngle();

            return ahrs;
        }).when(HardwareFactory.hardwareFactory).newAHRS_();

        // ---------- Joystick mocking ----------
        doAnswer(invocation -> {
            final Joystick joystick = mock(Joystick.class);

            final int joystickID = invocation.getArgument(0, Integer.class).intValue();

            final CommJoystick matchedHardwareItem = (CommJoystick) findMatchingHardware("Joystick", joystickID);

            doAnswer(invocation2 -> {
                int axis = invocation2.getArgument(0, Integer.class);
                return matchedHardwareItem.doubles[axis];
            }).when(joystick).getRawAxis(anyInt());

            doAnswer(invocation2 -> {
                int buttonId = invocation2.getArgument(0, Integer.class);
                return matchedHardwareItem.booleans[buttonId];
            }).when(joystick).getRawButton(anyInt());

            doAnswer(invocation2 -> {
                return matchedHardwareItem.integers[1];
            }).when(joystick).getPOV(anyInt());

            return joystick;
        }).when(HardwareFactory.hardwareFactory).newJoystick_(anyInt());
    }

    public void generateDictionary() {
        for(Hardware item : zmqServer.robotPacket.hardware) {
            String key = item.type + item.integers[0];
            hardwareDictionary.put(key, item);
        }
    }

    public Hardware findMatchingHardware(String hardwareItemType, int itemID) {
        String generatedKey = hardwareItemType + itemID;

        if(hardwareDictionary.containsKey(generatedKey)) {
            return hardwareDictionary.get(generatedKey);
        }

        System.out.println(ANSI_RED + "ERROR: " + hardwareItemType + " ID " + itemID
            + " does not match any simulated "+ hardwareItemType + " ID." + ANSI_RESET);

        return null;
    }

    @Test
    public void simulateRobot() {
        zmqServer = new ZMQServer();
        System.out.println("Awaiting communication from Unity (ctrl c to kill)...");
        zmqServer.awaitComms();
        generateDictionary();
        zmqServer.start();

        Robot robot = new Robot();

        // Spaces have been added to the print statment to cover
        // text that sometimes appears behind the print statment.
        System.out.println(ANSI_BLUE + "Running robotInit()                    " + ANSI_RESET);

        robot.robotInit();

        System.out.println("Awaiting Unity to start robot period...\n");

        while (zmqServer.unityPacket.robotMode != -1
                && System.currentTimeMillis() - zmqServer.lastRecived < timeoutTime) {
            switch (zmqServer.unityPacket.robotMode) {
                case 0:
                    while (zmqServer.unityPacket.robotMode == 0
                            && System.currentTimeMillis() - zmqServer.lastRecived < timeoutTime) {
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
                    while (zmqServer.unityPacket.robotMode == 1
                            && System.currentTimeMillis() - zmqServer.lastRecived < timeoutTime) {
                        long loopStartTime = System.currentTimeMillis();

                        robot.teleopPeriodic();

                        int loopLengthMillis = (int) (System.currentTimeMillis() - loopStartTime);
                        if (loopLengthMillis < 20) {
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
                    while (zmqServer.unityPacket.robotMode == 2
                            && System.currentTimeMillis() - zmqServer.lastRecived < timeoutTime) {
                        long loopStartTime = System.currentTimeMillis();

                        robot.autonomousPeriodic();

                        int loopLengthMillis = (int) (System.currentTimeMillis() - loopStartTime);
                        if (loopLengthMillis < 20) {
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
                    while (zmqServer.unityPacket.robotMode == 3
                            && System.currentTimeMillis() - zmqServer.lastRecived < timeoutTime) {
                        long loopStartTime = System.currentTimeMillis();

                        robot.testPeriodic();

                        int loopLengthMillis = (int) (System.currentTimeMillis() - loopStartTime);
                        if (loopLengthMillis < 20) {
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