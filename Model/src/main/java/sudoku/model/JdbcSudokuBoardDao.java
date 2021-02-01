package sudoku.model;

import sudoku.model.exception.SudokuBoardException;
import sudoku.model.exception.SudokuDataBaseException;
import sudoku.model.exception.SudokuFinalizeException;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    private String fileName;
    DB db;

    public JdbcSudokuBoardDao(String fileName) {
        this.fileName = fileName;
        db = new DB();
        try {
            db.createNewDatabase();
        } catch (SudokuDataBaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    sudokuBoard.set(i, j, db.select(fileName, i, j));
                } catch (SudokuBoardException e) {
                    e.printStackTrace();
                }
            }
        }
        return sudokuBoard;
    }

    @Override
    public void  write(final SudokuBoard sudokuBoard) {
        try {
            db.createTable(fileName);
        } catch (SudokuDataBaseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Integer id = i * 9 + j + 1;
                try {
                    db.insert(fileName, sudokuBoard.get(i, j), id);
                } catch (SudokuDataBaseException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void finalize() throws SudokuFinalizeException {
        try {
            close();
        } catch (Throwable e) {
            throw new SudokuFinalizeException("Cannot properly finalize objects", e);
        }
    }

    @Override
    public void close() throws SudokuDataBaseException {
        try {
            db.close();
        } catch (Exception e) {
            throw new SudokuDataBaseException(e.getMessage());
        }

    }
}