package sudoku.model.exception;

public class SudokuBoardException extends Exception{
    public SudokuBoardException(){
        super();
    }
    public SudokuBoardException(String message){
        super(message);
    }
    public SudokuBoardException(String message, Throwable cause){
        super(message, cause);
    }
}
