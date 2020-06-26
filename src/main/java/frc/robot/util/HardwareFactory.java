package frc.robot.util;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * This factory wraps the constructors for pieces of hardware on the robot 
 * in order to allow for use of the robot with the 2020FRCSim. However, 
 * the code should work with a physcial robot as well.
 */
public class HardwareFactory {
    public static HardwareFactory hardwareFactory = new HardwareFactory();


    /**
	 * Create a new SPARK MAX Controller
	 *
	 * @param deviceID The device ID. [0,62]
	 * @param type     The motor type connected to the controller. In the case of the 
     *                 simulator use {@code MotorType.kBrushless}. Brushless motors
	 *                 must be connected to their matching color and the hall sensor
	 *                 plugged in. Brushed motors must be connected to the Red and
	 *                 Black terminals only.
	 */
    public static CANSparkMax newCANSparkMax(int deviceID, MotorType type) {
        return hardwareFactory.newCANSparkMax_(deviceID, type);
    }

    public CANSparkMax newCANSparkMax_(int deviceID, MotorType type) {
        return new CANSparkMax(deviceID, type);
    }


    /**
     * Create a new TALON FX Controller
     * 
     * @param deviceNumber The device ID. [0,62]
     */
    public static TalonFX newTalonFX(int deviceNumber) {
        return hardwareFactory.newTalonFX_(deviceNumber);
    }

    public TalonFX newTalonFX_(int deviceNumber) {
        return new TalonFX(deviceNumber);
    }

    /**
     * Create a new TALON SRX Controller
     * 
     * @param deviceNumber The device ID. [0,62]
     */
    public static TalonSRX newTalonSRX(int deviceNumber) {
        return hardwareFactory.newTalonSRX_(deviceNumber);
    }

    public TalonSRX newTalonSRX_(int deviceNumber) {
        return new TalonSRX(deviceNumber);
    }

    /**
     * Create a new AHRS object, which is the code representation of 
     * a NavX.
     * 
     * The real constructor looks like {@code new AHRS(SPI.Port.kMXP)}.
     * However for simplicity this is taken care of by the HardwareFactory.
     */
    public static AHRS newAHRS() {
        return hardwareFactory.newAHRS_();
    }

    public AHRS newAHRS_() {
        return new AHRS(SPI.Port.kMXP);
    }

    /**
     * Construct an instance of a joystick. The joystick index is the 
     * USB port on the drivers station. If using with FRCSim2020, use 
     * port 0.
     * 
     * @param port The port on the Driver Station that the joystick 
     * is plugged into.
     */
    public static Joystick newJoystick(int port) {
        return hardwareFactory.newJoystick_(port);
    }

    public Joystick newJoystick_(int port) {
        return new Joystick(port);
    }
}