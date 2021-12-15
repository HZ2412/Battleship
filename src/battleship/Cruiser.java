package battleship;

public class Cruiser extends Ship {

	public Cruiser() {
		// default constructor with a length of 3
		super(3);
	}

	/**
	 * returns the type of the ship in lower case 
	 */
	@Override
	public String getShipType() {
		return "cruiser";
	}
	
}
