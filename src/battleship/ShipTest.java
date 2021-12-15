package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		// case 2
		ship = new Cruiser();
		assertEquals(3, ship.getLength());
		
		// case 3
		ship = new Destroyer();
		assertEquals(2, ship.getLength());
		
		ship = new Submarine();
		assertEquals(1, ship.getLength());

	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		// case 2
		Ship cruiser = new Cruiser();
		row = 9;
		column = 9;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		
		// case 3
		Ship destroyer = new Destroyer();
		row = 5;
		column = 5;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, destroyer.getBowRow());
		
		
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		// case 2
		Ship cruiser = new Cruiser();
		row = 9;
		column = 9;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, cruiser.getBowColumn());
		
		// case 3
		Ship destroyer = new Destroyer();
		row = 5;
		column = 5;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, destroyer.getBowColumn());
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		assertFalse(ship.getHit()[2]);
		assertFalse(ship.getHit()[3]);

		// case 2
		ship = new Submarine();
		boolean[] hits_submarine = new boolean[1];
		assertArrayEquals(hits_submarine, ship.getHit());
		assertFalse(ship.getHit()[0]);
		
		// case 3
		ship = new Destroyer();
		boolean[] hits_destroyer = new boolean[2];
		assertArrayEquals(hits_destroyer, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);

	}

	@Test
	void testIsHorizontal​() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		// case 2
		Ship cruiser = new Cruiser();
		row = 9;
		column = 9;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertFalse(cruiser.isHorizontal());
		
		// case 3
		Ship submarine = new Submarine();
		row = 5;
		column = 5;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertTrue(submarine.isHorizontal());
		
	
	}

	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		// case 2
		ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());
		
		// case 3
		ship = new EmptySea();
		assertEquals("empty", ship.getShipType());

	}

	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		battleship.setBowColumn(column);
		battleship.setHorizontal(horizontal);
		assertEquals(row, battleship.getBowRow());
		
		// case 2
		Ship destroyer = new Destroyer();
		row = 9;
		column = 9;
		horizontal = true;
		destroyer.setBowRow(row);
		destroyer.setBowColumn(column);
		destroyer.setHorizontal(horizontal);
		assertEquals(row, destroyer.getBowRow());
		
		// case 3
		Ship emptySea = new EmptySea();
		row = 5;
		column = 5;
		horizontal = false;
		emptySea.setBowRow(row);
		emptySea.setBowColumn(column);
		emptySea.setHorizontal(horizontal);
		assertEquals(row, emptySea.getBowRow());
		

	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		battleship.setBowColumn(column);
		battleship.setHorizontal(horizontal);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		
		// case 2
		Ship destroyer = new Destroyer();
		row = 9;
		column = 9;
		horizontal = true;
		destroyer.setBowRow(row);
		destroyer.setBowColumn(column);
		destroyer.setHorizontal(horizontal);
		assertEquals(column, destroyer.getBowColumn());
		
		// case 3
		Ship emptySea = new EmptySea();
		row = 5;
		column = 5;
		horizontal = false;
		emptySea.setBowRow(row);
		emptySea.setBowColumn(column);
		emptySea.setHorizontal(horizontal);
		assertEquals(column, emptySea.getBowColumn());
		
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		battleship.setBowColumn(column);
		battleship.setHorizontal(horizontal);
		assertEquals(row, battleship.getBowRow());
		assertTrue(battleship.isHorizontal());
		
		// case 2
		Ship destroyer = new Destroyer();
		horizontal = false;
		destroyer.setBowRow(row);
		destroyer.setBowColumn(column);
		destroyer.setHorizontal(horizontal);
		assertFalse(destroyer.isHorizontal());
		
		// case 3
		Ship cruiser = new Cruiser();
		horizontal = true;
		cruiser.setBowRow(row);
		cruiser.setBowColumn(column);
		cruiser.setHorizontal(horizontal);
		assertTrue(cruiser.isHorizontal());
		
		
		
		

	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		
		Ship battleship2 = new Battleship();
		row = 9;
		column = 9;
		horizontal = true;
		ok = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		// case 2
		// this should return false as it will be "sticking out" of the ocean
		Ship battleship3 = new Battleship();
		row = 9;
		column = 0;
		horizontal = true;
		ok = battleship3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "OK to place ship here.");
		
		// case 3
		Ship submarine = new Submarine();
		row = 5;
		column = 5;
		horizontal = true;
		ok = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		
		
		
		
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		
		//place first ship case2
		Battleship battleship1_case2 = new Battleship();
		row = 9;
		column = 9;
		horizontal = false;
		ok1 = battleship1_case2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1_case2.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2_case2 = new Battleship();
		row = 8;
		column = 8;
		horizontal = false;
		ok2 = battleship2_case2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent on the left.");
		
		
		//place first ship case3
		Submarine sub1 = new Submarine();
		row = 5;
		column = 5;
		horizontal = false;
		ok1 = sub1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		sub1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Submarine sub2 = new Submarine();
		row = 5;
		column = 5;
		horizontal = false;
		ok2 = sub2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship on top of another!.");
		

	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		
		// case 2
		// placing a battleship vertically at 9,9 and check if 5,9 is an ocean and 6,9 is (the last piece of) the battleship
		Ship battleship2 = new Battleship();
		row = 9;
		column = 9;
		horizontal = false;
		battleship2.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship2.getBowRow());
		assertEquals(column, battleship2.getBowColumn());
		assertFalse(battleship2.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[5][9].getShipType());
		assertEquals(battleship2, ocean.getShipArray()[6][9]);
		
		// case 3
		// placing a cruiser vertically at 5,5 and check if 5,4 is an empty ocean, and 4,5 is the cruiser 
		Ship cruiser = new Cruiser();
		row = 5;
		column = 5;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		assertEquals(column, cruiser.getBowColumn());
		assertFalse(cruiser.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[5][4].getShipType());
		assertEquals(cruiser, ocean.getShipArray()[4][5]);

	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		
		// case 2
		Ship battleship2 = new Battleship();
		row = 9;
		column = 9;
		horizontal = false;
		battleship2.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship2.shootAt(9, 9));
		assertFalse(battleship2.shootAt(9, 8));
		boolean[] hitArray1 = {true, false, false, false};
		assertArrayEquals(hitArray1, battleship2.getHit());
		
		// case 3
		Ship emptySea = new EmptySea();
		row = 5;
		column = 5;
		horizontal = false;
		emptySea.placeShipAt(row, column, horizontal, ocean);
		// shooting at emptySea results in "false", even though the hit array is updated
		assertFalse(emptySea.shootAt(5, 5));
		boolean[] hitArray1_sea = {true};
		assertArrayEquals(hitArray1_sea, emptySea.getHit());
		

		// case 4
		Ship submarine = new Submarine();
		row = 7;
		column = 3;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		// shooting around the submarine, with one row/column pair on the bow
		// though this case will never occur in the real game, it is tested for completeness
		assertFalse(submarine.shootAt(7, 5));
		assertFalse(submarine.shootAt(6, 3));
		boolean[] hitArray1_sub = {false};
		assertArrayEquals(hitArray1_sub, submarine.getHit());

		
		
	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		// case 2
		Ship battleship = new Battleship();
		row = 9;
		column = 9;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		// shooting the battleship one by one and checking whether it's sunk
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(9, 9));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(8, 9));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(7, 9));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(6, 9));
		assertTrue(battleship.isSunk());
		
		// case 3
		Ship emptySea = new EmptySea();
		row = 5;
		column = 5;
		horizontal = false;
		emptySea.placeShipAt(row, column, horizontal, ocean);
		// shooting the empty sea
		assertFalse(emptySea.isSunk());
		assertFalse(emptySea.shootAt(5, 5));
		assertFalse(emptySea.isSunk());
		
		
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		
		//case 2 the submarine is sunk after shooting
		Ship submarine = new Submarine();
		assertEquals("x", submarine.toString());
		
		row = 4;
		column = 4;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		submarine.shootAt(4, 4);
		assertEquals("s", submarine.toString());
		
		//case 3 shooting empty sea: whether it's hit, it will return "-"
		Ship emptySea = new EmptySea();
		assertEquals("-", emptySea.toString());
		
		row = 6;
		column = 6;
		horizontal = false;
		emptySea.placeShipAt(row, column, horizontal, ocean);
		emptySea.shootAt(6, 6);
		assertEquals("-", emptySea.toString());
		

	}

}
