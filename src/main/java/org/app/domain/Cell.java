package org.app.domain;

/**
 * @author Brian
 *
 */
public class Cell {
	private boolean isMine;
	private boolean isRevealed;
	private int adjacentMines;

	public boolean isMine() {
		return isMine;
	}

	public void placeMine() {
		this.isMine = true;
	}

	public boolean isRevealed() {
		return isRevealed;
	}

	public void reveal() {
		this.isRevealed = true;
	}

	public int getAdjacentMines() {
		return adjacentMines;
	}

	public void setAdjacentMines(int count) {
		this.adjacentMines = count;
	}

	public char displayChar() {
		if (!isRevealed)
			return '_';
		if (isMine)
			return '*';
		if (adjacentMines == 0)
			return ' ';
		return (char) ('0' + adjacentMines);
	}

	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}
}