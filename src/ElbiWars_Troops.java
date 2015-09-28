import java.awt.Color;

import javax.swing.JPanel;

public class ElbiWars_Troops extends JPanel{
	private static final long serialVersionUID = 1L;
	int type = 0;
	final static int barbarian = 1;
	final static int archer = 2;
	final static int giant = 3;
	
	public ElbiWars_Troops(int ttype){
		type = ttype;
		
		if(type == barbarian){
			this.setBackground(Color.BLACK);
		} else if(type == archer){
			this.setBackground(Color.RED);
		} else if(type == giant){
			this.setBackground(Color.GRAY);
		}
		
	}
	
	
}
