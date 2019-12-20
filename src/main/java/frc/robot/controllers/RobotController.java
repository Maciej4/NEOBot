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
        zmqServer = new ZMQServer();
        zmqServer.start();

        Context.robotController = this;
    }
}