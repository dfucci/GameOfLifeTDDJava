package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import classes.Cell;
import classes.CellCoordinateOutOfBoundariesException;
import classes.Grid;

public class TestGrid {

	@Test
	public void gridWithCellsIsNotEmpty() throws Exception {
		ArrayList<Cell> aliveCells = new ArrayList<Cell>();
		Cell c = new Cell(0, 0);
		Cell b = new Cell(2, 1);
		aliveCells.add(c);
		aliveCells.add(b);
		Grid g = new Grid(aliveCells);
		assertFalse(g.isEmpty());
	}

	@Test
	public void gridHasBoundaries() throws Exception {
		ArrayList<Cell> al = new ArrayList<Cell>();
		Grid g = new Grid(al, 5, 5);
		assertEquals(5, g.getWidth());
		assertEquals(5, g.getHeight());
	}

	@Test
	public void cellOutOfBoundariesRaisesException() throws Exception {
		try {
			Cell c = new Cell(6, 6);
			ArrayList<Cell> aliveCells = new ArrayList<Cell>();
			aliveCells.add(c);
			Grid g = new Grid(aliveCells, 3, 3);
			fail("Cell coordinates must be inside the boundaries of the grid");
		} catch (CellCoordinateOutOfBoundariesException expected) {
			assertEquals("Cell coordinates are outside the grid boundaries",
					expected.getMessage());
		}
	}

	public void newGenerationIsEmptyIfGridIsEmpty() throws Exception {
		ArrayList<Cell> aliveCells = new ArrayList<Cell>();
		Grid g = new Grid(aliveCells, 5, 5);
		assertTrue(g.tick().isEmpty());
	}

	public void oneCellAloneInTheGridDies() throws Exception {
		ArrayList<Cell> aliveCells = new ArrayList<Cell>();
		Cell c = new Cell(1, 1);
		aliveCells.add(c);
		Grid g = new Grid(aliveCells, 3, 3);
		assertTrue(g.tick().getAliveCells().size() == 0);
	}

	public void gotFourAliveCellsAfterTickingWithThreeCells() throws Exception {
		ArrayList<Cell> aliveCells = new ArrayList<Cell>();
		Cell a = new Cell(0, 2);
		Cell b = new Cell(0, 1);
		Cell c = new Cell(1, 2);
		aliveCells.add(c);
		aliveCells.add(b);
		aliveCells.add(a);
		Grid g = new Grid(aliveCells, 3, 3);
		assertTrue(g.tick().getAliveCells().size() == 4);
	}

	@Test
	public void gotSixAliveCellsAfterTickingWithFourCells() throws Exception {
		ArrayList<Cell> aliveCells = new ArrayList<Cell>();
		Cell a = new Cell(0, 0);

		aliveCells.add(a);

		Grid g = new Grid(aliveCells, 3, 3);
		assertTrue(g.tick().getAliveCells().size() == 0);
	}
}
