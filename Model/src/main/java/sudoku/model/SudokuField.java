package sudoku.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {

    public transient IntegerProperty value = new SimpleIntegerProperty();

    private  PropertyChangeSupport changes = new PropertyChangeSupport(this);
    public IntegerProperty getValueProperty() {return value; }

    private void writeObject(ObjectOutputStream out)
            throws IOException {

        out.defaultWriteObject();   // always call this first

        out.writeObject(value.get());

    }
    private void readObject(ObjectInputStream in)
            throws IOException,
            ClassNotFoundException {

        in.defaultReadObject();    // always call this first

        value = new SimpleIntegerProperty((Integer) in.readObject());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append("value", value.getValue())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuField that = (SudokuField) o;

        return new EqualsBuilder()
                .append(value.get(), that.value.get())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value.get())
                .toHashCode();
    }

    public SudokuField() {
    }

    public SudokuField(int value) {
        this.value.setValue(value);
    }

    public SudokuField(PropertyChangeListener listener) {
        this.addPropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changes.removePropertyChangeListener(listener);
    }

    public int getFieldValue() {
        return value.get();
    }



    public void setFieldValue(int value) {
        if (value >= -1 && value < 10) {
            int oldValue = this.value.getValue();
            this.value.setValue(value);
            changes.firePropertyChange("value",oldValue,value);
        } else {
            System.out.print("Błąd, value musi być w zakresie <-1;9>,"
                    + " a jego wartość to=" + value + "\n");
        }
    }

    public int compareTo(SudokuField o) {
        if (this.getFieldValue() == o.getFieldValue()) {
            return 0;
        } else if (this.getFieldValue() > o.getFieldValue()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SudokuField sudokuField = new SudokuField();
        sudokuField.value = this.value;
        return sudokuField;
    }

}
