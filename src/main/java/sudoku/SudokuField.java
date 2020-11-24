package sudoku;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SudokuField {

    private int value;
    private  PropertyChangeSupport changes = new PropertyChangeSupport(this);

    public SudokuField() {
        this.addPropertyChangeListener(new SudokuFieldListener());
    }

    public SudokuField(int value) {
        this.addPropertyChangeListener(new SudokuFieldListener());
        this.value = value;
    }
    public void addPropertyChangeListener(PropertyChangeListener listener){
        changes.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener){
        changes.removePropertyChangeListener(listener);
    }

    public int getFieldValue() {
        return this.value;
    }

    public void setFieldValue(int value) {
        if (value >= -1 && value < 10) {
            int oldValue=this.value;
            this.value = value;
            changes.firePropertyChange("value",oldValue,value);
        } else {
            System.out.print("Błąd, value musi być w zakresie <-1;9>,"
                    + " a jego wartość to=" + value + "\n");
        }
    }
}
