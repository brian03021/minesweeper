package domain;

import org.junit.jupiter.api.Test;

import domain.Cell;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Brian
 *
 */
class CellTest {

	@Test
	void freshCellShouldNotBeRevealedNorMine() {
		Cell cell = new Cell();
		assertThat(cell.isMine()).isFalse();
		assertThat(cell.isRevealed()).isFalse();
		assertThat(cell.getAdjacentMines()).isZero();
		assertThat(cell.displayChar()).isEqualTo('_');
	}

	@Test
	void afterPlaceMine_displayShowsStarWhenRevealed() {
		Cell cell = new Cell();
		cell.placeMine();
		cell.reveal();
		assertThat(cell.displayChar()).isEqualTo('*');
	}

	@Test
	void numbersAreDisplayedCorrectly() {
		Cell cell = new Cell();
		cell.setAdjacentMines(3);
		cell.reveal();
		assertThat(cell.displayChar()).isEqualTo('3');

		cell = new Cell();
		cell.setAdjacentMines(0);
		cell.reveal();
		assertThat(cell.displayChar()).isEqualTo(' ');
	}
}