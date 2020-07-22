package frc.robot.util.hardware;

public interface Motor {
    void setPower(double motorPower);
    double getPower();
    void setEncoderPos(double encoderPosition);
    double getEncoderPos();
    int getMotorID();
}