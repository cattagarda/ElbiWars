import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ElbiWars_LoadTester implements ActionListener {
	static JTextField ip;
	static JTextField port;
	static JTextField numusers;
	public ElbiWars_LoadTester () {
		
	}
	public static void main (String[] args){
			JPanel panel = new JPanel();
			JFrame frame = new JFrame("Load Tester");
			
			JLabel ipl = new JLabel("IP ADDRESS");
			ip = new JTextField(20);
			JLabel portl = new JLabel("PORT NUMBER");
			port = new JTextField(20);
			JLabel numusersl = new JLabel("NUMBER OF USERS");
			 numusers = new JTextField(20);
			JButton submit = new JButton("Submit");
			
			ElbiWars_LoadTester t = new ElbiWars_LoadTester();
			submit.addActionListener(t);
			
			panel.setPreferredSize(new Dimension(300,300));
			
			
			panel.add(ipl);
			panel.add(ip);
			panel.add(portl);
			panel.add(port);
			panel.add(numusersl);
			panel.add(numusers);
			
			frame.setContentPane(panel);
			frame.setVisible(true);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String[] args = new String[2];
		args[0] = ip.getText();
		args[1] = port.getText();
		
		ClientGUI s = new ClientGUI(args);
	}
}
