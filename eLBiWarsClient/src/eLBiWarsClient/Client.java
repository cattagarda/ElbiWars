package eLBiWarsClient;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

	String username, address = "localhost";
    static ArrayList<String> users = new ArrayList();
    int port = 1984;
    Boolean isConnected = false;
    
    Socket socket;
    static BufferedReader reader;
    PrintWriter writer;
	
	static JTextArea chatTextArea;
	JPanel frmClient;
	private JPanel messagePanel;
	private JLabel lblMessage;
	private JTextField messageField;
	private JButton btnSend;
	private JPanel chatPanel;
	private JLabel lblIpAddress;
	private JTextField ipAddress;
	private JLabel lblPort;
	private JTextField portNum;
	private JLabel lblUsername;
	private JTextField playername;
	private JButton btnConnect;
	private JButton btnDisconnect;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args){
//		EventQueue.invokeLater(new Runnable() {
//		public void run() {
//			try {
//				Client window = new Client();
//				window.frmClient.setVisible(true);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	});
//	}
	

	/**
	 * Create the application.
	 */
	public Client(JPanel mainWindow) {
		initialize(mainWindow);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void  initialize(JPanel mainWindow) {
		frmClient = new JPanel();
//		frmClient.setResizable(false);
//		frmClient.setTitle("Client");
//		frmClient.setBounds(100, 100, 750, 450);
//		frmClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClient.setLayout(new BorderLayout(0, 0));

		JPanel detailsPanel = new JPanel();
		frmClient.add(detailsPanel, BorderLayout.NORTH);
		detailsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblIpAddress = new JLabel("IP Address");
		detailsPanel.add(lblIpAddress);
		
		ipAddress = new JTextField();
		ipAddress.setText("localhost");
		detailsPanel.add(ipAddress);
		ipAddress.setColumns(10);
		
		lblPort = new JLabel("Port");
		detailsPanel.add(lblPort);
		
		portNum = new JTextField();
		portNum.setText("1984");
		detailsPanel.add(portNum);
		portNum.setColumns(10);
		
		lblUsername = new JLabel("Username");
		detailsPanel.add(lblUsername);
		
		playername = new JTextField();
		detailsPanel.add(playername);
		playername.setColumns(10);
		
		//btnConnect = new JButton("CONNECT");
		//btnConnect.addActionListener(new ActionListener() {
			//public void actionPerformed(ActionEvent e) {
				//connect(e);
			//}
		//});
		//detailsPanel.add(btnConnect);
		
		mainWindow.add(frmClient);
		
	}
	
	public JPanel Initialize2() {
		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(300,600));
		messagePanel = new JPanel();
		messagePanel.setPreferredSize(new Dimension(300,200));
		btnDisconnect = new JButton("DISCONNECT");
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeConnection(e);
			}
		});
		
		
		frmClient.add(messagePanel, BorderLayout.SOUTH);
		
		lblMessage = new JLabel("Message");
		messagePanel.add(lblMessage);
		
		messageField = new JTextField();
		messagePanel.add(messageField);
		messageField.setColumns(20);
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage(e);
			}
		});
		messagePanel.add(btnSend);
		messagePanel.add(btnDisconnect);
			
		panel2.add(messagePanel);
		
		chatTextArea = new JTextArea();
		chatTextArea.setEditable(false);
		chatTextArea.setWrapStyleWord(true);
		chatTextArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(chatTextArea);
		
		chatPanel = new JPanel(new BorderLayout(0, 0));
		chatPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
		chatPanel.add(scrollPane);
		panel2.add(chatPanel);
		
		return panel2;
	}
	protected boolean connect(ActionEvent e){
		boolean connect = false;
		System.out.println("PASOK");
		address = ipAddress.getText();
		if (isConnected == false){
			System.out.println("PASOK2");
			username = playername.getText();
            playername.setEditable(false);

            try{
                socket = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(socket.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush(); 
                isConnected = true; 
                connect = true;
            } 
            catch (Exception ex){
               System.out.println("Cannot Connect! Try Again. \n");
                connect = false;
                return(connect);
                //playername.setEditable(true);
            }
            
            ListenThread();
            
        } 
		else if (isConnected == true){
           System.out.println("You are already connected. \n");
            connect = true;
            return(connect);
        }
		return connect;
		
			
	}
	
	private void closeConnection(ActionEvent e){
		sendDisconnect();
		Disconnect();
	}
	
	private void sendMessage(ActionEvent e){
		String nothing = "";
        if((messageField.getText()).equals(nothing)) {
            messageField.setText("");
            messageField.requestFocus();
        } 
        else{
            try{
               writer.println(username + ":" + messageField.getText() + ":" + "Chat");
               writer.flush();
            } 
            catch (Exception ex) {
                chatTextArea.append("Message was not sent. \n");
            }
            messageField.setText("");
            messageField.requestFocus();
        }

        messageField.setText("");
        messageField.requestFocus();
		
	}
	
	public void ListenThread(){
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
	
	public static void userAdd(String data) {
         users.add(data);
    }
    
    public static void userRemove(String data){
         chatTextArea.append(data + " is now offline.\n");
    }
    
    public static void writeUsers(){
         String[] tempList = new String[(users.size())];
         users.toArray(tempList);
         for (String token:tempList){
             //users.append(token + "\n");
         }
    }
    
    public void sendDisconnect(){
        String bye = (username + ": :Disconnect");
        try{
            writer.println(bye); 
            writer.flush(); 
        } 
        catch (Exception e){
            chatTextArea.append("Could not send Disconnect message.\n");
        }
    }
    
    public void Disconnect(){
        try{
            chatTextArea.append("Disconnected.\n");
            socket.close();
        } 
        catch(Exception e) {
            chatTextArea.append("Failed to disconnect. \n");
        }
        isConnected = false;
        playername.setEditable(true);
    }
}
