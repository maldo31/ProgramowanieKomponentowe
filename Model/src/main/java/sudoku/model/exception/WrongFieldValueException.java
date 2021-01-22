package sudoku.model.exception;

public class WrongFieldValueException extends SudokuBoardException {
    public WrongFieldValueException() {
        super("Field value must be 1-9 inclusive");
    }
}
