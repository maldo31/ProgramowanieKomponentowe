package sudoku;

public class Series {
    final int size = 9;
    protected SudokuField[] cellSeries = new SudokuField[size];

    public Series(final SudokuField[] copiedSeries) {
        this.cellSeries = copiedSeries;
    }

    public boolean verify() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (cellSeries[j].getFieldValue() > cellSeries[j + 1].getFieldValue()) {
                    int temp = cellSeries[j].getFieldValue();
                    cellSeries[j].setFieldValue(cellSeries[j + 1].getFieldValue());
                    cellSeries[j + 1].setFieldValue(temp);
                }
            }
        }
        for (int i = 0; i < size - 1; i++) {
            if (cellSeries[i].getFieldValue() != i + 1) {
                return false;
            }
        }
        return true;
    }
}
