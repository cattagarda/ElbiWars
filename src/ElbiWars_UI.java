import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ElbiWars_UI extends JPanel implements ActionListener{

	private static final long serialVersionUID = -5431274060160512044L;
	static int loginFlag = 0;
	JButton button = new JButton("Boom!");
	ElbiWars_Panel front; 
	ElbiWars_Panel second;
	ElbiWars_GameBase mainGame;
	JPanel mainChat;
	static URL url = null;
	
	public ElbiWars_UI(){
		this.setPreferredSize(new Dimension(1024,700));
		this.setLayout(new CardLayout());
		
		front = new ElbiWars_Panel("login.png");
		front.setLayout(new BorderLayout());
		
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(550,50));
		button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		JLabel hi = new JLabel("Yo mamma");
		hi.setPreferredSize(new Dimension(550,50));
		hi.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JTextField ipAddress = new JTextField("IP Address");
		ipAddress.setToolTipText("IP Address");
		ipAddress.setPreferredSize(new Dimension(550,50));
		ipAddress.setBackground(new Color(255,255,255,100));
		
		JTextField port = new JTextField("Port number");
		port.setPreferredSize(new Dimension(500,50));
		port.setBackground(new Color(255,255,255,100));
		
		JPanel extraPanel = new JPanel();
		extraPanel.setPreferredSize(new Dimension(200,200));
		extraPanel.setBackground(new Color(255,255,255,100));
		
		//extraPanel.add( Box.createVerticalGlue() );
		extraPanel.add(hi);
		extraPanel.add(ipAddress);
		extraPanel.add(port);
		extraPanel.add(button);
		//extraPanel.add( Box.createVerticalGlue() );
		
		JPanel chu = new JPanel();
		chu.setPreferredSize(new Dimension(1024, 200));
		chu.setBackground(new Color(255,255,255,0));
		JPanel chu2 = new JPanel();
		chu2.setPreferredSize(new Dimension(1024, 200));
		chu2.setBackground(new Color(255,255,255,0));
		
		front.add(chu, BorderLayout.NORTH);
		front.add(extraPanel);
		front.add(chu2, BorderLayout.SOUTH);
		
		second = new ElbiWars_Panel("grass.png");
		JLabel hello = new JLabel("Yo~");
		second.add(hello);
		second.setLayout(new BorderLayout());
		
		mainGame = new ElbiWars_GameBase("grass.png");
		mainGame.setPreferredSize(new Dimension(800, 700));
		
		Thread t = new Thread(mainGame);
		t.start();
		
		mainChat = new JPanel();
		mainChat.setBackground(new Color(255,255,255,100));
		mainChat.setPreferredSize(new Dimension(220, 700));
		
		second.add(mainGame, BorderLayout.CENTER);
		second.add(mainChat, BorderLayout.EAST);
		
		this.add(front, "Login page");
		this.add(second, "Game");
		
		CardLayout cardLayout = (CardLayout) this.getLayout();
		cardLayout.show(this, "Login page");
	}
	
	public static void main(String[] args) throws MalformedURLException{
		JFrame mainFrame = new JFrame("Elbi Wars 1.0");
		ElbiWars_UI mainPanel = new ElbiWars_UI();

		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.setContentPane(mainPanel);
		mainFrame.pack();
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
	    //Convert to Java2D Object
	    Graphics2D g2 = (Graphics2D) g;
	    	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button){
			CardLayout cardLayout = (CardLayout) this.getLayout();
			cardLayout.show(this, "Game");
			
			revalidate();
			repaint();
		}
	}
	
	
}
