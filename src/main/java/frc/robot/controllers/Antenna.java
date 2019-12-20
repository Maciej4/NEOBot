package frc.robot.controllers;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Antenna 
{
    public NetworkTableInstance inst;
    public NetworkTable table;
    public NetworkTableEntry wKey, aKey, sKey, dKey;
    public boolean wKeyState = false, aKeyState = false, sKeyState = false, dKeyState = false;

    public final String tableName = "UnityData";

    public Antenna ()
    {
        inst = NetworkTableInstance.getDefault();
        table = inst.getTable(tableName);
        wKey = table.getEntry("WKey");
        aKey = table.getEntry("AKey");
        sKey = table.getEntry("SKey");
        dKey = table.getEntry("DKey");
    }

    public void loop()
    {
        String wKeyString = wKey.getString("0");
        String aKeyString = aKey.getString("0");
        String sKeyString = sKey.getString("0");
        String dKeyString = dKey.getString("0");

        wKeyState = wKeyString.equals("1") ? true : false;
        aKeyState = aKeyString.equals("1") ? true : false;
        sKeyState = sKeyString.equals("1") ? true : false;
        dKeyState = dKeyString.equals("1") ? true : false;

        System.out.println("w: " + wKeyState + " a: " + aKeyState + " s: " + sKeyState + " d: " + dKeyState);
    }

    public double getPower()
    {
        if(wKeyState)
        {
            return -1;
        }
        else if(sKeyState)
        {
            return 1;
        }

        return 0;
    }

    public double getTurn()
    {
        if(aKeyState)
        {
            return -1;
        }
        else if(dKeyState)
        {
            return 1;
        }

        return 0;
    }

    public boolean getWKey()
    {
        return wKeyState;
    }
}