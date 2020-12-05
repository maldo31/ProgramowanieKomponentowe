package sudoku;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void write(SudokuBoard object) {
        FileOutputStream fos = new FileOutputStream(fileName);

    }

    @Override
    public SudokuBoard read() {

    }
    

    // Finalize nie może być użyte, bo finalize istnieje już w java.lang.Object
    // i jest protected <???>
    /*
    void finalize(){

    }
     */
}
