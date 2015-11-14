package eLBiWarsServer;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerStart implements Runnable{
	int portNum = 1984;
	
	public ServerStart(int portnum){
		this.portNum = portnum;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Server.clientOutput = new ArrayList();
        Server.users = new ArrayList();
        
        try{
        	ServerSocket sSocket = new ServerSocket(this.portNum);
        	while(true){
        		Socket cSocket = sSocket.accept();
            	
            	PrintWriter writer = new PrintWriter(cSocket.getOutputStream());
    			Server.clientOutput.add(writer);

    			Thread listener = new Thread(new ClientHandler(cSocket, writer));
    			listener.start();
//    			Server.logTextArea.append("Got a connection. \n");
    			System.out.println("Got a conn~");
    			
    			
        	}
        	
        }
        catch(Exception e){
//        	Server.logTextArea.append("Error making a connection. \n");
        	e.printStackTrace();
        }
	}

}
