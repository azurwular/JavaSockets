/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class EchoServer
{
    private static int PORT = 1234;
    private static String IP = "localhost";
    private static ServerSocket serverSocket;
    
    public static void handleClient(Socket s) throws IOException
    {
        Scanner scan = new Scanner (s.getInputStream());
        PrintWriter pw = new PrintWriter(s.getOutputStream(),true);//DONT FORGET THE true (autoflush)
        //IMPORTANT BLOCKING
        String messageFromClient = scan.nextLine();//important BLOCKING CALL
        System.out.println("Received " + messageFromClient);
        while(!(messageFromClient.equals("STOP")))
          {
            pw.println(messageFromClient.toUpperCase());
            messageFromClient = scan.nextLine();//important BLOCKING CALL
            
            System.out.println("Received " + messageFromClient);
          }
        System.out.println("STOPPED");
    }

    public static void main(String[] args) throws IOException
    {
        if (args.length == 2)
          {
            PORT = Integer.parseInt(args[0]);
            IP = args[1];
          }

        serverSocket = new ServerSocket();//remember to bind
        serverSocket.bind(new InetSocketAddress(IP,PORT));
        System.out.println("Waiting for client");
        while(true)
        {
            Socket socket = serverSocket.accept();//important BLOCKING CALL
            System.out.println("Got a client");
            
            handleClient(socket);
        }
    }

}

