package frc.robot.util;

public class Timer {

    long currentTime;
    long startTime;

    public Timer() {
        currentTime = System.currentTimeMillis();
        startTime = currentTime;
    }

    public void start() {
        currentTime = System.currentTimeMillis();
        startTime = currentTime;
    }

    public int getms() {
        currentTime = System.currentTimeMillis();
        return (int)(currentTime - startTime);
    }

    public double getS() {
        currentTime = System.currentTimeMillis();
        return (currentTime - startTime)/1000.0;
    }

}