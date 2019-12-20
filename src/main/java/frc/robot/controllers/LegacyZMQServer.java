package frc.robot.controllers;

import org.zeromq.ZMQ;
import org.zeromq.ZContext;

import frc.robot.util.RobotPacket;
import frc.robot.util.UnityPacket;

import com.google.gson.Gson;

public class LegacyZMQServer
{
    public static void main(String[] args) throws Exception
    {
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(ZMQ.REP);
            socket.bind("tcp://*:5555");

            while (!Thread.currentThread().isInterrupted()) {
                // Block until a message is received
                byte[] reply = socket.recv(0);

                String replyString = new String(reply, ZMQ.CHARSET);

                // Print the message
                System.out.println(
                    "Received: [" + replyString + "]"
                );

                Gson g = new Gson();

                UnityPacket smartPacket = g.fromJson(replyString, UnityPacket.class);

                System.out.println("Heartbeat = " + smartPacket.heartbeat);

                RobotPacket robotPacket = new RobotPacket();

                robotPacket.heartbeat = System.currentTimeMillis();

                String response = g.toJson(robotPacket);
                
                socket.send(response.getBytes(ZMQ.CHARSET), 0);
            }
        }
    }
}