package battleship;

public class Battleship extends Ship {

	public Battleship() {
		// default constructor with a length of 4
		super(4);
	}
	
	/**
	 * returns the type of the ship in lower case 
	 */
	@Override
	public String getShipType() {
		return "battleship";
	}
	
	
	
	
}
