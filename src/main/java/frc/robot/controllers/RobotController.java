package frc.robot.controllers;

import frc.robot.util.Context;

public class RobotController
{
    public Drivetrain drivetrain;
    // public AutoDrive autoDrive;
    public NavX navX;
    // public Antenna antenna;
    public ZMQServer zmqServer;

    public RobotController () {
        drivetrain = new Drivetrain();
        // autoDrive = new AutoDrive();
        navX = new NavX();
        // antenna = new Antenna();
        zmqServer = new ZMQServer();
        zmqServer.start();
        // zmqServer.run();

        Context.robotController = this;
    }
}