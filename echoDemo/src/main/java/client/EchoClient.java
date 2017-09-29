/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class EchoClient
{
    Socket socket;
    Scanner scan;
    PrintWriter pw;
    
    public void connect(String ip , int port) throws IOException
    {
        socket = new Socket(ip , port);
        
        scan = new Scanner (socket.getInputStream());
        pw = new PrintWriter (socket.getOutputStream(),true);
    }
    
    public void send(String msg)
    {
        pw.println(msg);
        
    }
    //will not work from a gui since it blocks
    public String receive()
    {
        return scan.nextLine();
    
    }
     
    public static void main(String[] args) throws IOException
    {
        EchoClient client = new EchoClient();
        client.connect("localhost" , 1234);
        client.send("Hello World");
        System.out.println("GOT " + client.receive());
        client.send("STOP");
        System.out.println("DONE");
    }
        
        
    
    
    
}
