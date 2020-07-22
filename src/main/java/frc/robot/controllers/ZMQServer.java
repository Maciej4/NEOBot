package frc.robot.controllers;

import org.zeromq.ZMQ;
import org.zeromq.SocketType;
import org.zeromq.ZContext;

import frc.robot.util.RobotPacket;
import frc.robot.util.UnityPacket;
import frc.robot.util.hardware.CommCANSparkMax;
import frc.robot.util.hardware.Hardware;
import frc.robot.util.hardware.CommTalonFX;
import frc.robot.util.hardware.TempHardwareBox;

import com.google.gson.Gson;

public class ZMQServer extends Thread
{
    // DO NOT EDIT, MAY BREAK CONNECTION TO SIMULATOR
    private Gson g;
    private ZMQ.Socket socket;
    private ZContext context;
    public RobotPacket robotPacket;
    public UnityPacket unityPacket;
    public long lastRecived = 0;

    public ZMQServer()
    {
        g = new Gson();
        context = new ZContext();
        robotPacket = new RobotPacket();
        unityPacket = new UnityPacket();
        
        socket = context.createSocket(SocketType.REP); //ZMQ.REP
        socket.bind("tcp://*:5555");

        System.out.println("Sucessful Server Initialization");
    }

    public void awaitComms() {
        //Recieving data and putting it into object
        String replyString = new String(socket.recv(0), ZMQ.CHARSET);

        lastRecived = System.currentTimeMillis();

        unityPacket = g.fromJson(replyString, UnityPacket.class);

        System.out.println("Communication recieved: \n" + replyString + "\n");

        //Setting up the robotpacket hardware list
        robotPacket.hardware.clear();
        unityPacket.hardware.clear();

        for (String itemString : unityPacket.hardwareString) {
            TempHardwareBox tempHardwareBox = g.fromJson(itemString, TempHardwareBox.class);

            unityPacket.hardware.add(decodeHardware(tempHardwareBox));
        }

        robotPacket.hardware.addAll(unityPacket.hardware);

        //Responding to unity server with data
        robotPacket.heartbeat = System.currentTimeMillis();

        String response = g.toJson(robotPacket);
        
        socket.send(response.getBytes(ZMQ.CHARSET), 0);
    }

    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            //Recieving data and putting it into object
            String replyString = new String(socket.recv(0), ZMQ.CHARSET);

            lastRecived = System.currentTimeMillis();

            unityPacket = g.fromJson(replyString, UnityPacket.class);
            
            for(int i = 0; i < unityPacket.hardware.size(); i++) {
                TempHardwareBox tempHardwareBox = g.fromJson(
                    unityPacket.hardwareString.get(i), TempHardwareBox.class);
                unityPacket.hardware.get(i).CopyValues(decodeHardware(tempHardwareBox));
            }

            //Responding to unity server with data
            robotPacket.heartbeat = System.currentTimeMillis();

            String response = g.toJson(robotPacket);
            
            socket.send(response.getBytes(ZMQ.CHARSET), 0);
        }
    }

    public Hardware decodeHardware(TempHardwareBox tempHardwareBox) {
        switch(tempHardwareBox.type) {
            case("CANSparkMax"): {
                CommCANSparkMax tempHardware = new CommCANSparkMax(0);
                tempHardware.CopyValues(tempHardwareBox);
                return tempHardware;
            }
            case("TalonFX"): {
                CommTalonFX tempHardware = new CommTalonFX(0);
                tempHardware.CopyValues(tempHardwareBox);
                return tempHardware;
            }
            default: {
                return null;
            }
        }
    }
}