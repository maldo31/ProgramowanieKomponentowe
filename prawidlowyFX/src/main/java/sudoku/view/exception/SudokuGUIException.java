package sudoku.view.exception;

public class SudokuGUIException extends Exception {
    public SudokuGUIException() {
        super();
    }

    public SudokuGUIException(String message) {
        super(message);
    }

    public SudokuGUIException(String message, Throwable cause) {
        super(message, cause);
    }
}
