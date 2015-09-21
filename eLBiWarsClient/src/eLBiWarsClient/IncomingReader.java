package eLBiWarsClient;

public class IncomingReader implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String[] data;
        String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

        try{
            while ((stream = Client.reader.readLine()) != null){
                 data = stream.split(":");

                 if (data[2].equals(chat)){
                    Client.chatTextArea.append(data[0] + ": " + data[1] + "\n");
                    Client.chatTextArea.setCaretPosition(Client.chatTextArea.getDocument().getLength());
                 } 
                 else if (data[2].equals(connect)){
                	Client.chatTextArea.removeAll();
                    Client.userAdd(data[0]);
                 } 
                 else if (data[2].equals(disconnect)){
                     Client.userRemove(data[0]);
                 } 
                 else if (data[2].equals(done)){
                    Client.writeUsers();
                    Client.users.clear();
                 }
            }
       }
       catch(Exception e) { }
    }
}

