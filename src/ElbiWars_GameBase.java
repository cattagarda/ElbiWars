import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;


public class ElbiWars_GameBase extends ElbiWars_Panel implements ActionListener, Runnable, MouseListener{
	
	private static final long serialVersionUID = 1L;
	LinkedList<ElbiWars_Troops> characters = new LinkedList<ElbiWars_Troops>();
	LinkedList<ElbiWars_Building> builds = new LinkedList<ElbiWars_Building>();
	ElbiWars_Building newBuilding;
	ElbiWars_Building newBuilding2;
	ElbiWars_Building newBuilding3;
	ElbiWars_Building newBuilding4;
	ElbiWars_Building newBuilding5;
	ElbiWars_Building newBuilding6;
	ElbiWars_Building newBuilding7;
	
	public ElbiWars_GameBase(String filename) {
		super(filename);
		
		this.addMouseListener(this);
		newBuilding = new ElbiWars_Building(25, 10, 1, 1);
		newBuilding2 = new ElbiWars_Building(50, 10, 1, 1);
		newBuilding3 = new ElbiWars_Building(75, 10, 1, 1);
		newBuilding4 = new ElbiWars_Building(50, 40, 1, 1);
		newBuilding5 = new ElbiWars_Building(25, 70, 1, 1);
		newBuilding6 = new ElbiWars_Building(50, 70, 1, 1);
		newBuilding7 = new ElbiWars_Building(75, 70, 1, 1);
		
		builds.add(newBuilding);
		builds.add(newBuilding2);
		builds.add(newBuilding3);
		builds.add(newBuilding4);
		builds.add(newBuilding5);
		builds.add(newBuilding6);
		builds.add(newBuilding7);
		
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for(ElbiWars_Building c: builds){
		    g.drawRect(c.xcoordinate, c.ycoordinate,20,20);
	    }
		
	    for(ElbiWars_Troops b: characters){
	    	g.drawOval(b.xcoordinate, b.ycoordinate,10,10);
	    }
	    
	}
	
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		ElbiWars_Troops troop = new ElbiWars_Troops(arg0.getX(), arg0.getY(), 5000, 1);
		System.out.println(arg0.getX()+" "+arg0.getY());
		characters.add(troop);
		
		Thread k = new Thread(characters.getLast());
		k.start();
		
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
