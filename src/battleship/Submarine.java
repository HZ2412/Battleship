package battleship;

public class Submarine extends Ship{

	public Submarine() {
		// default constructor with a length of 1
		super(1);
	}
	
	/**
	 * returns the type of the ship in lower case 
	 */
	@Override
	public String getShipType() {
		return "submarine";
	}
	

}
