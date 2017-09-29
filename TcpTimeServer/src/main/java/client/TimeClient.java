/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.PrintWriter;
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
public class TimeClient
{
    Socket socket;
    PrintWriter pw;
    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    public void connect(String ip , int port) throws IOException
    {
        socket = new Socket(ip , port);
        pw = new PrintWriter (socket.getOutputStream(),true);
    }
    
    public void send()
    {
        Date date = new Date();
        pw.println(sdf.format(date));
        
    }
    
    
     
    public static void main(String[] args) throws IOException
    {
        TimeClient client = new TimeClient();
        client.connect("localhost" , 1234);
        client.send();
    }
        
        
    
}
