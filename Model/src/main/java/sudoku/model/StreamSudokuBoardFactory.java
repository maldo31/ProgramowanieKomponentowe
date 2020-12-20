package sudoku.model;

public class StreamSudokuBoardFactory extends SudokuBoardDaoFactory {
    public Dao getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }
}
