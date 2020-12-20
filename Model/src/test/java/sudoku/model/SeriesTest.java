package sudoku.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sudoku.model.Series;
import sudoku.model.SudokuBoard;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SeriesTest {
    private Series row;
    @BeforeEach
    void setUp() {
        SudokuBoard board = new SudokuBoard();
        board.solveGame();
        row = board.getRow(0);
    }

    @Test
    void testToString() {
        row.toString();
    }

    @Test
    void testEqualsSame() {
        assertTrue(row.equals(row));
    }
    @Test
    void testEqualsNull() {
        assertFalse(row.equals(null));
    }
    @Test
    void testEqualsDiffrentClass() {
        assertFalse(row.equals(1));
    }
    @Test
    void testEqualsDiffrent() {
        SudokuBoard board = new SudokuBoard();
        board.solveGame();
        Series row1 = board.getRow(0);
        assertFalse(row.equals(row1));
    }

}