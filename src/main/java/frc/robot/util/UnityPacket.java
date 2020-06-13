package frc.robot.util;

public class UnityPacket
{
    public double heartbeat = 0.0;
    public double leftDriveMotor1Position = 0.0;
    public double leftDriveMotor2Position = 0.0;
    public double rightDriveMotor1Position = 0.0;
    public double rightDriveMotor2Position = 0.0;
    public double leftDriveEncoderInterfacePosition = 0.0;
    public double rightDriveEncoderInterfacePosition = 0.0;
    public double telescopeMotorPosition = 0.0;
    public double coilMotor1Position = 0.0;
    public double coilMotor2Position = 0.0;
    public double intakeTalonPosition = 0.0;
    public double nmfNeoPosition = 0.0;
    public double shooterPosition1 = 0.0;
    public double shooterPosition2 = 0.0;
    public double nmfColorR = 0.0;
    public double nmfColorG = 0.0;
    public double nmfColorB = 0.0;
    public double nmfEncoderInterfacePosition = 0.0;
    public double omniNeoPosition = 0.0;
    public boolean[] joyButtonArray = new boolean[]{false, false, false, false};
    public double[] joyAxisArray = new double[]{0.0, 0.0, 0.0, 0.0};
    public int joyPOV = 0;
    public double navXHeading = 0.0;
}
