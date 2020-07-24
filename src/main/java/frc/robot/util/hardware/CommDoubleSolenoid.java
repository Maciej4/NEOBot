package frc.robot.util.hardware;

public class CommDoubleSolenoid extends Hardware {
    public CommDoubleSolenoid() {
        type = "DoubleSolenoid";
        booleans = new boolean[0];
        integers = new int[] { 0, 0, 0 };
        doubles = new double[0];
        strings = new String[0];
    }

    public CommDoubleSolenoid(int channelA, int channelB) {
        type = "DoubleSolenoid";
        booleans = new boolean[0];
        integers = new int[] { channelA, channelB, 0 };
        doubles = new double[0];
        strings = new String[0];
    }

    @Override
    public void copyRelValues(Hardware other) {
        return;
    }

    public void setState(int newState) {
        newState = Math.max(Math.min(newState, 2), 0);
        integers[2] = newState;
    }
}