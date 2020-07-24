package frc.robot.util.hardware;

public abstract class Hardware
{
    public String type;
    public boolean[] booleans;
    public int[] integers;
    public double[] doubles;
    public String[] strings;

    public String getHardwareType()
    {
        return type;
    }

    public void CopyValues(Hardware other)
    {
        this.type = other.type;
        this.booleans = other.booleans;
        this.integers = other.integers;
        this.doubles = other.doubles;
        this.strings = other.strings;
    }

    public void CopyValues(TempHardwareBox other)
    {
        this.type = other.type;
        this.booleans = other.booleans;
        this.integers = other.integers;
        this.doubles = other.doubles;
        this.strings = other.strings;
    }

    public abstract void copyRelValues(Hardware other);
}