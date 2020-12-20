package sudoku.model;

import java.util.ArrayList;
import java.util.List;

public class SudokuColumn extends Series {
    public SudokuColumn(final List<SudokuField> copiedSeries) {
        super(copiedSeries);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        List<SudokuField> fields = new ArrayList<>(getSudokuFieldList());
        return new SudokuBox(fields);
    }
}