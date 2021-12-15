package battleship;

import java.util.Random;
/**
 * 
 * @author Hongyi Zhu
 * The ocean class holds the ships and keep track of the macro variables such as number of ships sunk and number of shots fired
 */
public class Ocean {
	//establishing the Ship array, class variables, and final static class variables
	private Ship[][] ships = new Ship[10][10];
	private int shotsFired, hitCount, shipSunk;
	final static int BattleshipNum = 1;
	final static int CruiserNum = 2;
	final static int DestroyerNum = 3;
	final static int SubmarineNum = 4;
	final static int TotalShipCount = 10;


	public Ocean(){
		//initialize each location to an empty sea in the construtor 
		for(int i = 0; i < ships.length; i++) {
			for(int j = 0; j < ships[i].length; j++) {
				ships[i][j] = new EmptySea();
				ships[i][j].setBowRow(i);
				ships[i][j].setBowColumn(j);
			}
		}
	}
	
/**
 * getter for the ship array, the ship class can directly modify the ships in the array
 * @return ship array
 */
	Ship[][] getShipArray() {
		return this.ships;	
	}
	
	/**
	 * returns the shots fired so far 
	 * @return the number of shots fired
	 */
	int getShotsFired() {
		return shotsFired;
	}
	
	/**
	 * returns the hit count so far
	 * @return total hit count
	 */
	int getHitCount() {
		return hitCount;
	}
	
	/**
	 * returns the number of ships sunk
	 * @return number of ships sunk
	 */
	int getShipsSunk() {
		return shipSunk;
	}
	
	/**
	 * returns true if all ships have been sunk
	 * @return boolean whether all ships have been sunk
	 */
	boolean isGameOver() {
		if(this.shipSunk == TotalShipCount) {
			return true;
		}else {
			return false;
		}
	}
	
	/*
	 * returns true if the underlying type of the ship is "empty"
	 */
	boolean isOccupied(int row, int column) {
		if(this.getShipArray()[row][column].getShipType() != "empty") {
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * places all ships randomly by:
	 * 1. checking if the location is valid
	 * 2. placing the ship and setting the parameters of the ship accordingly
	 */
	void placeAllShipsRandomly() {
		Random rand = new Random();
		// rand.setSeed(0); // for debugging purposes
		
		// ship counter to keep track of the number of ships placed for a specific type of ship
		int shipCounter = 0;
		int row, column;
		boolean horizontal;		

		// while we have not placed enough ships for that type 
		while(shipCounter != BattleshipNum) {	
			Ship bs = new Battleship();	
			// generate random numbers for row (0-9), column (0-9), and horizontal (true/false) 
			row = rand.nextInt(9);
			column = rand.nextInt(9);
			horizontal = rand.nextBoolean();
			// check whether the ship of the specific type can be placed in the randomly generated location
			if(bs.okToPlaceShipAt(row, column, horizontal, this)) {
				//set the row, column, and horizontal variables to the values generated
				bs.setBowColumn(column);
				bs.setBowRow(row);
				bs.setHorizontal(horizontal);
				// place the ship in the grid
				bs.placeShipAt(row, column, horizontal, this);
				// increase ship counter for this type of ship
				shipCounter++;
			}
		}

		
		// reset the ship counter variable to zero to place cruisers
		shipCounter = 0;		
		while(shipCounter != CruiserNum) {
			Ship cru = new Cruiser();
			row = rand.nextInt(9);
			column = rand.nextInt(9);
			horizontal = rand.nextBoolean();
			if(cru.okToPlaceShipAt(row, column, horizontal, this)) {
				cru.setBowColumn(column);
				cru.setBowRow(row);
				cru.setHorizontal(horizontal);
				cru.placeShipAt(row, column, horizontal, this);
				shipCounter++;
			}			
		}

		// reset the ship counter variable to zero to place destroyers
		shipCounter = 0;		
		while(shipCounter != DestroyerNum) {
			Ship des = new Destroyer();
			row = rand.nextInt(9);
			column = rand.nextInt(9);
			horizontal = rand.nextBoolean();
			if(des.okToPlaceShipAt(row, column, horizontal, this)) {
				des.setBowColumn(column);
				des.setBowRow(row);
				des.setHorizontal(horizontal);
				des.placeShipAt(row, column, horizontal, this);
				shipCounter++;
			}
		}	
	
		// reset the ship counter variable to zero to place submarines
		shipCounter = 0;	
		while(shipCounter != SubmarineNum) {
			Ship sub = new Submarine();
			row = rand.nextInt(9);
			column = rand.nextInt(9);
			horizontal = rand.nextBoolean();
			if(sub.okToPlaceShipAt(row, column, horizontal, this)) {
				sub.setBowColumn(column);
				sub.setBowRow(row);
				sub.setHorizontal(horizontal);
				sub.placeShipAt(row, column, horizontal, this);
				shipCounter++;
			}
		}
	}
	
	
	/**
	 * print the grid to console with borders 
	 */
	void print() {
		// iterate through the 1st dimension of the ship array (rows) 
		for(int i = 0; i < ships.length; i++) {
			// on the first row...
			if(i==0) {
				// print the space at the top left hand corner
				System.out.print("  ");
				// print the column headers, separated by a space
				for(int z = 0; z < ships.length; z++) {
					System.out.print(z);
					System.out.print(" ");
				}
				// go to the next line after printing the header
				System.out.println();
			}
			
			// print the row title and a space
			System.out.print(i);
			System.out.print(" ");
			
			// the heavy lifting
			// for each element in the second dimension (column)
			for(int j = 0; j < ships[i].length; j++) {
				// grab the value of the bow  
				int shipR = ships[i][j].getBowRow();
				int shipC = ships[i][j].getBowColumn();
				
				// if the ship is horizontal, distance from bow is calculated as bow column - j
				// j being the index of the column currently printing
				if(ships[i][j].isHorizontal()) {
					int distanceFromBow = shipC - j;
					// if the ship has been hit
					if(ships[i][j].getHit()[distanceFromBow]==true) {
						if(ships[i][j].getShipType() == "empty") {
							// if the location is empty sea, display "-"
							System.out.print("-");
						}else {
							// if the ship is not empty sea, then it should display either "x" or "s"
							// based on the toString method
							System.out.print(ships[i][j].toString());
						}						
					}else {
						// display "." if the ship has not been hit
						System.out.print(".");
					}						
				}else {
					// the ship is not horizontal
					int distanceFromBow = shipR - i;
					// if the ship has been hit
					if(ships[i][j].getHit()[distanceFromBow]==true) {
						// if the location is empty sea, display "-"
						if(ships[i][j].getShipType() == "empty") {
							System.out.print("-");
						}else {
							// if the ship is not empty sea, then it should display either "x" or "s"
							// based on the toString method
							System.out.print(ships[i][j].toString());
						}						
					}else {
						// display "." if the ship has not been hit
						System.out.print(".");
					}					
				}
					// print a space to separate ships and line up with column titles
					System.out.print(" ");
					// go to next line if end of the array
					if(j == ships[i].length-1) {
					System.out.println();
				}
			}
		}
		
	}
	
	
	/**
	 * this method governs the shooting by the ocean class
	 * it records hits in the hit array of the ship and returns
	 * true if it hits a ship
	 * @param row - a row number representing the grid to shoot
	 * @param column - a column number representing the grid to shoot
	 * @return whether the shot hit a ship
	 */
	boolean shootAt(int row, int column) {
		// increasing shots fired whether hitting a ship or empty sea
		this.shotsFired++;
		
		// create a new ship and let it point to the ship (or empty ocean)
		// in the ocean with the (row, column) pair to avoid long code 
		Ship tempS = this.getShipArray()[row][column];

		//if it's empty ocean update the hit array and done
		if(tempS.getShipType()== "empty") {
			tempS.getHit()[0] = true;
			return false;

		// if the ship is sunk, return false
		}else if (tempS.isSunk()) {
			return false;

		// we are here if it's a ship and hasn't been sunk
		}else {
			// increase hit count because it hit a ship
			this.hitCount++;
			
			// call the shootAt method in the Ship class
			tempS.shootAt(row, column);
			
			// record if the ship is sunk after calling shootAt from Ship class and print a message
			if(tempS.isSunk()) {
				System.out.println("You just sunk a "+ tempS.getShipType());
				shipSunk++;
				}
			return true;
			}
		}
}
