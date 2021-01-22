package sudoku.model.exception;

public class SudokuFileException extends SudokuBoardException {
    public SudokuFileException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
    public SudokuFileException(final String string) {
        super(string);
    }
    public SudokuFileException() {
        super();
    }
}
