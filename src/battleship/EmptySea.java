package battleship;

public class EmptySea extends Ship{

	/*
	 * constructor
	 */
	public EmptySea() {
		super(1);
	}
	
	@Override
	/*
	 * return "empty" for type
	 */
	public String getShipType() {
		return "empty";
	}
	
	@Override
	/*
	 * overridden the method from super class to record hit and return false
	 */
	boolean shootAt(int row, int column) {
		this.getHit()[0] = true;
		return false;
	}
	
	@Override
	/*
	 * empty sea cannot be sunk
	 */
	boolean isSunken() {
		return false;
	}
	
	/*
	 * empty sea will always return "-" when hit
	 */
	public String toString() {
			return "-";
	}
}
