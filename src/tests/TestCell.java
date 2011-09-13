package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import classes.Cell;
import classes.NegativeCoordinateException;

public class TestCell {
	@Test
	public void cellDetectsIfItCanSurvive() throws Exception {
		Cell c = new Cell(0, 0);
		Cell x = new Cell(0, 1);
		Cell y = new Cell(1, 0);
		Cell z = new Cell(4, 4);
		ArrayList<Cell> cells = new ArrayList<Cell>();
		cells.add(c);
		cells.add(x);
		cells.add(y);
		cells.add(z);
		c.setCellParameters(cells);
		assertTrue(c.willSurvive());

	}

	@Test
	public void cellDetectsWhenItHasNoNeighbor() throws Exception {
		Cell c = new Cell(0, 0);
		ArrayList<Cell> cells = new ArrayList<Cell>();
		c.setCellParameters(cells);
		assertEquals(0, c.getNumberOfNeighbors());
	}

	@Test
	public void cellDetectsWhenItHasOneNeighbor() throws Exception {
		Cell c = new Cell(2, 2);
		Cell n1 = new Cell(2, 1);
		Cell n2 = new Cell(0, 0);
		ArrayList<Cell> cells = new ArrayList<Cell>();
		cells.add(n1);
		cells.add(n2);
		c.setCellParameters(cells);
		assertEquals(1, c.getNumberOfNeighbors());
	}

	// @Test
	// public void cellIsAlive() throws Exception{
	// Cell c = new Cell(2,2);
	// Cell n1 = new Cell(2,1);
	// Cell n2 = new Cell(1,2);
	// ArrayList<Cell> cells = new ArrayList<Cell>();
	// cells.add(c);
	// cells.add(n1);
	// cells.add(n2);
	// c.setCellParameters(cells);
	// assertTrue(c.isAlive());
	// }
	// @Test
	// public void cellisNotNeighborOfItself() throws Exception{
	// Cell c = new Cell(2,2);
	// Cell n = new Cell(2,2);
	// assertFalse(c.isNeighbor(n));
	// }

	@Test
	public void CellsWithSameCoordinatesAreEquals() throws Exception {
		Cell c = new Cell(2, 2);
		Cell c1 = new Cell(2, 2);
		assertTrue(c.equals(c1));
	}

	@Test
	public void cellWillReviveNextGeneration() throws Exception {
		Cell c = new Cell(2, 2);
		Cell n1 = new Cell(2, 1);
		Cell n2 = new Cell(1, 2);
		Cell n3 = new Cell(1, 1);
		ArrayList<Cell> cells = new ArrayList<Cell>();
		cells.add(n1);
		cells.add(n2);
		cells.add(n3);
		c.setCellParameters(cells);
		assertTrue(c.willRevive());
	}

	@Test
	public void HasCoordinates() throws Exception {
		Cell c = new Cell(4, 1);
		assertEquals(c.getX(), 4);
		assertEquals(c.getY(), 1);
	}

	// @Test
	// public void isNeighbor() throws Exception{
	// Cell c = new Cell(1,1);
	// Cell n = new Cell(2,2);
	// assertTrue(c.isNeighbor(n));
	// }
	//
	// @Test
	// public void isNotNeighbor() throws Exception{
	// Cell c = new Cell(1,1);
	// Cell n = new Cell(3,3);
	// assertFalse(c.isNeighbor(n));
	// }
	@Test
	public void throwsExceptionsIfAtLeastOneOfTheCoordinatesIsNegative()
			throws Exception {
		try {
			Cell c = new Cell(1, -1);
			fail("Can't have negative value");
		} catch (NegativeCoordinateException ex) {
			assertEquals("Coordinates must have positive values",
					ex.getMessage());
		}
	}

	@Test
	public void throwsExceptionsIfCoordinatesAreNegative() throws Exception {
		try {
			Cell c = new Cell(-1, 0);
			fail("Can't have a negative coordinate");
		} catch (NegativeCoordinateException expected) {
			assertEquals("Coordinates must have positive values",
					expected.getMessage());
		}
	}
}
