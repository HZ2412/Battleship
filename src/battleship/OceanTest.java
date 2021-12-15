package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++; //battleship is misspelled 
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		
		//case 2
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		
		
		//case 3
		Ship emptySea = new EmptySea();
		row = 4;
		column = 4;
		horizontal = true;
		emptySea.placeShipAt(row, column, horizontal, ocean);
		assertFalse(ocean.isOccupied(4, 4));
		
		
		//case 4
		Ship battleship = new Battleship();
		row = 9;
		column = 9;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(9, 9));
		assertTrue(ocean.isOccupied(8, 9));
		assertFalse(ocean.isOccupied(5, 9));

	}

	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		
		// case 2
		Cruiser cruiser = new Cruiser();
		row = 5;
		column = 5;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(5, 5));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(4, 5));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(3, 5));
		assertTrue(cruiser.isSunk());
		
		// case 3
		EmptySea emptySea = new EmptySea();
		row = 8;
		column = 8;
		horizontal = true;
		emptySea.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(ocean.shootAt(8, 8));
		assertFalse(emptySea.isSunk());
	
	}

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		//case 2
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());

		//case 3
		Ship battleship = new Battleship();
		row = 9;
		column = 9;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(9, 9));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(8, 9));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(7, 9));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(6, 9));
		assertTrue(battleship.isSunk());
		
		assertEquals(10, ocean.getShotsFired());

		//case 4
		Ship emptySea = new EmptySea();
		row = 8;
		column = 7;
		horizontal = false;
		emptySea.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(ocean.shootAt(8, 7));
		assertFalse(emptySea.isSunk());
		assertEquals(11, ocean.getShotsFired());

	}

	@Test
	void testGetHitCount() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		
		
		// case 2: shooting at empty sea should return false and should not raise hit count
		Ship emptySea = new EmptySea();
		row = 8;
		column = 7;
		horizontal = false;
		emptySea.placeShipAt(row, column, horizontal, ocean);		
		
		assertFalse(ocean.shootAt(8, 7));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		
		
		Ship submarine = new Submarine();
		row = 4;
		column = 4;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);		
		
		assertTrue(ocean.shootAt(4, 4));
		assertTrue(submarine.isSunk());
		assertEquals(2, ocean.getHitCount());
		

	}
	
	@Test
	void testGetShipsSunk() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		
		
		// case 2
		Ship submarine = new Submarine();
		row = 4;
		column = 4;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);		
		
		assertTrue(ocean.shootAt(4, 4));
		assertTrue(submarine.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		
		
		// case 3 shooting at empty ocean does not increase hit count or ship sunk
		Ship emptySea = new EmptySea();
		row = 9;
		column = 1;
		horizontal = false;
		emptySea.placeShipAt(row, column, horizontal, ocean);		
		
		assertFalse(ocean.shootAt(9, 1));
		assertFalse(emptySea.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		
		
		// case 4 fully sinking a battleship
		Ship battleship = new Battleship();
		row = 9;
		column = 9;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);		
		
		assertTrue(ocean.shootAt(9, 9));
		assertFalse(battleship.isSunk());
		assertEquals(3, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		
		// shoot it again
		assertTrue(ocean.shootAt(8, 9));
		assertFalse(battleship.isSunk());
		assertEquals(4, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		
		// and again
		assertTrue(ocean.shootAt(7, 9));
		assertFalse(battleship.isSunk());
		assertEquals(5, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
				
		// and sunk
		assertTrue(ocean.shootAt(6, 9));
		assertTrue(battleship.isSunk());
		assertEquals(6, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
	}

	@Test
	void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		//case 2
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertEquals("destroyer", shipArray[1][5].getShipType());
		
		//case 3
		EmptySea emptySea = new EmptySea();
		row = 5;
		column = 5;
		horizontal = false;
		emptySea.placeShipAt(row, column, horizontal, ocean);
		
		assertEquals("empty", shipArray[5][5].getShipType());
		
		
		
		
		

		
		
		
		
	}

}
