package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Series {
    final int size = 9;
    List<SudokuField> cellSeries = Arrays.asList(new SudokuField[size]);

    public Series(final List<SudokuField> copiedSeries) {
        this.cellSeries = copiedSeries;
    }

    public List<Integer> copyValues() {
        List<Integer> copiedValues = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            copiedValues.add(i,cellSeries.get(i).getFieldValue());

        }
        return copiedValues;
    }

    public boolean verify() {
        List<Integer> copiedValues = copyValues();
        for (int i = 0; i < size; i++) {
            if (copiedValues.contains(i + 1) == false) {
                return false;
            }

        }
        return true;
    }
}
