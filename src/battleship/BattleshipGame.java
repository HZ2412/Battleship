package battleship;
import java.util.Scanner;

/**
 * Driver class for battleship game
 * @author Hongyi Zhu
 *
 */
public class BattleshipGame {
	
	/**
	 * main method for the battle ship game
	 * @param args
	 */
	public static void main(String[] args) {
		// create a new ocean instance variable
		Ocean oc = new Ocean();
		// place ships randomly in the ocean
		oc.placeAllShipsRandomly();

		Scanner sc = new Scanner(System.in);
		
		// while the game is not over
		while(!oc.isGameOver()) {
			//print the ocean
			oc.print();
			// ask for row column pair for input
			System.out.println("Enter row column as 'row, column'.");
			// create an integer array to store the values parsed
			int[] coordinates = new int[2];
			// parse the line entered separated by comma and return true/false for successfully parsed
			if(parseCoordinates(sc, coordinates)) {
				// if successfully parsed inputs, call shootAt to see if anything was hit
				// the shootAt method calls shootAt in the ship class, which updates the hit array
				if(oc.shootAt(coordinates[0], coordinates[1])){
					System.out.println("Hit.");
				}else {
					System.out.println("Miss.");
				}
			}	
		}
				// after the game is over, print the ocean again and some statistics
				oc.print();
				System.out.println("Shots fired: " + oc.getShotsFired());
				System.out.println("Hit Count: " + oc.getHitCount());
				System.out.println("Ships sunk: " + oc.getShipsSunk());
				System.out.println("The game is over. " + "20 shots were required, and " 
				+ oc.getShotsFired() + " shots have been fired.");

		// close the scanner
		sc.close();
	}
	
	/**
	 * this function parses the input from user and returns whether parsing was successful
	 * @param sc - the scanner created in Main
	 * @param co - an array of integers to hold the coordinates (r,c)
	 * @return boolean - whether the parsing was successful
	 */
	static boolean parseCoordinates(Scanner sc, int[] co) {
		// get the next line as a string
		String line = sc.nextLine();
		// split the string by comma and store into an array
		String [] tempString = line.split(",");

		try {
			int x, y;
			// try to parse the string split as number
			x = Integer.parseInt(tempString[0].trim());
			y = Integer.parseInt(tempString[1].trim());
		
			// check the validity of the numbers entered
			if(x <= 9 && x >= 0 && y<=9 && y>=0) {
				co[0] = x;
				co[1] = y;
				return true;
			}else {
			// print a message if the coordinates are out of range
				System.out.println("Coordinates entered are not valid. Please try again");
				return false;
			}
		
		// throw exception if parse failed
		}catch(Exception e){
			System.out.println("The format entered is not valid. Please try agin.");
			return false;
			
		}
		
	}
}
