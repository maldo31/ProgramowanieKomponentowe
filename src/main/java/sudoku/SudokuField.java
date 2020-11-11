package sudoku;

public class SudokuField {

    private int value;

    public SudokuField() {
    }

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {
        return this.value;
    }

    public void setFieldValue(int value) {
        if (value > -1 && value < 10) {
            this.value = value;
        } else {
            System.out.print("Błąd, value musi być w zakresie <-1;9>,"
                    + " a jego wartość to=" + value + "\n");
        }
    }
}
