package eLBiWarsClient;

import java.awt.Rectangle;

import javax.swing.JPanel;

public class ElbiWars_Troops extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int xcoordinate = 0;
	int ycoordinate = 0;
	int width = 20;
	int height = 20;
	int type = 0;
	int speed = 100;
	int quantity = 0;
	int status = 0;
	int hp = 1000;
	int owner = 0;
	double range = 10.0;
	ElbiWars_Building a;
	
	public ElbiWars_Troops(int xcoordinate, int ycoordinate, int type, int owner) {
		this.xcoordinate = xcoordinate;
		this.ycoordinate = ycoordinate;
		this.type = type;
		this.owner = owner;
	}

	public Rectangle getBounds(){
		return new Rectangle(xcoordinate, ycoordinate, width, height);
	}
	
	


	
	@Override
	public void run() {
		while(true){
			this.xcoordinate++;
			this.ycoordinate++;
			try {
				Thread.sleep(this.speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
