package org.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.app.domain.GameBoard;
import org.app.domain.Position;
import org.app.util.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void constructor_validInput_createsBoard() {
        GameBoard board = new GameBoard(5, 5);
        assertEquals(5, board.getSize());
        assertEquals(GameState.RUNNING, board.getState());
    }

    @Test
    void constructor_invalidSize_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new GameBoard(2, 1));
        assertThrows(IllegalArgumentException.class, () -> new GameBoard(31, 5));
    }

    @Test
    void constructor_invalidMineCount_throwsException() {
        // 5×5 = 25 cells => max ~8 mines (35%)
        assertThrows(IllegalArgumentException.class, () -> new GameBoard(5, 9));
        assertThrows(IllegalArgumentException.class, () -> new GameBoard(5, 0));
        assertThrows(IllegalArgumentException.class, () -> new GameBoard(5, -1));
    }

    @Test
    void reveal_invalidPosition_returnsFalse() {
        GameBoard board = new GameBoard(5, 3);
        assertFalse(board.reveal(new Position(-1, 0)));
        assertFalse(board.reveal(new Position(5, 5)));
    }

    @Test
    void reveal_sameCellTwice_returnsFalseSecondTime() {
        GameBoard board = new GameBoard(5, 3);
        Position pos = new Position(0, 0);
        assertTrue(board.reveal(pos));
        assertFalse(board.reveal(pos));
    }
    


    @Test
    void getDisplay_containsExpectedElements() {
        GameBoard board = new GameBoard(4, 2);
        String display = board.getDisplay(false);

        assertTrue(display.contains("  1 2 3 4"));//header
        assertTrue(display.contains("A"));//row labels
        assertTrue(display.contains("_"));//hidden cells
    }
}