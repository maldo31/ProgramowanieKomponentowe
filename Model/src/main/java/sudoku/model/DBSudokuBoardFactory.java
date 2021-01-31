package sudoku.model;

public class DBSudokuBoardFactory extends SudokuBoardDaoFactory {
    @Override
    public Dao getFileDao(String fileName) {
        return new JdbcSudokuBoardDao(fileName);
    }
}



