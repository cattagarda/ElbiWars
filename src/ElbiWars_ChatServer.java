   // File Name GreetingServer.java

import java.net.*;
import java.io.*;
import java.util.LinkedList;

public class ElbiWars_ChatServer extends Thread
{
   
   private Socket server;
   private static LinkedList<ElbiWars_ChatServer> users = new LinkedList<ElbiWars_ChatServer>();
   private static ServerSocket serverSocket;
   private DataOutputStream out;  
   public String nickname = "ALLAHU AKBAR";
   
   
   public ElbiWars_ChatServer(Socket server) throws IOException
   {
         this.server = server ;
         this.out = new DataOutputStream(this.server.getOutputStream());
   }

   public void broadcast(String message, String addr){
   
        for(ElbiWars_ChatServer t: users){
            try{
           
            String addr2 = (String) t.server.getRemoteSocketAddress().toString();
             
            if(!addr.equals(addr2)){
                t.out.writeUTF("\n"+this.nickname+": "+message);
            }
            }catch(Exception e){
            }
            
        }
   }
   public void run()
   {
      DataInputStream in;
      boolean connected = true;
      
      while(connected)
      {
         try
         {

            System.out.println("Just connected to " + this.server.getRemoteSocketAddress().toString());
            //GET THE NICKNAME
              in = new DataInputStream(this.server.getInputStream());
             this.nickname = in.readUTF();
             //broadcast(in.readUTF(), this.server.getRemoteSocketAddress().toString());
             
            broadcast(this.nickname+" Joined the Chat", this.server.getRemoteSocketAddress().toString());
                
            while (true){
            /* Read data from the ClientSocket */
             in = new DataInputStream(this.server.getInputStream());
           // System.out.println(in.readUTF());
           
            broadcast(in.readUTF(), this.server.getRemoteSocketAddress().toString());
            
           }
           
         
            //server.close();
            //connected = false;
            //System.out.println("Server ended the connection to "+ server.getRemoteSocketAddress());
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            //e.printStackTrace();
            System.out.println("Server ended connection to"+ server.getRemoteSocketAddress());
            break;
         }
      } 
   }
   public static void main(String [] args)
   {
      
      try
      {
         int port = Integer.parseInt(args[0]);
        waitConn(port);
      }catch(ArrayIndexOutOfBoundsException e)
      {
         System.out.println("Usage: java GreetingServer <port no.> ");
      }
   }


public static void waitConn(int port){
    while(true){
         try
        {
         serverSocket = new ServerSocket(port);
          System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
         Socket tempserver = serverSocket.accept();
         users.add(new ElbiWars_ChatServer(tempserver));
            users.getLast().start();
            System.out.println("Awaiting for more users...");
        serverSocket.close();
        }catch(IOException e)
        {
          
        }
       
      
        }
        
       
 
}



}


/**
a) Socket server = serverSocket.accept();
b) serverSocket = new ServerSocket(port);
**/
