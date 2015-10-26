package eLBiWarsClient;


public class ElbiWars_Building extends ElbiWars_Troops{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int xcoordinate = 0;
	int ycoordinate = 0;
	int type = 0;
	int quantity = 0;
	int width = 50;
	int height = 50;
	
	public ElbiWars_Building(int xcoordinate, int ycoordinate, int type) {
		super(xcoordinate, ycoordinate, type);
		this.xcoordinate = xcoordinate;
		this.ycoordinate = ycoordinate;
		this.type = type;
	}
	
}
