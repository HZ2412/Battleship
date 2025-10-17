package battleship;
import java.util.Scanner;
import java.util.Random;

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
		// create ocean for human player
		Ocean humanOcean = new Ocean();
		humanOcean.placeAllShipsRandomly();
		
		// create ocean for computer player
		Ocean computerOcean = new Ocean();
		computerOcean.placeAllShipsRandomly();

		Scanner sc = new Scanner(System.in);
		
		// while the game is not over (either player still has ships)
		while(!humanOcean.isGameOver() && !computerOcean.isGameOver()) {
			// Human's turn
			System.out.println("\n=== YOUR TURN ===");
			System.out.println("\nYour Ocean:");
			humanOcean.print();
			System.out.println("\nComputer's Ocean:");
			computerOcean.print();
			
			// ask for row column pair for input
			System.out.println("Enter \"row, column\" to shoot at computer's ocean:");
			// create an integer array to store the values parsed
			int[] coordinates = new int[2];
			// parse the line entered separated by comma and return true/false for successfully parsed
			if(parseCoordinates(sc, coordinates)) {
				// shoot at computer's ocean
				computerOcean.shootAt(coordinates[0], coordinates[1]);
			}	
			
			// Check if human won
			if(computerOcean.isGameOver()) {
				break;
			}
			
			// Computer's turn
			System.out.println("\n=== COMPUTER'S TURN ===");
			computerShoot(humanOcean);
		}
		
		// after the game is over, print both oceans and statistics
		System.out.println("\n=== GAME OVER ===");
		System.out.println("\nYour Ocean:");
		humanOcean.print();
		System.out.println("\nComputer's Ocean:");
		computerOcean.print();
		
		if(computerOcean.isGameOver()) {
			System.out.println("\n*** YOU WIN! ***");
			System.out.println("You sank all computer's ships!");
		} else {
			System.out.println("\n*** COMPUTER WINS! ***");
			System.out.println("Computer sank all your ships!");
		}
		
		System.out.println("\nYour Statistics:");
		System.out.println("Shots fired: " + computerOcean.getShotsFired());
		System.out.println("Hit Count: " + computerOcean.getHitCount());
		System.out.println("Ships sunk: " + computerOcean.getShipsSunk());
		
		System.out.println("\nComputer's Statistics:");
		System.out.println("Shots fired: " + humanOcean.getShotsFired());
		System.out.println("Hit Count: " + humanOcean.getHitCount());
		System.out.println("Ships sunk: " + humanOcean.getShipsSunk());

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
	
	/**
	 * Computer shoots at a random location on the human's ocean
	 * If the location has already been hit, it tries again until finding an unhit location
	 * @param humanOcean - the ocean belonging to the human player
	 */
	static void computerShoot(Ocean humanOcean) {
		Random rand = new Random();
		int row, column;
		boolean validShot = false;
		
		// Keep trying until we find a location that hasn't been shot at
		while(!validShot) {
			row = rand.nextInt(10);
			column = rand.nextInt(10);
			
			// Check if this location has already been hit
			Ship ship = humanOcean.getShipArray()[row][column];
			int distanceFromBow;
			
			if(ship.isHorizontal()) {
				distanceFromBow = ship.getBowColumn() - column;
			} else {
				distanceFromBow = ship.getBowRow() - row;
			}
			
			// If this location hasn't been hit yet, it's a valid shot
			if(!ship.getHit()[distanceFromBow]) {
				validShot = true;
				System.out.println("Computer shoots at (" + row + ", " + column + ")");
				humanOcean.shootAt(row, column);
			}
		}
	}
}
