package domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.Position;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Brian
 *
 */
class PositionTest {

 @ParameterizedTest
 @CsvSource({
         "0, 0,  10, true",
         "9, 9,  10, true",
         "10,0,  10, false",
         "-1,5,  10, false",
         "4, -1, 8,  false",
         "5, 12, 10, false"
 })
 
 @DisplayName("Position validity check")
 void testIsValid(int row, int col, int size, boolean expected) {
     Position pos = new Position(row, col);
     assertThat(pos.isValid(size)).isEqualTo(expected);
 }
}