package org.app.domain;

import java.util.*;

import org.app.util.GameState;

/**
 * @author Brian
 *
 */
public class GameBoard {
	
	private final int size;
	private final Cell[][] cells;
	private int minesCount;
	private int revealedSafeCells;//num of safe cell that opened 
	
	private GameState state = GameState.RUNNING;
	private final Random random = new Random();
	public GameBoard(int size, int mines) {
		int maxMines = (int) (size * size * 0.35);
		// check size and mine
		if (size < 3 || size > 30 || mines <1 || mines > maxMines)
			throw new IllegalArgumentException("Grid size must be 3–>30 , and mines must be 1->" + maxMines);

		this.size = size;
		this.minesCount = mines;
		this.cells = new Cell[size][size];
		this.revealedSafeCells = 0;

		initBoard();
		initPlaceMines();
		calculateNumbers();
	}

	private void initBoard() {
		for (int row = 0; row < size; row++) {
			for (int colum = 0; colum < size; colum++) {
				cells[row][colum] = new Cell();
			}
		}
	}

	//auto randomly place mine
	private void initPlaceMines() {
		int placed = 0;
//		if(placed < minesCount) {}
		while (placed < minesCount) {
			int row = random.nextInt(size);
			int column = random.nextInt(size);
			if (!cells[row][column].isMine()) {
				cells[row][column].placeMine();
				placed++;
			}
		}
	}

	//calculate and assign the number of adjacent mines to all non mine cells
	private void calculateNumbers() {
		for (int row = 0; row< size; row++) {
			for (int col = 0; col < size; col++) {
				if (!cells[row][col].isMine()) {
					int count = countAdjacentMines(row, col);
					cells[row][col].setAdjacentMines(count);
				}
			}
		}
	}

	private int countAdjacentMines(int row, int col) {
		int count = 0;
		for (int dr = -1; dr <= 1; dr++) {//delta row and column
			for (int dc = -1; dc <= 1; dc++) {
				if (dr == 0 && dc == 0)
					continue;
				int nr = row + dr;
				int nc = col + dc;
				if (nr >= 0 && nr < size && nc >= 0 && nc < size && cells[nr][nc].isMine()) {
					count++;
				}
			}
		}
		return count;
	}

	public boolean reveal(Position pos) {
		if (!pos.isValid(size))
			return false;
		Cell cell = cells[pos.row()][pos.col()];
		if (cell.isRevealed())
			return false;

		//set to true
		cell.reveal();

		//ì its mine => game over
		if (cell.isMine()) {
			state = GameState.LOST;
			//show out
			revealAllMines();
			return true;
		}
		
		//
		revealedSafeCells++;

		if (cell.getAdjacentMines() == 0) {
			floodFill(pos.row(), pos.col());
		}

		if (revealedSafeCells == (size * size - minesCount)) {
			state = GameState.WON;
		}

		return true;
	}

	//automatically open all surrounding safe cells
	private void floodFill(int row, int col) {
		for (int dr = -1; dr <= 1; dr++) {
			for (int dc = -1; dc <= 1; dc++) {
				if (dr == 0 && dc == 0)
					continue;
				int nr = row + dr;
				int nc = col + dc;
				if (nr >= 0 && nr < size && nc >= 0 && nc < size) {
					Cell neighbor = cells[nr][nc];
					if (!neighbor.isRevealed() && !neighbor.isMine()) {
						neighbor.reveal();
						revealedSafeCells++;
						if (neighbor.getAdjacentMines() == 0) {
							//recur
							floodFill(nr, nc);
						}
					}
				}
			}
		}
	}

	private void revealAllMines() {
		for (int row = 0; row < size;row++) {
			for (int col = 0; col < size; col++) {
				if (cells[row][col].isMine()) {
					cells[row][col].reveal();
				}
			}
		}
	}

	public String getDisplay(boolean showAllMines) {
		StringBuilder sb = new StringBuilder();

		//header
		sb.append("  ");
		for (int col = 1; col <= size; col++) {
			sb.append(col).append(" ");
		}
		sb.append("\n");

		for (int row = 0; row < size; row++) {
			char rowLabel = (char) ('A' + row);
			sb.append(rowLabel).append(" ");

			for (int col = 0; col < size; col++) {
				Cell cell = cells[row][col];
				char ch;
				if (cell.isRevealed()) {
					ch = cell.displayChar();
				} else if (showAllMines && cell.isMine()) {
					ch = '*';
				} else {
					ch = '_';
				}
				sb.append(ch).append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void placeMine(int row, int col) {
	    if (row >= 0 && row < size && col >= 0 && col < size) {
	        cells[row][col].placeMine();
	    }
	}
	
	public void recalculateNumbers() {
	    for (int r = 0; r < size; r++) {
	        for (int c = 0; c < size; c++) {
	            if (!cells[r][c].isMine()) {
	                int count = countAdjacentMines(r, c);
	                cells[r][c].setAdjacentMines(count);
	            }
	        }
	    }
	}
	
	public GameState getState() {
		return state;
	}
	public int getSize() {
		return size;
	}
	public int getRevealedSafeCells() {
		return revealedSafeCells;
	}
	public Cell getCell(int r , int c) {
		return cells[r][c];
	}
	
	

}