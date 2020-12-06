package sudoku;

public abstract class SudokuBoardDaoFactory {
    public abstract Dao getFileDao(String fileName);

    public static SudokuBoardDaoFactory getSudokuBoardDaoFactory(){
        return new StreamSudokuBoardFactory();
    }
    }

