import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.Icon;


public class ElbiWars_GameBase extends ElbiWars_Panel implements ActionListener, Runnable, MouseListener{
	
	private static final long serialVersionUID = 1L;
	LinkedList<ElbiWars_Troops> characters = new LinkedList<ElbiWars_Troops>();
	static ArrayList<ElbiWars_Building> team1 = new ArrayList<ElbiWars_Building>();
	static LinkedList<ElbiWars_Building> team2 = new LinkedList<ElbiWars_Building>();
	static LinkedList<ElbiWars_Building> team3 = new LinkedList<ElbiWars_Building>();
	static int currentTroop = -1;
	
	public ElbiWars_GameBase(String filename) {
		super(filename);
		
		team1.add(new ElbiWars_Building(10,0,1,1));
		team1.add(new ElbiWars_Building(100,10,2,1));
		team1.add(new ElbiWars_Building(10,80,3,1));
		team1.add(new ElbiWars_Building(100,80,4,1));
		team1.add(new ElbiWars_Building(170,50,5,1));
		team1.add(new ElbiWars_Building(50,125,6,1));
		
		for(int a=0; a<team1.size(); a++){
			Thread k = new Thread(team1.get(a));
			k.start();
		}
		
		team2.add(new ElbiWars_Building(700,10,1,2));
		team2.add(new ElbiWars_Building(620,10,2,2));
		team2.add(new ElbiWars_Building(725,80,3,2));
		team2.add(new ElbiWars_Building(620,80,4,2));
		team2.add(new ElbiWars_Building(550,50,5,2));
		team2.add(new ElbiWars_Building(670,125,6,2));
		
		for(int b=0; b<team1.size(); b++){
			Thread j = new Thread(team2.get(b));
			j.start();
		}
		
		team3.add(new ElbiWars_Building(400,500,1,3));
		team3.add(new ElbiWars_Building(410,450,2,3));
		team3.add(new ElbiWars_Building(330,510,3,3));
		team3.add(new ElbiWars_Building(490,510,4,3));
		team3.add(new ElbiWars_Building(330,450,5,3));
		team3.add(new ElbiWars_Building(490,450,6,3));
		
		for(int b=0; b<team3.size(); b++){
			Thread l = new Thread(team3.get(b));
			l.start();
		}
		
		this.addMouseListener(this);
		
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		
		for(int j=0; j<team1.size(); j++){
			 g.drawImage(team1.get(j).imageIcon.getImage(), team1.get(j).xcoordinate, team1.get(j).ycoordinate, team1.get(j).width, team1.get(j).height, null);
	    }
		
		for(int k=0; k<team2.size(); k++){
			g.drawImage(team2.get(k).imageIcon.getImage(), team2.get(k).xcoordinate, team2.get(k).ycoordinate, team2.get(k).width, team2.get(k).height, null);
	    }
		
		for(int l=0; l<team3.size(); l++){
			g.drawImage(team3.get(l).imageIcon.getImage(), team3.get(l).xcoordinate, team3.get(l).ycoordinate, team3.get(l).width, team3.get(l).height, null);
	    }
		
	    for(ElbiWars_Troops b: characters){
	    	 g.drawImage(b.imageIcon.getImage(), (int)b.xcoordinate, (int)b.ycoordinate, b.width, b.height, null);
	    }
	    
	    revalidate();
	    repaint();
	    
	}
	
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(currentTroop != -1){
			ElbiWars_Troops troop = new ElbiWars_Troops(arg0.getX(), arg0.getY(), currentTroop, 1);
			System.out.println(arg0.getX()+" "+arg0.getY());
			System.out.println("Location reg: "+troop.xcoordinate+" "+troop.ycoordinate);
			characters.add(troop);
			
			Thread k = new Thread(characters.getLast());
			k.start();
		}
	
		this.revalidate();
		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void run() {
		while(true){
			this.revalidate();
			this.repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
