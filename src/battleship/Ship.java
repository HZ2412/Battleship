package battleship;

/**
 * the abstract class ship contains the common methods and variables that will be common for each ship subclass
 * @author Hongyi
 *
 */
public abstract class Ship {
	// bowRow bowColumn to record the location of the bow, length for length of the ship
	private int bowRow, bowColumn, length;
	private boolean horizontal;
	private boolean [] hit; // each of the element of this 'hit' array represents a square of the ship
	
	/**
	 * constructor setting both length and hit array
	 * @param length
	 */
	public Ship(int length) {
		this.length = length;
		this.hit = new boolean[length];
	}

	/**
	 * default constructor setting the length to 1 
	 */
	public Ship() {
		this.length = 1;
		this.hit = new boolean[length];
	}
	
	/**
	 * get the class variable bowRow
	 * @return bow row of the ship
	 */
	public int getBowRow() {
		return bowRow;
	}

	/**
	 * set the class variable bowRow
	 * @param bowRow
	 */
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}

	/**
	 * returns the bow column of the ship
	 * @return bow column
	 */
	public int getBowColumn() {
		return bowColumn;
	}

	/**
	 * set the class variable bowColumn
	 * @param bowColumn
	 */
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}

	/**
	 * get the length of the ship
	 * @return length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * set the length of the ship
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * return the class variable horizontal
	 * @return horizontal
	 */
	public boolean isHorizontal() {
		return horizontal;
	}
	
	/**
	 * set the class variable horizontal
	 * @param horizontal
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * return the hit array
	 * @return hit array
	 */
	public boolean[] getHit() {
		return hit;
	}

	/**
	 * abstract method to be overridden in subclasses
	 * @return
	 */
	public abstract String getShipType();
	
	
	/**
	 * I am not speaking Korean and I agree the naming could be improved
	 * NooBaE it stands for "Not Out Of Bound And Empty"
	 * this function is needed to make sure row, column pairs are within the range
	 * This function is called for two purposes: 
	 * 1. make sure the underlying grid is an empty ocean
	 * 2. make sure the edge of the ship is not sticking out of the grid  
	 * @param row
	 * @param column
	 * @param ocean
	 * @return
	 */
	public boolean NooBaE(int row, int column, Ocean ocean) {
		// first check if the r,c are in the grid
		// they are, make sure an empty ocean in that location
		if(row <= 9 && column <=9 && row >=0 && column >=0)
		{
			if(ocean.getShipArray()[row][column].getShipType()=="empty") {
				return true;
			}
		// if the r,c pair is one out of bound of the array (-1 or 10), return true
		// this is because when checking the squares surrounding the ship
		// if a ship is touching the side of the grid, -1 or 10 can result
		// however, if it's greater than one out of bound, then it will return false as the ship is sticking out
		}else if(row == -1 || row == 10 || column == -1 || column == 10) {
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * checks if the bow of the ship can be placed in the (row, column) location by calling 
	 * Not Out Of Bound And Empty (NooBaE) function on:
	 * 1. the bow
	 * 2. the body of the ship
	 * 3. the "wall" -  squares surrounding the bow and body of the ship
	 * the function basically draws a rectangular wall surrounding the ship, and make sure that
	 * the ship is not sticking out of the ocean, and there are no ships on the wall 
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return
	 */
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {

		// for a horizontal battleship, the walls form a rectangle, surrounding the battleship
		// the wall is made up of 14 squares
		if(this.getShipType() == "battleship" && horizontal) {
			if(		// the bow
					NooBaE(row, column, ocean) && 
					// the three body
					NooBaE(row, column-1, ocean) && NooBaE(row, column-2, ocean) && NooBaE(row, column-3, ocean) &&
					// the square directly behind (left of) the tail and in front (right) of the bow
					NooBaE(row, column-4, ocean) &&	NooBaE(row, column+1, ocean) &&
					//the same row above (on the left of) the ship
					NooBaE(row-1, column, ocean) && NooBaE(row-1, column-1, ocean) && NooBaE(row-1, column-2, ocean) && 
					NooBaE(row-1, column-3, ocean) && NooBaE(row-1, column-4, ocean) && NooBaE(row-1, column+1, ocean) &&
					//the same row below (on the right of) the ship
					NooBaE(row+1, column, ocean) && NooBaE(row+1, column-1, ocean) && NooBaE(row+1, column-2, ocean) && 
					NooBaE(row+1, column-3, ocean) && NooBaE(row+1, column-4, ocean) && NooBaE(row+1, column+1, ocean)) {
					return true;
			}}
		
		// similarly for not horizontal battleship, the walls surrounding the ship is vertical
		if(this.getShipType() == "battleship" && !horizontal) {
			if(NooBaE(row, column, ocean) && 
					NooBaE(row+1, column, ocean) && NooBaE(row-1, column, ocean) && NooBaE(row-2, column, ocean) && 
					NooBaE(row-3, column, ocean) && NooBaE(row-4, column, ocean) && NooBaE(row, column-1, ocean) && 
					NooBaE(row+1, column-1, ocean) && NooBaE(row-1, column-1, ocean) && NooBaE(row-2, column-1, ocean) && 
					NooBaE(row-3, column-1, ocean) && NooBaE(row-4, column-1, ocean) && NooBaE(row, column+1, ocean) && 
					NooBaE(row+1, column+1, ocean) && NooBaE(row-1, column+1, ocean) && NooBaE(row-2, column+1, ocean) && 
					NooBaE(row-3, column+1, ocean) && NooBaE(row-4, column+1, ocean)) {
					return true;
			}}
		
		
		// same as the battleship except the length of the ship and the wall are both 1 less
		// the wall is made up of 12 squares
		if(this.getShipType() == "cruiser" && horizontal) {
			if(NooBaE(row, column, ocean) && 
					NooBaE(row, column-1, ocean) && NooBaE(row, column-2, ocean) && NooBaE(row, column-3, ocean) && 
					NooBaE(row, column+1, ocean) && NooBaE(row-1, column, ocean) && NooBaE(row-1, column-1, ocean) && 
					NooBaE(row-1, column-2, ocean) && NooBaE(row-1, column-3, ocean) && NooBaE(row-1, column+1, ocean) &&
					NooBaE(row+1, column, ocean) && NooBaE(row+1, column-1, ocean) && NooBaE(row+1, column-2, ocean) && 
					NooBaE(row+1, column-3, ocean) && NooBaE(row+1, column+1, ocean)) {
					return true;
			}}
		
		if(this.getShipType() == "cruiser" && !horizontal) {
			if(NooBaE(row, column, ocean) && 
					NooBaE(row+1, column, ocean) && NooBaE(row-1, column, ocean) && NooBaE(row-2, column, ocean) && 
					NooBaE(row-3, column, ocean) && NooBaE(row, column-1, ocean) && NooBaE(row+1, column-1, ocean) && 
					NooBaE(row-1, column-1, ocean) && NooBaE(row-2, column-1, ocean) && NooBaE(row-3, column-1, ocean) &&
					NooBaE(row, column+1, ocean) && NooBaE(row+1, column+1, ocean) && NooBaE(row-1, column+1, ocean) && 
					NooBaE(row-2, column+1, ocean) && NooBaE(row-3, column+1, ocean)) {
					return true;
			}}
		
		// shed off another length from the ship and surrounding walls. 10 squares make up the wall
		if(this.getShipType() == "destroyer" && horizontal) {
			if(NooBaE(row, column, ocean) &&
					NooBaE(row, column-1, ocean) && NooBaE(row, column-2, ocean) && NooBaE(row, column+1, ocean) &&
					NooBaE(row-1, column, ocean) && NooBaE(row-1, column-1, ocean) && NooBaE(row-1, column-2, ocean) && 
					NooBaE(row-1, column+1, ocean) && NooBaE(row+1, column, ocean) && NooBaE(row+1, column-1, ocean) && 
					NooBaE(row+1, column-2, ocean) && NooBaE(row+1, column+1, ocean)) {
					return true;
			}}
		
		if(this.getShipType() == "destroyer" && !horizontal) {
			if(NooBaE(row, column, ocean) && 
					NooBaE(row+1, column, ocean) && NooBaE(row-1, column, ocean) && NooBaE(row-2, column, ocean) &&
					 NooBaE(row, column-1, ocean) && NooBaE(row+1, column-1, ocean) && NooBaE(row-1, column-1, ocean) && 
					 NooBaE(row-2, column-1, ocean) && NooBaE(row, column+1, ocean) && NooBaE(row+1, column+1, ocean) && 
					 NooBaE(row-1, column+1, ocean) && NooBaE(row-2, column+1, ocean)) {
					return true;
			}}
		

		// submarine does not care if it's horizontal and only check the 8 squares surrounding the bow
		if(this.getShipType() == "submarine") {
			if(NooBaE(row, column, ocean) 
					&& NooBaE(row, column-1, ocean)  && NooBaE(row, column+1, ocean)
					&& NooBaE(row-1, column, ocean) && NooBaE(row+1, column, ocean)
					&& NooBaE(row-1, column-1, ocean) && NooBaE(row+1, column+1, ocean)
					&& NooBaE(row-1, column+1, ocean) && NooBaE(row+1, column-1, ocean))   {
					return true;
			}}
		
		// if none of the condition is satisfied, return false
		return false;
		
	}
	
	/**
	 * places the ship in a given location with direction of facing right (horizontal) and down (not horizontal)
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
			// set the instance variables
			this.setBowRow(row);
			this.setBowColumn(column);
			this.setHorizontal(horizontal);
		
			// placing the bow then the body according to a set of prescribed directions
			// for battleship
			if(this.getShipType() == "battleship" && horizontal) {
				ocean.getShipArray()[row][column] = this;
				ocean.getShipArray()[row][column - 1] = this;
				ocean.getShipArray()[row][column - 2] = this;
				ocean.getShipArray()[row][column - 3] = this;				
			}
			if(this.getShipType() == "battleship" && !horizontal) {
				ocean.getShipArray()[row][column] = this;
				ocean.getShipArray()[row - 1][column] = this;
				ocean.getShipArray()[row - 2][column] = this;
				ocean.getShipArray()[row - 3][column] = this;
			}
		
			// for cruiser
			if(this.getShipType() == "cruiser" && horizontal) {
				ocean.getShipArray()[row][column] = this;
				ocean.getShipArray()[row][column - 1] = this;
				ocean.getShipArray()[row][column - 2] = this;	
			}
			
			
			if(this.getShipType() == "cruiser" && !horizontal) {
				ocean.getShipArray()[row][column] = this;
				ocean.getShipArray()[row - 1][column] = this;
				ocean.getShipArray()[row - 2][column] = this;
			}
		
			// for destroyer
			if(this.getShipType() == "destroyer" && horizontal) {
				ocean.getShipArray()[row][column] = this;
				ocean.getShipArray()[row][column - 1] = this;				
			}
			if(this.getShipType() == "destroyer" && !horizontal) {
				ocean.getShipArray()[row][column] = this;
				ocean.getShipArray()[row - 1][column] = this;
			}

			// for submarine
			if(this.getShipType() == "submarine") {
				ocean.getShipArray()[row][column] = this;			
			}
		
	}
	
	/**
	 * this method checks if the (r,c) pair is part of the ship, and if so update the hit array accordingly
	 * returns true if the ship is "hit", false otherwise
	 * @param row
	 * @param column
	 * @return
	 */
	boolean shootAt(int row, int column) {
		int bowR = this.getBowRow();
		int bowC = this.getBowColumn();
		
		// if the ship is already sunk before hitting, return false
		if(this.isSunken()) {
			return false;
		}
		
		// if the ship is horizontal and the (r,c) pair satisfies the following conditions:
		// 1. if the ship is horizontal
		// 		I. bowR - row = 0, meaning the location to shoot is on the same row as the ship
		// 		II. bowC - column < ship length, meaning it's not shooting beyond (left of) the tail
		// 		II. bowC - column >= 0, meaning it's not shooting beyond (right of) the bow 
		// Similarly 2. if the ship is not horizontal
		// 		I. bowC - column = 0, meaning the location to shoot is on the same column as the ship
		// 		II. bowR - row < ship length, meaning it's not shooting beyond (above) the tail
		// 		II. bowR - row >= 0, meaning it's not shooting beyond (below) the bow 
		if(this.isHorizontal() && bowR - row == 0 && bowC - column < this.length && bowC - column >= 0) {	
			this.getHit()[bowC - column]= true;
				return true;
		}else if(bowC - column == 0 && bowR - row < this.length && bowR - row >= 0) {
			this.getHit()[bowR - row] = true;
			return true;
		// if neither criteria is met return false
		}else {
			return false;
		}
	}
	
	
	/**
	 * returns the true if all elements of the hit array is true
	 * @return
	 */
	boolean isSunken() {
		for(int i = 0; i<this.hit.length; i++)
		{
			if(!this.hit[i]) {
				return false;
			}
		}
		return true;
	}
	
	
	/*
	 * this method returns the character "s" or "x", based on whether it's sunk
	 */
	public String toString() {
		if(this.isSunken()) {
			return "s";
		}else {
			return "x";
		}
	}
	
	
	

	
}
