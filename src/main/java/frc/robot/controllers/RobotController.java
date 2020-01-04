package frc.robot.controllers;

import frc.robot.util.Context;

public class RobotController
{
    public Drivetrain drivetrain;
    public AutoDrive autoDrive;
    public NavX navX;
    // public Antenna antenna;
    public ZedZMQClient zmqServer;

    public RobotController () {
        drivetrain = new Drivetrain();
        autoDrive = new AutoDrive();
        navX = new NavX();
        zmqServer = new ZedZMQClient();
        zmqServer.start();

        Context.robotController = this;
    }
}