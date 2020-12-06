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
        try
                (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))){
            outputStream.writeObject(object);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
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
            System.out.println("Wystapil wyjatek klasy");
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
