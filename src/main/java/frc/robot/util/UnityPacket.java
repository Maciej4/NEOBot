package frc.robot.util;

public class UnityPacket
{
    // DO NOT EDIT, MAY BREAK CONNECTION TO SIMULATOR
    public double heartbeat = 0.0;
    public int robotMode = 0;
    public double[] motorPositions = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
    public double nmfColorR = 0.0;
    public double nmfColorG = 0.0;
    public double nmfColorB = 0.0;
    public boolean[] joyButtonArray = new boolean[]{false, false, false, false};
    public double[] joyAxisArray = new double[]{0.0, 0.0, 0.0, 0.0};
    public int joyPOV = 0;
    public double navXHeading = 0.0;
}
