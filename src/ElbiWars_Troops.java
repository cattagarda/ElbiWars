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
	int xcoordinate = 0;
	int ycoordinate = 0;
	int width = 20;
	int height = 20;
	int type = 0;
	int speed = 100;
	int quantity = 0;
	int status = 0;
	int hp = 1000;
	double range = 10.0;
	String filename = "";
	ElbiWars_Building a;
	int owner = 0;
	ImageIcon img;
	
	public ElbiWars_Troops(int xcoordinate, int ycoordinate, int type, int owner) {
		this.xcoordinate = xcoordinate;
		this.ycoordinate = ycoordinate;
		this.type = type;
		this.owner = owner;
		
	}

	
	@Override
	public void run() {
		
		while(true){
			try {
				Thread.sleep(this.speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
	    //Convert to Java2D Object
	    Graphics2D g2 = (Graphics2D) g;
	    
	    
	}
}
