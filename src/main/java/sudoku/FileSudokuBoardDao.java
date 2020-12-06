package sudoku;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

private String fileName;
private ObjectOutputStream outputStream;
private FileInputStream fis;
private ObjectInputStream ois;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }


    public void write(SudokuBoard object) {
        try {
            this.outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(object);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public SudokuBoard read() {
        SudokuBoard object = null;
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            object = (SudokuBoard) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    //Close próbuje zamknąć strumienie gdyby któryś nadal pozostawał otwarty
    @Override
    public void close() {
        try {
            if (ois != null) {
                ois.close();
                ois = null;
                }
            if (fis != null) {
                fis.close();
                fis = null;
                }
            if (outputStream != null) {
                outputStream.close();
                }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Metoda wywołuje się gdy GC napotka obiekt, i stwierdzi brak referencji do niego
    @Override
    public void finalize() {
        try {
        close();
            } catch (Throwable e) {
            e.printStackTrace();
                            }
        }



}
