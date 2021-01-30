package sudoku.model;

import sudoku.model.exception.SudokuBoardException;
import sudoku.model.exception.SudokuFinalizeException;

import java.time.LocalDateTime;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    DB db;
    String tmp = LocalDateTime.now().toString();

    public JdbcSudokuBoardDao() {
        db = new DB();
        db.createNewDatabase();
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    sudokuBoard.set(i, j, db.select("SudokuBoard" + tmp, i, j));
                    //sudokuBoard.set(i, j, db.select("sudokuBoard", i, j));
                } catch (SudokuBoardException e) {
                    e.printStackTrace();
                }
            }
        }
        return sudokuBoard;
    }

    @Override
    public void  write(final SudokuBoard sudokuBoard) {
        db.createTable("SudokuBoard" + tmp);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Integer id = i * 9 + j + 1;
                db.insert("sudokuBoard", sudokuBoard.get(i, j), id);
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
    public void close() throws Exception {
        try {
            //reading.close();
            //writing.close();
        } catch (Exception e) {
            // ...
        }

    }
}