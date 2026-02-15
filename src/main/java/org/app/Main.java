package org.app;

import java.util.Scanner;

import org.app.domain.GameBoard;
import org.app.domain.Position;
import org.app.ui.PrintConsole;
import org.app.util.GameState;

/**
 * @author Brian
 *
 */
public class Main {

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			startGame();

			PrintConsole.printPressAnyKey();
			scanner.nextLine(); // wait for enter
			System.out.println("\n");
		}
	}

	public static void startGame() {
		PrintConsole.printWelcome();

		int size = readGridSize();
		int mines = readMinesCount(size);
		//randomly initial
		GameBoard board = new GameBoard(size, mines);

		System.out.println("Here is your minefield:\n");
		PrintConsole.printBoard(board, false);

		while (board.getState() == GameState.RUNNING) {
			//select
			PrintConsole.printPrompt();
			String input = scanner.nextLine();

			Position pos = parsePosition(input, size);
			if (pos == null) {
				PrintConsole.printInvalidInput();
				continue;
			}

			boolean changed = board.reveal(pos);
			if (!changed) {
				PrintConsole.printAlreadyRevealed();
				continue;
			}

			System.out.println("");
			boolean gameOver = board.getState() != GameState.RUNNING;
			PrintConsole.printBoard(board, gameOver);

			if (gameOver) {
				if (board.getState() == GameState.LOST) {
					PrintConsole.printHitMine();
				} else {
					PrintConsole.printWin();
				}
				return;
			}
		}
	}

	public static int readGridSize() {
		while (true) {
			System.out.print("Enter the size of the grid (3–30): ");
			String line = scanner.nextLine().trim();
			try {
				int size = Integer.parseInt(line);
				if (size >= 3 && size <= 30) {
					return size;
				}
			} catch (NumberFormatException e) {
			}
			System.out.println("Please enter a number from 3 to 30.");
		}
	}

	public static int readMinesCount(int size) {
		// total = size ^2 x 35%
		int maxMines = (int) (size * size * 0.35);
		while (true) {
			System.out.printf("Enter number of mines (1–%d): ", maxMines);
			String line = scanner.nextLine().trim();
			try {
				int mines = Integer.parseInt(line);
				// check
				if (mines >= 1 && mines <= maxMines) {
					return mines;
				}
			} catch (NumberFormatException e) {
			}
			System.out.printf("Please enter a number from 1 to %d.\n", maxMines);
		}
	}
	//A1,3
	public static Position parsePosition(String input, int size) {
		input = input.trim().toUpperCase();//a1
		
		if (input.length() < 2)
			return null;

		char rowChar = input.charAt(0);
		String colStr = input.substring(1);//a4

		if (rowChar < 'A' || rowChar > 'Z')
			return null;

		int row = rowChar - 'A';
		try {
			int col = Integer.parseInt(colStr) - 1;
			Position pos = new Position(row, col);
			if (pos.isValid(size)) {
				return pos;
			}
		} catch (NumberFormatException e) {
		}
		return null;
	}
}