package sudoku.model.exception;

public class SudokuDataBaseException extends SudokuBoardException {
    public SudokuDataBaseException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
    public SudokuDataBaseException(final String string) {
        super(string);
    }
    public SudokuDataBaseException() {
        super();
    }
}
