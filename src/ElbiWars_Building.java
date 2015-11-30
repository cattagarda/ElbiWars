import javax.swing.ImageIcon;


public class ElbiWars_Building extends ElbiWars_Troops implements Runnable{
	private static final long serialVersionUID = 1L;
	int xcoordinate = 0;
	int ycoordinate = 0;
	int type = 0;
	int quantity = 0;
	int width = 50;
	int height = 50;
	int team = 1;
	ImageIcon imageIcon;
	
	public ElbiWars_Building(int xcoordinate, int ycoordinate, int type, int owner) {
		super(xcoordinate, ycoordinate, type, owner);
		// TODO Auto-generated constructor stub
		this.xcoordinate = xcoordinate;
		this.ycoordinate = ycoordinate;
		this.type = type;
		this.team = owner;
		
		this.setBuilding();
	}
	
	@Override
	public void run() {
		
	}
	
	private void setBuilding(){
		if(this.team == 1){
			switch(this.type){
				case 1: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/physci.gif"));
					this.width = 60;
					this.height = 50;
					break;
				case 2: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/PHD Tower.gif"));
					this.width = 40;
					this.height = 40;
					break;
				case 3: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Masteral.gif"));
					this.width = 40;
					this.height = 40;
					break;
				case 4: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Proffesor.gif"));
					this.width = 40;
					this.height = 40;
					break;
				case 5: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Instructor.gif"));
					this.width = 40;
					this.height = 40;
					break;
				case 6: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/SA.gif"));
					this.width = 40;
					this.height = 40;
					break;
			}
		} else if(this.team == 2){
			switch(this.type){
				case 1: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/mainlib.gif"));
					this.width = 60;
					this.height = 60;
					break;
				case 2: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/PHD Tower.gif"));
					this.width = 40;
					this.height = 40;
					break;
				case 3: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Masteral.gif"));
					this.width = 40;
					this.height = 40;
					break;
				case 4: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Proffesor.gif"));
					this.width = 40;
					this.height = 40;
					break;
				case 5: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Instructor.gif"));
					this.width = 40;
					this.height = 40;
					break;
				case 6: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/SA.gif"));
					this.width = 40;
					this.height = 40;
					break;
				}
		} else {
			switch(this.type){
				case 1: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/hum.gif"));
					this.width = 60;
					this.height = 60;
					break;
				case 2: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/PHD Tower.gif"));
					this.width = 40;
					this.height = 40;
					break;
				case 3: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Masteral.gif"));
					this.width = 40;
					this.height = 40;
					break;
				case 4: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Proffesor.gif"));
					this.width = 40;
					this.height = 40;
					break;
				case 5: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/Instructor.gif"));
					this.width = 40;
					this.height = 40;
					break;
				case 6: this.imageIcon = new ImageIcon(ElbiWars_GameBase.class.getResource("Images/SA.gif"));
					this.width = 40;
					this.height = 40;
					break;
			}
		}
		
	}

}
