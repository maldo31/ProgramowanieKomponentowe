package sudoku;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class Series {
    final int size = 9;
    List<SudokuField> cellSeries;

    public Series(final List<SudokuField> copiedSeries) {
        this.cellSeries = copiedSeries;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("size", size)
                .append("cellSeries", cellSeries)
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
        Series series = (Series) o;
        return new EqualsBuilder()
                .append(size, series.size)
                .append(cellSeries, series.cellSeries)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(size)
                .append(cellSeries)
                .toHashCode();
    }

    public List<Integer> copyValues() {
        List<Integer> copiedValues = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            copiedValues.add(i,cellSeries.get(i).getFieldValue());

        }
        return copiedValues;
    }

    public boolean verify() {
        List<Integer> copiedValues = copyValues();
        for (int i = 0; i < size; i++) {
            //użyto implementacji metody containts z https://docs.oracle.com/javase/7/docs/api/java/util/Set.html
            if (!copiedValues.contains(i + 1)) {
                return false;
            }

        }
        return true;
    }
}
