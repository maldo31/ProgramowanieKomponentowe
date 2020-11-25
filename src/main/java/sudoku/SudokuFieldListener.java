package sudoku;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SudokuFieldListener implements PropertyChangeListener, java.io.Serializable {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (9 < (int)evt.getNewValue()) {
            System.out.println("Nieprawidłowa zmiana pola=" + evt.getPropertyName());
        }
    }
}
