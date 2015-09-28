import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ElbiWars_UI extends JPanel implements ActionListener{
private static final long serialVersionUID = 1L;
JPanel field;
JPanel battleField;
ElbiWars_Buildings buildings[][] = new ElbiWars_Buildings[22][22];
ElbiWars_Troops troops[] = new ElbiWars_Troops[20];
int counter = 0;

	public ElbiWars_UI(){
		Random r = new Random();
		
		 field = new JPanel();
		 field.setBackground(Color.GREEN);
		 field.setPreferredSize(new Dimension(600,600));
		 
		 battleField = new JPanel();
		 battleField.setLayout(new GridLayout(22,22));
		 
		 for(int i=0; i<22; i++){
			 for(int j=0; j<22; j++){
				 if(i%10/2 == 0){
					 buildings[i][j] = new ElbiWars_Buildings(1);
				 } else {
					 buildings[i][j] = new ElbiWars_Buildings(0);
				 }
				 
				 buildings[i][j].addActionListener(this);
				 buildings[i][j].setPreferredSize(new Dimension(25,25));
				 battleField.add(buildings[i][j]);
			 }
		 }
		 
		 field.add(battleField);
		 add(field);
		 setPreferredSize(new Dimension(800,600));
	}
	
	public static void main (String[] args){
		JFrame mainFrame = new JFrame("Elbi Wars 1.0");
		ElbiWars_UI mainPanel = new ElbiWars_UI();
		
		mainFrame.setContentPane(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	public void moveTroops(int x, int y){
		buildings[x][y].setBackground(Color.GREEN);
		for(int i=x; i<25; i++){
			if(buildings[i][y].type != 1){
				
				buildings[i][y].setBackground(Color.BLACK);
				/*try {
					Thread t = new Thread();
					t.start();
					t.sleep(1000);
					
				} catch (Interrupt
				edException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int j=0; j<22; j++){
			for(int jj=0; jj<22; jj++){
				if(e.getSource() == buildings[j][jj]){
					System.out.print(j+" "+jj+" "+buildings[j][jj].type+"\n");
					if(buildings[j][jj].type != 1 && counter < 20){
							troops[counter] = new ElbiWars_Troops(1);
							troops[counter].setPreferredSize(new Dimension(22,22));
							buildings[j][jj].add(troops[counter]);
							buildings[j][jj].setBackground(Color.BLACK);
							moveTroops(j,jj);
							counter++;
						
					} else {
						System.out.print("Deployed all troops");
					}
				}	
			}
		}
	}
}
