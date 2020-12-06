package sudoku;

import java.io.*;


public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

private String fileName;
private ObjectOutputStream outputStream;
private FileInputStream fis;
private ObjectInputStream ois;
    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void write(SudokuBoard object) {
        try
        {   this.outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
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
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            object = (SudokuBoard) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
    @Override
    public void close(){
        try {
            ois.close();
            fis.close();
            outputStream.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }


    // Finalize nie może być użyte, bo finalize istnieje już w java.lang.Object
    // i jest protected <???>
    /*
    void finalize(){

    }
     */
}
