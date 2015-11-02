package eLBiWarsServer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.demo.Graph;
import org.jfree.ui.RefineryUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class Server {

	static ArrayList clientOutput;
	static ArrayList<String> users;
	
	
	private JFrame frmServer;
	private JPanel btnPanel;
	private JButton btnStart;
	private JButton btnEnd;
	private JButton btnClear;
	static JTextArea logTextArea;
	private JPanel logPanel;
	static Graph demo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server window = new Server();
					window.frmServer.setVisible(true);
					
					demo = new Graph("Data packets recieved from client");
			        demo.pack();
			        RefineryUtilities.centerFrameOnScreen(demo);
			        demo.setVisible(true);

			        
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Server() {
		initialize();
		

        
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServer = new JFrame();
		frmServer.setResizable(false);
		frmServer.setTitle("Server");
		frmServer.setBounds(100, 100, 356, 500);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(new BorderLayout(0, 0));
		
		btnPanel = new JPanel();
		frmServer.getContentPane().add(btnPanel, BorderLayout.SOUTH);
		btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startServer(e);
			}
		});
		btnPanel.add(btnStart);
		
		btnEnd = new JButton("END");
		btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endServer(e);
			}
		});
		btnPanel.add(btnEnd);
		
		btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearLog(e);
			}
		});
		btnPanel.add(btnClear);
		
		logTextArea = new JTextArea();
		logTextArea.setEditable(false);
		logTextArea.setWrapStyleWord(true);
		logTextArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(logTextArea);
		
		logPanel = new JPanel(new BorderLayout());
		logPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
	    logPanel.add(scrollPane);
		frmServer.getContentPane().add(logPanel);
		
	}
	
	private void startServer(ActionEvent e){
		Thread starter = new Thread(new ServerStart());
        starter.start();
        
        logTextArea.append("Server started...\n");
	}
	
	private void endServer(ActionEvent e){
		try{
			Thread.sleep(5000);
		}
		catch(InterruptedException ex){
			Thread.currentThread().interrupt();
		}
		tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
        logTextArea.append("Server stopping... \n");
        
        logTextArea.setText("");
	}
	
	private void clearLog(ActionEvent e){
		logTextArea.setText("");
	}
	
	public static void userAdd (String data) {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        logTextArea.append("Before " + name + " added. \n");
        users.add(name);
        logTextArea.append("After " + name + " added. \n");
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public static void userRemove (String data) {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public static void tellEveryone(String message) {
	Iterator it = clientOutput.iterator();

        while(it.hasNext()){
            try{
            	PrintWriter writer = (PrintWriter) it.next();
            	writer.println(message);
            	logTextArea.append("Sending: " + message + "\n");
                writer.flush();
                logTextArea.setCaretPosition(logTextArea.getDocument().getLength());

            } 
            catch (Exception e) {
            	logTextArea.append("Error telling everyone. \n");
            }
        } 
    }
	
}
