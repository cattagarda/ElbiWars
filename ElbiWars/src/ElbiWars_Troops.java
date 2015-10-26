import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class ElbiWars_Troops extends JPanel implements Runnable {
	int xcoordinate = 0;
	int ycoordinate = 0;
	int type = 0;
	int speed = 0;
	
	public ElbiWars_Troops(int xcoordinate, int ycoordinate, int speed, int type) {
		this.xcoordinate = xcoordinate;
		this.ycoordinate = ycoordinate;
		this.speed = speed;
		this.type = type;
	}

	@Override
	public void run() {
		while(true){
			this.xcoordinate--;
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
