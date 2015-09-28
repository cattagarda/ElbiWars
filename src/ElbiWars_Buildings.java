import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ElbiWars_Buildings extends JButton{
	private static final long serialVersionUID = 1L;
	JLabel label = new JLabel("B");
	int type = 0;
	
	public ElbiWars_Buildings(int type){
		this.type = type;
		if(type == 1){
			this.setBackground(Color.DARK_GRAY);
		} else {
			this.setBackground(Color.GREEN);
		}
		
	}
	
}
