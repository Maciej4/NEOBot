package frc.robot.util.hardware;

public class CommDoubleSolenoid extends Hardware {
    public CommDoubleSolenoid() {
        type = "DoubleSolenoid";
        booleans = new boolean[0];
        integers = new int[] { 0 };
        doubles = new double[] { 0.0 };
        strings = new String[0];
    }

    @Override
    public void copyRelValues(Hardware other) {
        doubles[0] = other.doubles[0];
    }
}