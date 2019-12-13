package frc.robot;

public class RandomDrive
{
    public double driveLength = 0;
    public double goalHeading = 0;
    public PID headingPID = new PID(0.5,0,0);

    public RandomDrive(double driveLength_)
    {
        this.driveLength = driveLength_;
    }

    public void loop()
    {
        
    }
}