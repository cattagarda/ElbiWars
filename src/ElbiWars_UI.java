import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
	JPanel troopPanel = new JPanel();
	JButton troopButton[] = new JButton[10];
	JTextField ipAddress;
	JTextField port;
	static int team;
	
	public ElbiWars_UI(){
		Random rn = new Random();

		for(int i =0; i < 4; i++){
		    team = rn.nextInt(10) + 1;
		    System.out.println(team);
		}
		
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
		
		ipAddress = new JTextField("IP Address");
		ipAddress.setToolTipText("IP Address");
		ipAddress.setPreferredSize(new Dimension(550,50));
		ipAddress.setBackground(new Color(255,255,255,100));
		
		port = new JTextField("Port number");
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
		
		troopPanel.setLayout(new GridLayout(1,10));
		for(int u=0; u<10; u++){
			troopButton[u] = new JButton();
			troopButton[u].setPreferredSize(new Dimension(50,80));
			setImg(troopButton[u], u);
			troopButton[u].addActionListener(this);
			troopPanel.add(troopButton[u]);
		}
		
		second.add(mainGame, BorderLayout.CENTER);
		second.add(mainChat, BorderLayout.EAST);
		second.add(troopPanel, BorderLayout.SOUTH);
		
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
			String[] args = new String[2];
			args[0] = ipAddress.getText();
			args[1] = port.getText();
			ClientGUI s = new ClientGUI(args);
			mainChat.add(s.mainpanel);
			
			
			CardLayout cardLayout = (CardLayout) this.getLayout();
			cardLayout.show(this, "Game");
			
			revalidate();
			repaint();
		} else {
			for(int troopBtn = 0; troopBtn < 10; troopBtn++){
				if(e.getSource() == troopButton[troopBtn]){
					ElbiWars_GameBase.currentTroop = troopBtn; 
				}
			}
		}
	}
	
	public void setImg(JButton button, int index) { 
		// TODO Auto-generated method stub
		try{
			switch(index){
				case 0:
					button.setIcon(new ImageIcon(ElbiWars_UI.class.getResource("Images/Freshman.gif")));
					break;
				case 1:
					button.setIcon(new ImageIcon(ElbiWars_UI.class.getResource("Images/Sophomore.gif"))); 
					break;
				case 2:
					button.setIcon(new ImageIcon(ElbiWars_UI.class.getResource("Images/Transferee.gif"))); 
					break;
				case 3:
					button.setIcon(new ImageIcon(ElbiWars_UI.class.getResource("Images/Prerog.gif"))); 
					break;
				case 4:
					button.setIcon(new ImageIcon(ElbiWars_UI.class.getResource("Images/Leecher.gif"))); 
					break;
				case 5:
					button.setIcon(new ImageIcon(ElbiWars_UI.class.getResource("Images/Senior.gif"))); 
					break;
				case 6:
					button.setIcon(new ImageIcon(ElbiWars_UI.class.getResource("Images/Senior++.gif"))); 
					break;
				case 7:
					button.setIcon(new ImageIcon(ElbiWars_UI.class.getResource("Images/SPPresentor.gif"))); 
					break;
				case 8:
					button.setIcon(new ImageIcon(ElbiWars_UI.class.getResource("Images/Double.gif"))); 
					break;
				case 9:
					button.setIcon(new ImageIcon(ElbiWars_UI.class.getResource("Images/Vendor.gif"))); 
					break;
			}
		} catch (Exception e){ }
	}
}
