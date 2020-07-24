package frc.robot.util.hardware;

public class CommJoystick extends Hardware {
    public CommJoystick() {
        type = "Joystick";
        booleans = new boolean[] { false, false, false, false, false, false, false, false, false, false };
        integers = new int[] { 0, 0 };
        doubles = new double[] { 0.0, 0.0, 0.0, 0.0 };
        strings = new String[0];
    }

    public CommJoystick(int joystickID) {
        type = "Joystick";
        booleans = new boolean[] { false, false, false, false, false, false, false, false, false, false };
        integers = new int[] { joystickID, 0 };
        doubles = new double[] { 0.0, 0.0, 0.0, 0.0 };
        strings = new String[0];
    }

    @Override
    public void copyRelValues(Hardware other) {
        booleans = other.booleans;
        doubles = other.doubles;
    }
}
