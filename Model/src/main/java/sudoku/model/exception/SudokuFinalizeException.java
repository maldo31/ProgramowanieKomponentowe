package sudoku.model.exception;

public class SudokuFinalizeException extends SudokuBoardException {
    public SudokuFinalizeException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
