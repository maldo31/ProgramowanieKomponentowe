package sudoku;

public class StreamSudokuBoardFactory extends SudokuBoardDaoFactory {
    public Dao getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }
}
