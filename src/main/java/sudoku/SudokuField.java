package sudoku;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuField {

    private int value;
    private  PropertyChangeSupport changes = new PropertyChangeSupport(this);

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
                .append("changes", changes)
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
                .append(changes)
                .toHashCode();
    }

    public SudokuField() {
        this.addPropertyChangeListener(new SudokuFieldListener());
    }

    public SudokuField(int value) {
        this.addPropertyChangeListener(new SudokuFieldListener());
        this.value = value;
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
}
