import java.net.*;
import java.io.*;
import java.util.*;

public class ElbiWars_ChatClient extends Thread
{
  protected static Socket client;
  protected static boolean running  = true;
  
  public void run(){
    while(running){
         /* Receive data from the ServerSocket */
            try{
                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                System.out.print(in.readUTF()+"\nInput Message: ");
            }catch(Exception e){
            }
    }
  }
 
   public static void main(String[] args)
   {
        ClientGUI a = new ClientGUI(args);

    }
   
   
   
}

class ClientGUI{
     
        public ClientGUI(String[] args){
        
        try{
   	  String serverName = args[0]; //get IP address of server from first param
          int port = Integer.parseInt(args[1]); //get port from second param
          String message = "DEFAULT MESSAGE"; //declare a default message

         /* Open a ClientSocket and connect to ServerSocket */
         System.out.println("Connecting to " + serverName + " on port " + port);
         //insert missing line here for creating a new socket for client and binding it to a port
         ElbiWars_ChatClient.client = new Socket(serverName, port);

	      
         System.out.println("Just connected to " + ElbiWars_ChatClient.client.getRemoteSocketAddress());
         /* Send data to the ServerSocket */
        Thread t = new ElbiWars_ChatClient();
        t.start();
	
	while (!message.equals("/EXIT")){
		System.out.print("Input Message: ");
		 OutputStream outToServer = ElbiWars_ChatClient.client.getOutputStream();
         	DataOutputStream out = new DataOutputStream(outToServer);
		Scanner scan = new Scanner(System.in);
		message = scan.nextLine();
         	out.writeUTF("Client " + ElbiWars_ChatClient.client.getLocalSocketAddress()+" says: " +message);
         	
        }     
        
         //insert missing line for closing the socket from the client side
	ElbiWars_ChatClient.client.close(); 
	ElbiWars_ChatClient.running = false;

      }catch(IOException e)
      {
         e.printStackTrace();
      	//System.out.println("Cannot find Server");
      }catch(ArrayIndexOutOfBoundsException e)
      {
         System.out.println("Usage: java GreetingClient <server ip> <port no.> '<your message to the server>'");
            }
        }
   }
   
