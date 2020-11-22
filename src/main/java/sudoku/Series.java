package sudoku;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Series {
    final int size = 9;
    List<SudokuField> cellSeries = Arrays.asList(new SudokuField[size]);

    public Series(final List<SudokuField>copiedSeries) {
        this.cellSeries = copiedSeries;
    }

    public boolean verify() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (cellSeries.get(j).getFieldValue() > cellSeries.get(j + 1).getFieldValue()) {
                    int temp = cellSeries.get(j).getFieldValue();
                    cellSeries.get(j).setFieldValue(cellSeries.get(j + 1).getFieldValue());
                    cellSeries.get(j + 1).setFieldValue(temp);
                }
            }
        }
        for (int i = 0; i < size - 1; i++) {
            if (cellSeries.get(i).getFieldValue() != i + 1) {
                return false;
            }
        }
        return true;
    }
}
