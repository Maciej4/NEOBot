package frc.robot.util.hardware;

public class CommAHRS extends Hardware {
    public CommAHRS() {
        type = "AHRS";
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