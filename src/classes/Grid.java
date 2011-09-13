package classes;

import java.util.ArrayList;
import java.util.Iterator;

public class Grid {

	public static void main(String[] args) throws java.io.IOException {
		ArrayList<Cell> alc = new ArrayList<Cell>();
		try {
			Cell a = new Cell(0, 0);
			Cell b = new Cell(1, 1);
			Cell c = new Cell(0, 2);
			Cell d = new Cell(2, 0);
			Cell e = new Cell(2, 2);
			alc.add(a);
			alc.add(b);
			alc.add(c);
			alc.add(d);
			alc.add(e);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Grid g = new Grid(alc, 3, 3);
		String o = "";
		try {
			o = g.printGrid();

			System.out.print(o);

			System.out.println(g.tick().printGrid());
		} catch (NegativeCoordinateException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<Cell> aliveCells;
	private int width;

	private int height;

	public Grid(ArrayList<Cell> aliveCells) {
		this.aliveCells = new ArrayList<Cell>();
		this.aliveCells = aliveCells;
	}

	public Grid(ArrayList<Cell> al, int w, int h) {
		this.setHeight(h);
		this.setWidth(w);
		this.aliveCells = new ArrayList<Cell>();
		if (checkCellsAreValid(al)) {
			this.aliveCells = al;
		}
	}

	private boolean checkCellsAreValid(ArrayList<Cell> al) {
		boolean res = false;
		Iterator<Cell> it = al.iterator();
		while (it.hasNext()) {
			if (!(isCellInsideBoundaries(it.next()))) {
				res = false;
			} else {
				res = true;
			}
		}
		return res;
	}

	public ArrayList<Cell> getAliveCells() {
		return aliveCells;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	private boolean isCellInsideBoundaries(Cell c)
			throws CellCoordinateOutOfBoundariesException {
		if (c.getX() <= this.getWidth() && c.getY() <= this.getHeight()) {
			return true;
		} else {
			throw new CellCoordinateOutOfBoundariesException(
					"Cell coordinates are outside the grid boundaries");
		}
	}

	public boolean isEmpty() {
		return !(this.aliveCells.size() > 0);
	}

	public String printGrid() throws NegativeCoordinateException {
		String p = "";
		for (int i = 0; i < this.getHeight(); i++) {
			for (int j = 0; j < this.getWidth(); j++) {
				if (this.getAliveCells().contains(new Cell(j, i))) {
					p += "*";
				} else
					p += "-";
			}
			p += "\n";
		}
		return p;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Grid tick() throws NegativeCoordinateException {
		ArrayList<Cell> newGenCells = new ArrayList<Cell>();
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				Cell c = new Cell(i, j);
				c.setCellParameters(this.getAliveCells());
				if (c.willRevive() || c.willSurvive()) {
					newGenCells.add(c);
				}
			}
		}
		Grid nextGen = new Grid(newGenCells, this.getWidth(), this.getHeight());
		return nextGen;
	}
}
