import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ElbiWars_Troops extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double xcoordinate = 0;
	double ycoordinate = 0;
	int width = 20;
	int height = 20;
	int type = 0;
	int speed = 100;
	int quantity = 0;
	int status = 0;
	int hp = 1000;
	int damage = 0;
	double range = 10.0;
	String filename = "";
	ElbiWars_Building a;
	int owner = 0;
	ElbiWars_Building target;
	ImageIcon imageIcon;
	
	public ElbiWars_Troops(int xcoordinate, int ycoordinate, int type, int owner) {
		this.xcoordinate = xcoordinate;
		this.ycoordinate = ycoordinate;
		this.type = type;
		this.owner = owner;
		
		this.setTroop();
		this.setHPandDamage();
	}

	private void setHPandDamage(){
		switch(this.type){
			case 0: this.hp = 100;
					this.damage = 20;
					break;
			case 1: this.hp = 80;
					this.damage = 20;
					break;
			case 2: this.hp = 500;
					this.damage = 100;
					break;
			case 3: this.hp = 60;
					this.damage = 150;
					break;
			case 4: this.hp = 2000;
					this.damage = 50;
					break;
			case 5: this.hp = 200;
					this.damage = 100;
					break;
			case 6: this.hp = 1000;
					this.damage = 200;
					break;
			case 7: this.hp = 1000;
					this.damage = 100;
					break;
			case 8: this.hp = 2000;
					this.damage = 500;
					break;
			case 9: this.hp = 400;
					this.damage = 700;
					break;
		}
	}
	
	private void setTroop(){
		switch(this.type){
			case 0: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Freshman.gif"));
				this.width = 30;
				this.height = 30;
				this.speed = 100;
				break;
			case 1: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Sophomore.gif"));
				this.width = 30;
				this.height = 30;
				this.speed = 10;
				break;
			case 2: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Transferee.gif"));
				this.width = 30;
				this.height = 30;
				this.speed = 100;
				break;
			case 3: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Prerog.gif"));
				this.width = 30;
				this.height = 30;
				this.speed = 10;
				break;
			case 4: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Leecher.gif"));
				this.width = 30;
				this.height = 30;
				this.speed = 10;
				break;
			case 5: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Senior.gif"));
				this.width = 30;
				this.height = 30;
				this.speed = 100;
				break;
			case 6: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Senior++.gif"));
				this.width = 30;
				this.height = 30;
				this.speed = 100;
				break;
			case 7: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/SPPresentor.gif"));
				this.width = 30;
				this.height = 30;
				this.speed = 100;
				break;
			case 8: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Double.gif"));
				this.width = 30;
				this.height = 30;
				this.speed = 100;
				break;
			case 9: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Vendor.gif"));
				this.width = 30;
				this.height = 30;
				this.speed = 1;
				break;
		}
	}
	
	@Override
	public void run() {
		
		while(true){
			target = ElbiWars_GameBase.team1.get(0);
			
			try {
				this.computepos();
				Thread.sleep(this.speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
//	public void findTarget(team){
//		for(ElbiWars_Building a: ){
//			
//		}
//	}
	
	//compute the position relative to the target
		public void computepos(){	
			try {
				double slope=(target.ycoordinate-this.ycoordinate)/(target.xcoordinate-this.xcoordinate);
				double b=target.ycoordinate-(target.xcoordinate*slope);
				if (target.xcoordinate>this.xcoordinate) this.xcoordinate++; else this.xcoordinate--;
				this.ycoordinate=(((slope)*this.xcoordinate)+b);
				//System.out.println(": ("+this.xcoordinate+","+this.ycoordinate+")");
				if(Math.sqrt((target.xcoordinate-this.xcoordinate)*(target.xcoordinate-this.xcoordinate)+(target.ycoordinate-this.ycoordinate)*(target.ycoordinate-this.ycoordinate))<range){
					while(hp>0){	
						if(target.hp<=0){
							//System.out.println(id+" wins");
							hp=0;
							//replace later with target finding algorithm
							break;
						}
						System.out.println(target.type+"'s HP: "+target.hp);
						target.hp = target.hp - this.damage;
						
						
					}
				}
				if(hp==0){
					
				}
				Thread.sleep(this.speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
	    //Convert to Java2D Object
	    Graphics2D g2 = (Graphics2D) g;
	    
	    
	}
}
