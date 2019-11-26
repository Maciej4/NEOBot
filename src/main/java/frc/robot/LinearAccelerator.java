package frc.robot;

public class LinearAccelerator
{
    public double curVel = 0, stepSize;

    public LinearAccelerator(double stepSize_)/*simple class, nothing else, just linearly increases the speed*/
    {
        stepSize = stepSize_;
    }

    public double update(double tgtVel)
    {
        if((tgtVel - curVel)>stepSize) // if the current speed is less that the desired speed, add an increment size to the current speed
        {
            curVel+=stepSize;
        }
        else if((curVel-tgtVel) > stepSize) // if the current speed is greater that the desired speed, subtract an increment size from the current speed
        {
            curVel-=stepSize;
        }
        else
        {
            //curSpeed=tgtSpeed; // if the speed difference between target and current speed is less than an increment, set the current speed to target to avoid overshooting
        }

        return curVel;
    }
}