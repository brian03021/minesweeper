package org.app.ui;

import org.app.domain.GameBoard;

/**
 * @author Brian
 *
 */
public class PrintConsole {

	public static void printWelcome() {
		System.out.println("Welcome to Minesweeper!\n");
	}

	public static void printBoard(GameBoard board, boolean gameOver) {
		System.out.println(board.getDisplay(gameOver));
	}

	public static void printPrompt() {
		System.out.print("Select a square to reveal (e.g A1): ");
	}

	public static void printInvalidInput() {
		System.out.println("Invalid input. Use format like A1 ");
	}

	public static void printAlreadyRevealed() {
		System.out.println("That square is already revealed");
	}

	public static void printHitMine() {
		System.out.println("Oh no, you detonated a mine! Game over\n");
	}

	public static void printWin() {
		System.out.println("Congratulations, you have won the game!!\n");
	}

	public static void printPressAnyKey() {
		System.out.println("Press enter to play again ..");
	}
}