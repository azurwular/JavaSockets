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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class TimeServer
{
    
    private static int PORT = 1234;
    private static String IP = "localhost";
    private static ServerSocket serverSocket;
    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    public static void handleClient(Socket s) throws IOException
    {
        
        PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
        System.out.println("Received ");
        Date date = new Date();
        pw.println(sdf.format(date));
           
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
