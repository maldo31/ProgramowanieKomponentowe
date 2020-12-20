package sudoku.model;

import java.util.List;

public class SudokuColumn extends Series {
    public SudokuColumn(final List<SudokuField> copiedSeries) {
        super(copiedSeries);
    }
}