package sudoku.model.exception;

public class SudokuIOException extends SudokuBoardException {
    public SudokuIOException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
    public SudokuIOException(final String string) {
        super(string);
    }
    public SudokuIOException() {
        super();
    }
}

