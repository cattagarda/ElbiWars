import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Random;

public class ElbiWars_ManInTheMiddle extends Thread{
	protected static Socket client;
	int dropPercentage = 50;
	
	public static void main(String []args){
	   	
		   System.out.println("ELBI WARS MAN IN THE MIDDLE!\n");
		//   FrontEnd.createGUI();
		   Thread st = new Thread(new Runnable(){
			   
			@Override
			public void run() {
			}
			   
		   });
		   st.start(); 	
	    
	      
		try {
			client = new Socket("127.0.0.1", 10001);
			
			OutputStream outToServer;
			outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
		    out.writeUTF("MANINTHEMIDDLE");
		    System.out.println("LETTING KNOW MAN IN THE MIDDLE IS ENTERNING SERVER..");
		    
		    users.add(new ElbiWars_ManInTheMiddle(client));
            users.getLast().start();
		    
		} catch (UnknownHostException e) {
		//	 TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  try
	      {
	        int port = 10002;
	        waitConn(port);
	      }catch(ArrayIndexOutOfBoundsException e)
	      {
	    	 System.out.println("Usage: java GreetingServer <port no.> ");
	      }
		  
		
	}
	
	   
	   private Socket server;
	   private static LinkedList<ElbiWars_ManInTheMiddle> users = new LinkedList<ElbiWars_ManInTheMiddle>();
	   private static ServerSocket serverSocket;
	   private DataOutputStream out;  
	   public String nickname = "DEFAULT NICKNAME";
	   
	   public ElbiWars_ManInTheMiddle(Socket server) throws IOException
	   {
	         this.server = server ;
	         this.out = new DataOutputStream(this.server.getOutputStream());
	   }

	   	//broadcast to clients
	   public void broadcast(String message, String addr){
	   
	        for(ElbiWars_ManInTheMiddle t: users){
	            try{
	           
	            String addr2 = (String) t.server.getRemoteSocketAddress().toString();     
	            if(!addr.equals(addr2)){
	            	String input = message;

	            	  t.out.writeUTF(input);
	            }
	            }catch(Exception e){
	            	
	            }
	            
	        }
	   }
	   public void broadcast2(String message, String addr){
		   
	        for(ElbiWars_ManInTheMiddle t: users){
	            try{
	           
	            String addr2 = (String) t.server.getRemoteSocketAddress().toString();     
	            if(addr.equals(addr2)){
	            	String input = message;

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
	
	      while(connected)
	      {
	         try
	         {

	        System.out.println("Client Connected: " + this.server.getRemoteSocketAddress().toString()+"\n");
	    
	                
	            while (true){
	          //   Read data from the ClientSocket 
	             in = new DataInputStream(this.server.getInputStream());
	           
	             String temp = in.readUTF();
	             final byte[] utf8Bytes = temp.getBytes("UTF-8");
	             System.out.println("MAN IN THE MIDDLE RECEIVED"+temp);
	             Random rand = new Random();
	             int randno = rand.nextInt(100);
	             
	             if(randno < dropPercentage) {
		             if(this.server.getPort() == client.getPort()){
		            	 broadcast(temp, this.server.getRemoteSocketAddress().toString());
		             }else{
		            	 broadcast2(temp, client.getRemoteSocketAddress().toString());
		             }
	             }else{
	            	 System.out.println("DROPPED PACKET!");
	             }
	            
	           }
	         }catch(SocketTimeoutException s)
	         {
	        	System.out.println("Socket timed out!\n");
	            break;
	         }catch(IOException e)
	         {
	            e.printStackTrace();
	        	System.out.println("Server ended connection to"+ server.getRemoteSocketAddress()+"\n");
	            break;
	         }
	      } 
	   }
	  
		  // 	GreetingServer.logs = new LogViewer();
	
	   


	public static void waitConn(int port){
		 System.out.println("CHAT SERVER STARTED...\n");
		
	    while(true){
	         try
	        {
	         serverSocket = new ServerSocket(port);
	         System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...\n");
	         Socket tempserver = serverSocket.accept();
	         users.add(new ElbiWars_ManInTheMiddle(tempserver));
	            users.getLast().start();
	            System.out.println("Awaiting for more users...\n");
	            
	        serverSocket.close();
	        }catch(IOException e)
	        {
	          
	        }
	             
	        }
	}

}


