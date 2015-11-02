package eLBiWarsServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{

	BufferedReader reader;
    Socket socket;
    PrintWriter writer;
	
    public ClientHandler(Socket clientSocket, PrintWriter user){
    	writer = user;
    	try{
            socket = clientSocket;
            InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(isReader);
        }
        catch (Exception ex){
            Server.logTextArea.append("Unexpected error... \n");
        }
    }
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat" ;
        String[] data;

        try{
            while((message = reader.readLine()) != null){
                
            	Server.logTextArea.append("Received: " + message + "\n");
                data = message.split(":");
                
                for (String token:data) {
                	Server.logTextArea.append(token + "\n");
                }

                if (data[2].equals(connect)){
                   Server.tellEveryone((data[0] + ":" + data[1] + ":" + chat));
                   Server.userAdd(data[0]);
                } 
                else if (data[2].equals(disconnect)){
                    Server.tellEveryone((data[0] + ":has disconnected." + ":" + chat));
                    Server.userRemove(data[0]);
                } 
                else if (data[2].equals(chat)){
                    Server.tellEveryone(message);
                } 
                else{
                	Server.logTextArea.append("No Conditions were met. \n");
                }
            } 
         } 
         catch (Exception ex){
        	Server.logTextArea.append("Lost a connection. \n");
//            ex.printStackTrace();
            Server.clientOutput.remove(writer);
         } 
	}
}
