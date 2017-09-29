
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class EchoMultiServer {
    private ServerSocket serverSocket;
 
    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true)
            new EchoClientHandler(serverSocket.accept()).run();
    }
 
    public void stop() throws IOException {
        serverSocket.close();
    }
 
    private static class EchoClientHandler extends Thread  {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
 
        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }
 
        public void run() {
            try
              {
                out  = new PrintWriter(clientSocket.getOutputStream(), true);
              } catch (IOException ex)
              {
                Logger.getLogger(EchoMultiServer.class.getName()).log(Level.SEVERE, null, ex);
              }
            try
              {
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
              } catch (IOException ex)
              {
                Logger.getLogger(EchoMultiServer.class.getName()).log(Level.SEVERE, null, ex);
              }
             
            String inputLine;
            try
              {
                while ((inputLine = in.readLine()) != null) {
                    if (".".equals(inputLine)) {
                        out.println("bye");
                        break;
                    }
                    out.println(inputLine);
                } } catch (IOException ex)
              {
                Logger.getLogger(EchoMultiServer.class.getName()).log(Level.SEVERE, null, ex);
              }
 
            try
              {
                in.close();
              } catch (IOException ex)
              {
                Logger.getLogger(EchoMultiServer.class.getName()).log(Level.SEVERE, null, ex);
              }
            out.close();
            try
              {
                clientSocket.close();
              } catch (IOException ex)
              {
                Logger.getLogger(EchoMultiServer.class.getName()).log(Level.SEVERE, null, ex);
              }
    }
    }
}
