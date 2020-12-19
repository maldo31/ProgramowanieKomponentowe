package sudoku;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SudokuField implements Serializable, Comparable<SudokuField> {

    private int value;
    private  PropertyChangeSupport changes = new PropertyChangeSupport(this);

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append("value", value)
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
                .append(value, that.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value)
                .toHashCode();
    }

    public SudokuField() {
    }

    public SudokuField(int value) {
        this.value = value;
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
        return this.value;
    }



    public void setFieldValue(int value) {
        if (value >= -1 && value < 10) {
            int oldValue = this.value;
            this.value = value;
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
}
