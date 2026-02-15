package org.app;
//

import org.app.Main;
import org.app.domain.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Brian
 *
 */
public class MainTest {

	private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));
        output.reset();
    }

    //readGridSize()
//    @Test
//    @DisplayName("readGridSize accepts valid value immediately")
//    void readGridSize_validFirstInput_returnsIt() {
//        provideInput("8\n");
//
//        int size = Main.readGridSize();
//
//        assertEquals(8, size);
//        assertThat(output.toString()).contains("Enter the size of the grid");
//    }
//
//    @ParameterizedTest(name = "input={0} => should reprompt")
//    @ValueSource(strings = {"2", "31", "abc", "", "3.5", "-1"})
//    @DisplayName("readGridSize rejects invalid values and reprompts")
//    void readGridSize_rejectsInvalid_thenAcceptsNext(String badInput) {
//        provideInput(badInput + "\n12\n");
//
//        int size = Main.readGridSize();
//
//        assertEquals(12, size);
//        String out = output.toString();
//        assertThat(out).contains("Please enter a number from 3 to 30");
//        assertThat(out).contains("Enter the size of the grid").containsOnlyOnce("12");
//    }
//
//    //readMinesCount(int size)
//    @Test
//    @DisplayName("readMinesCount accepts valid count")
//    void readMinesCount_validInput() {
//        provideInput("4\n");
//
//        int mines = Main.readMinesCount(5); // 25 cells => max ≈ 8
//
//        assertEquals(4, mines);
//    }
//
//    //5×5 => max 8
//    @ParameterizedTest
//    @CsvSource({
//            "0,   Please enter a number from 1 to",
//            "9,   Please enter a number from 1 to",
//            "-3,  Please enter a number from 1 to",
//            "abc, Please enter a number from 1 to"
//    })
//    @DisplayName("readMinesCount rejects invalid and reprompts")
//    void readMinesCount_rejectsInvalid(int badValue, String expectedMessagePart) {
//        provideInput(badValue + "\n6\n");
//
//        int mines = Main.readMinesCount(5);
//
//        assertEquals(6, mines);
//        assertThat(output.toString()).contains(expectedMessagePart);
//    }

    //parsePosition(String, int)
    @ParameterizedTest
    @CsvSource({
            "A1,  10, 0, 0",
            "B3,  10, 1, 2",
            "a4,  10, 0, 3",
            "Z10, 26, 25, 9"
    })
    @DisplayName("parsePosition handles valid inputs correctly")
    void parsePosition_valid(String input, int size, int expectedRow, int expectedCol) {
        Position pos = Main.parsePosition(input, size);
        assertNotNull(pos);
        assertEquals(expectedRow, pos.row());
        assertEquals(expectedCol, pos.col());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "A", "1A", "AA1", "A0", "A11", "K1", "B999", "abc", "A-1"})
    @DisplayName("parsePosition returns null for invalid formats")
    void parsePosition_invalid_returnsNull(String invalid) {
        Position pos = Main.parsePosition(invalid, 10);
        assertNull(pos);
    }

}
