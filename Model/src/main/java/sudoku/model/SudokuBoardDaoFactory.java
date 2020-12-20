package sudoku.model;

public abstract class SudokuBoardDaoFactory {
    public static final String STREAM = "stream";

    public abstract Dao getFileDao(String fileName);

    public static SudokuBoardDaoFactory getSudokuBoardDaoFactory(String whichFactory) {
        switch (whichFactory) {
            case STREAM:
                return new StreamSudokuBoardFactory();
            default:
                return null;
        }

    }
    }

