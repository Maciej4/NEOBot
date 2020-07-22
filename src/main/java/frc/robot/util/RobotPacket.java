package frc.robot.util;

import java.util.ArrayList;

import frc.robot.util.hardware.Hardware;

public class RobotPacket
{
    // DO NOT EDIT, MAY BREAK CONNECTION TO SIMULATOR
    public long heartbeat = 0;
    public double[] motorPowers = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    public int intakeFlipSolenoidState = 0;
    public ArrayList<Hardware> hardware = new ArrayList<Hardware>();
}
