package sudoku;

public class SudokuField {

    private int value;
    public SudokuField() {

    }

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue(){
        return this.value;
    }

    public void setFieldValue(int value) {
        if (value > 0 && value < 10) {
            this.value = value;
        }
    }
}
