   // File Name GreetingServer.java

import java.net.*;
import java.awt.List;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.management.monitor.Monitor;

public class ElbiWars_ChatServer extends Thread
{
	//for the logs of the server
	//public static LogViewer logs;
   
   private Socket server;
   private static LinkedList<ElbiWars_ChatServer> users = new LinkedList<ElbiWars_ChatServer>();
   private static ServerSocket serverSocket;
   private DataOutputStream out;  
   public String nickname = "DEFAULT NICKNAME";
   public ArrayList<Integer> team = new ArrayList<Integer>();
   
   //FrontEnd monitor;
   
   public ElbiWars_ChatServer(Socket server) throws IOException
   {
	   	team.add(1);
	   	team.add(2);
	   	team.add(3);
	   	
         this.server = server ;
         this.out = new DataOutputStream(this.server.getOutputStream());
   }

   public void broadcast(String message, String addr){
   
        for(ElbiWars_ChatServer t: users){
            try{
           
            String addr2 = (String) t.server.getRemoteSocketAddress().toString();     
            if(!addr.equals(addr2)){
            	String input = "\n"+this.nickname+": "+message;
            	 // monitor.getBytesReceived2(input.length(), "Sent");
            	  t.out.writeUTF(input);
            }
            }catch(Exception e){
            	
            }
            
        }
   }
   public void run()
   {
        DataInputStream in;
      boolean connected = true;
      boolean mmid = false;
  //  monitor = new FrontEnd();
      while(connected)
      {
         try
         {

        System.out.println("Client Connected: " + this.server.getRemoteSocketAddress().toString()+"\n");
            //GET THE NICKNAME
              in = new DataInputStream(this.server.getInputStream());
             this.nickname = in.readUTF();
            
            broadcast(this.nickname+" Joined the Chat", this.server.getRemoteSocketAddress().toString());
            
            if(this.nickname.equals("MANINTHEMIDDLE")){
            	for(ElbiWars_ChatServer t: users){
            		if(t == this){
            			broadcast("RECONN",this.server.getRemoteSocketAddress().toString());
            			System.out.println("XD");
            			mmid = true;
            		}else{
            			//users.remove(t);
            		}
            	}
            	
            }
                
            while (true){
	            /* Read data from the ClientSocket */
	             in = new DataInputStream(this.server.getInputStream());
	
	             String temp = in.readUTF();
	             final byte[] utf8Bytes = temp.getBytes("UTF-8");
	             
	             System.out.println("SERVER RECEIVED"+temp);
	             if(mmid == false){
	            	 broadcast(temp, this.server.getRemoteSocketAddress().toString());
	             }else{
	            	 broadcast(temp, "10001");
	             }
       
           }
         }catch(SocketTimeoutException s)
         {
        	System.out.println("Socket timed out!\n");
            break;
         }catch(IOException e)
         {
            //e.printStackTrace();
        	System.out.println("Server ended connection to"+ server.getRemoteSocketAddress()+"\n");
            break;
         }
      } 
   }
   public static void main(String [] args) throws IOException
   {
	   //	GreetingServer.logs = new LogViewer();
	   	
	   System.out.println("ELBI WARS!\n");
	 //  FrontEnd.createGUI();
	   Thread st = new Thread(new Runnable(){
		   
		@Override
		public void run() {
		}
		   
	   });
	   st.start();
	 
	   	
      try
      {
        int port = 10001;
        waitConn(port);
      }catch(ArrayIndexOutOfBoundsException e)
      {
    	 System.out.println("Usage: java GreetingServer <port no.> ");
      }
   }


public static void waitConn(int port){
	 System.out.println("CHAT SERVER STARTED...\n");
	
    while(true){
         try
        {
         serverSocket = new ServerSocket(port);
         System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...\n");
         Socket tempserver = serverSocket.accept();
         users.add(new ElbiWars_ChatServer(tempserver));
            users.getLast().start();
            System.out.println("Awaiting for more users...\n");
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
