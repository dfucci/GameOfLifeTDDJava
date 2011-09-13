package classes;

import java.util.ArrayList;
import java.util.Iterator;

public class Cell {
	private int x;
	private int y;
	private int numberOfNeighbors;
	private boolean isAlive;

	public Cell() throws NegativeCoordinateException {
	}

	public Cell(final int _x, final int _y) throws NegativeCoordinateException {

		this.setX(_x);
		this.setY(_y);

	}

	private void checkIsAlive(final ArrayList<Cell> cells) {
		if (cells.contains(this)) {
			this.setAlive(true);
		} else {
			setAlive(false);
		}
	}

	private void countNeighbors(final ArrayList<Cell> cells) {
		int counter = 0;
		Iterator<Cell> it = cells.iterator();

		while (it.hasNext()) {
			if (this.isNeighbor(it.next())) {
				counter++;
			}
		}
		this.setNumberOfNeighbors(counter);
	}

	@Override
	public final boolean equals(final Object o) {
		return this.getX() == ((Cell) o).getX()
				&& this.getY() == ((Cell) o).getY();
	}

	public final int getNumberOfNeighbors() {
		return numberOfNeighbors;
	}

	public final int getX() {
		return this.x;
	}

	public final int getY() {
		return this.y;
	}

	private boolean isNeighbor(final Cell n) {

		return Math.abs(this.getX() - n.getX()) <= 1
				&& Math.abs(this.getY() - n.getY()) <= 1 && !this.equals(n);
	}

	private void setAlive(final boolean isAlive) {
		this.isAlive = isAlive;
	}

	public final void setCellParameters(final ArrayList<Cell> cells) {
		this.countNeighbors(cells);
		this.checkIsAlive(cells);
	}

	private void setNumberOfNeighbors(final int numberOfNeighbors) {
		this.numberOfNeighbors = numberOfNeighbors;
	}

	private void setX(final int i) throws NegativeCoordinateException {
		if (i < 0) {
			throw new NegativeCoordinateException(
					"Coordinates must have positive values");
		}
		this.x = i;
	}

	private void setY(final int i) throws NegativeCoordinateException {
		if (i < 0) {
			throw new NegativeCoordinateException(
					"Coordinates must have positive values");
		}
		this.y = i;

	}

	public final boolean willRevive() {
		if (!(this.isAlive)) {
			if (numberOfNeighbors == 3) {
				return true;
			}
		}
		return false;
	}

	public final boolean willSurvive() {
		return (this.isAlive && (getNumberOfNeighbors() == 2 || getNumberOfNeighbors() == 3));
	}

}
