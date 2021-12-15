package battleship;

public class Destroyer extends Ship {

	public Destroyer() {
		// default constructor with a length of 2
		super(2);
	}
	
	/**
	 * returns the type of the ship in lower case 
	 */
	@Override
	public String getShipType() {
		return "destroyer";
	}
	

}
