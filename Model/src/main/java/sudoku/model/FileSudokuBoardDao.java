package sudoku.model;

import sudoku.model.exception.SudokuFileException;
import sudoku.model.exception.SudokuFinalizeException;
import sudoku.model.exception.SudokuIOException;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String fileName;
    private ObjectOutputStream outputStream;
    private FileInputStream fis;
    private ObjectInputStream ois;
    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    public void write(SudokuBoard object) throws SudokuFileException {
        try {
            this.outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(object);
            outputStream.close();
        } catch (IOException e) {
            throw new SudokuFileException("Name not specified",e);
        }

    }

    public SudokuBoard read() throws SudokuFileException {
        SudokuBoard object = null;
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            object = (SudokuBoard) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new SudokuFileException("No such file: "+fileName,e);
        }
        return object;
    }

    //Close próbuje zamknąć strumienie gdyby któryś nadal pozostawał otwarty
    @Override
    public void close() throws SudokuIOException {
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
            throw new SudokuIOException("Cannot properly close streams",e);
        }

    }

    // Metoda wywołuje się gdy GC napotka obiekt, i stwierdzi brak referencji do niego
    @Override
    public void finalize() throws SudokuFinalizeException {
        try {
            close();
        } catch (Throwable e) {
            throw new SudokuFinalizeException("Cannot properly finalize objects", e);
        }
    }
}
