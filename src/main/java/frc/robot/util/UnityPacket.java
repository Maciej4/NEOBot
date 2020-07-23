package frc.robot.util;

import java.util.ArrayList;

import frc.robot.util.hardware.Hardware;

public class UnityPacket {
    // DO NOT EDIT, MAY BREAK CONNECTION TO SIMULATOR
    public double heartbeat = 0.0;
    public int robotMode = 0;
    public ArrayList<Hardware> hardware = new ArrayList<Hardware>();
    public ArrayList<String> hardwareString = new ArrayList<String>();
}
