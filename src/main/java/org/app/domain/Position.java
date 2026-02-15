package org.app.domain;

/**
 * @author Brian
 *
 */
public record Position(int row, int col) {

	public boolean isValid(int size) {
		return row >= 0 && row < size && col >= 0 && col < size;
	}

	@Override
	public String toString() {
		return String.format("(%d,%d)", row, col);
	}
}