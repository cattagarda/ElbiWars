import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;



public class ElbiWars_ChatClient extends Thread
{
  protected static Socket client;
  protected static JTextField inputbox = new JTextField(10); //Declare it here for Data Passing
  protected static JTextArea  field = new JTextArea(); //Declare it here for Data Passing
  protected static boolean running  = true;
  
  public void run(){
    while(running){
         /* Receive data from the ServerSocket */
            try{
                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                String s = in.readUTF();
                
                String[] st = s.split(" ");
                System.out.println(st[1]);
                if(st[1].equals("RECONN")){
                		System.out.println("RECONNECTING...");
                		ElbiWars_ChatClient.client = new Socket("127.0.0.1", 10002);
                	
                }else{
                	field.append(s);
                }
               
            }catch(Exception e){
            }
    }
  }
 
   public static void main(String [] args)
   {
        ClientGUI a = new ClientGUI(args);

    }
   
   
}

class ClientGUI{
      JPanel mainpanel = new JPanel();
    //  JFrame mainframe = new JFrame();
      int counter = 0;
      
        public ClientGUI(String[] args){
          mainpanel.setPreferredSize(new Dimension(220,768));
          

          ElbiWars_ChatClient.field.setPreferredSize(new Dimension(220,500));
          ElbiWars_ChatClient.inputbox.setSize(new Dimension(220,30));
          mainpanel.add(ElbiWars_ChatClient.field);
          mainpanel.add(ElbiWars_ChatClient.inputbox);
//          mainframe.setContentPane(mainpanel);
//          mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//          mainframe.setVisible(true);
//          mainframe.pack();
//          mainframe.setResizable(true);
          
        try{
        	
   	  String serverName = args[0]; //get IP address of server from first param
          int port = Integer.parseInt(args[1]); //get port from second param
          
          
          /* Open a ClientSocket and connect to ServerSocket */
         System.out.println("Connecting to " + serverName + " on port " + port);
         //insert missing line here for creating a new socket for client and binding it to a port
	  ElbiWars_ChatClient.client = new Socket(serverName, port);

	      
         System.out.println("Just connected to " + ElbiWars_ChatClient.client.getRemoteSocketAddress());
         /* Send data to the ServerSocket */
         ElbiWars_ChatClient.field.append("ELBI WARS! CHAT\n PLEASE INPUT YOUR NICKNAME IN THE BOX");
        
         
         Thread t = new ElbiWars_ChatClient();
        t.start();
        
        Action submit = new AbstractAction()
        {
          /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override 
          public void actionPerformed(ActionEvent e){
				if(counter == 0){
	         		 ElbiWars_ChatClient.field.append("\n\nWelcome "+ ElbiWars_ChatClient.inputbox.getText() +"!\n");
	         		 counter++;
	         	}else{
            ElbiWars_ChatClient.field.append("Me: "+ElbiWars_ChatClient.inputbox.getText()+"\n");
	         	}
            OutputStream outToServer;
			try {
				outToServer = ElbiWars_ChatClient.client.getOutputStream();
				DataOutputStream out = new DataOutputStream(outToServer);
	         	out.writeUTF(ElbiWars_ChatClient.inputbox.getText());
	         	System.out.println("Sending"+ElbiWars_ChatClient.inputbox.getText()+"to"+ElbiWars_ChatClient.client.getPort());
	         	
	         
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
         
          	ElbiWars_ChatClient.inputbox.setText("");
          }

        };
        ElbiWars_ChatClient.inputbox.addActionListener(submit);
        
        
         //insert missing line for closing the socket from the client side
	//ElbiWars_ChatClient.client.close(); 
	//ElbiWars_ChatClient.running = false;

      }catch(IOException e)
      {
        
      	ElbiWars_ChatClient.field.append("ERROR: Cannot Connect to Server. \nPlease Restart the Game.\n");
      }catch(ArrayIndexOutOfBoundsException e)
      {
         System.out.println("Usage: java GreetingClient <server ip> <port no.> '<your message to the server>'");
            }
        }
        
       public void SendMessage() {
    	   
			if(counter == 0){
        		 ElbiWars_ChatClient.field.append("\n\nWelcome "+ "RANDOM STRING" +"!\n");
        		 counter++;
        	}else{
       ElbiWars_ChatClient.field.append("Me: "+"RANDOM STRING\n");
        	}
       OutputStream outToServer;
		try {
			outToServer = ElbiWars_ChatClient.client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
        	out.writeUTF(ElbiWars_ChatClient.inputbox.getText());
        	System.out.println("Sending"+"RANDOM STRING"+"to"+ElbiWars_ChatClient.client.getPort());
        	
        
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    
     	ElbiWars_ChatClient.inputbox.setText("");
       }
   }
   
