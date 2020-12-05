package sudoku;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class FileSudokuBoardDao implements Dao<SudokuBoard> {

private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void write(SudokuBoard object) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
        } catch (IOException e) {
            System.out.println("Wystapil blad");
        }

    }

    @Override
    public SudokuBoard read() {
        SudokuBoard object = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            object = (SudokuBoard) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Wystapil blad");
        }
        return object;
    }


    // Finalize nie może być użyte, bo finalize istnieje już w java.lang.Object
    // i jest protected <???>
    /*
    void finalize(){

    }
     */
}
