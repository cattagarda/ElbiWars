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
	double range = 10.0;
	ElbiWars_Building a;
	
	public ElbiWars_Troops(int xcoordinate, int ycoordinate, int type) {
		this.xcoordinate = xcoordinate;
		this.ycoordinate = ycoordinate;
		this.type = type;
	}

	public Rectangle getBounds(){
		return new Rectangle(xcoordinate, ycoordinate, width, height);
	}
	
	public void computepos(){	
		try{
			float slope=(a.ycoordinate-this.ycoordinate)/(a.xcoordinate-this.xcoordinate);
			float b=a.ycoordinate-(a.xcoordinate*slope);
			if (a.xcoordinate>this.xcoordinate){
				this.xcoordinate++;
			}else{ 
				this.xcoordinate--;
			}
			this.ycoordinate=(int) (((slope)*this.xcoordinate)+b);
			if(Math.sqrt((a.xcoordinate-this.xcoordinate)*(a.xcoordinate-this.xcoordinate)+(a.ycoordinate-this.ycoordinate)*(a.ycoordinate-this.ycoordinate))<range+20){
					this.status = 1;
			}
		}catch(Exception e){
			
		}

}
	
	@Override
	public void run() {
		while(true){
			this.computepos();
			try {
				Thread.sleep(this.speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
