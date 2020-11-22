package sudoku;
import java.util.*;

public abstract class Series {
    final int size = 9;
    List<SudokuField> cellSeries = Arrays.asList(new SudokuField[size]);
    List<Integer> seriesValues = new ArrayList<Integer>();

    public Series(final List<SudokuField>copiedSeries) {
        this.cellSeries = copiedSeries;
    }

    public List<Integer> copyValues(){
        List<Integer> copiedValues = new ArrayList<>();
        for(int i = 0; i < size; i++){
            copiedValues.add(i,cellSeries.get(i).getFieldValue());

        }
        return copiedValues;
    }

    public boolean verify() {
        List<Integer> copiedValues=copyValues();

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                System.out.println(j);
                if (copiedValues.get(j) > copiedValues.get(j + 1)) {
                    int temp = copiedValues.get(j);
                    copiedValues.set(j,copiedValues.get(j+1));
                    copiedValues.set(j+1,temp);
                }
            }
        }
        System.out.println(copiedValues);
        for (int i = 0; i < size - 1; i++) {
            if (copiedValues.get(i) != i + 1) {
                System.out.println(i);
                return false;
            }
        }
        return true;
    }
}
