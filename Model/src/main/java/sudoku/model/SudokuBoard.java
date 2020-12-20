package sudoku.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/*
    //Sprawdzanie czy wtyczka checkstyle działa
    //Po odkomentowaniu checkstyle informuje o braku spacji między znakami
    for(int i=0;i<1;i++){
    System.out.print("Test");
    }
*/

public class SudokuBoard implements PropertyChangeListener, Serializable {

    public final int size = 9;
    private SudokuSolver solver;

    private List<List<SudokuField>> board;

    public SudokuBoard() {
        this.solver = new BacktrackingSudokuSolver();
        board = Arrays.asList(new List[size]);
        //Tworzymy dwuwymiarowa liste list o stałych wymiarach przy pomocy implementacji list z:
        // https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html#asList-T...-
        for (int i = 0; i < size; i++) {
            board.set(i, Arrays.asList(new SudokuField[size]));
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board.get(i).set(j,new SudokuField(this));
            }
        }
    }

    private SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
        board = Arrays.asList(new List[size]);
        for (int i = 0; i < size; i++) {
            board.set(i, Arrays.asList(new SudokuField[size]));
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board.get(i).set(j,new SudokuField(this));
            }
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("size", size)
                .append("solver", solver)
                .append("board", board)
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

        SudokuBoard that = (SudokuBoard) o;

        return new EqualsBuilder()
                .append(board, that.board)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(board)
                .toHashCode();
    }

    public void solveGame() {
        SudokuBoard sudoku = new SudokuBoard(this.solver);
        solver.solve(sudoku);
        this.board = sudoku.board;


    }

    public List<List<SudokuField>> getCopyOfBoard() {
        List<List<SudokuField>> copiedBoard = new ArrayList<List<SudokuField>>();
        copiedBoard = Arrays.asList(new List[size]);
        for (int i = 0; i < size; i++) {
            copiedBoard.set(i, Arrays.asList(new SudokuField[size]));
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copiedBoard.get(i).set(j,new SudokuField());
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copiedBoard.get(i).get(j).setFieldValue(board.get(i).get(j).getFieldValue());

            }
        }
        return copiedBoard;
    }

    public boolean checkCell(int row, int column, int value) {
        int subrow = row - row % 3;
        int subcol = column - column % 3;
        for (int i = 0; i < size; i++) {
            if (value == board.get(row).get(i).getFieldValue()) {
                    return false;
            }
        }
        for (int i = 0; i < size; i++) {
            if (value == board.get(i).get(column).getFieldValue()) {
                    return false;
            }
        }
       for (int i = 0; i < 3; i++) {
           for (int j = 0;j < 3; j++) {
               if (value == board.get(subrow + i).get(subcol + j).getFieldValue()) {
                           return false;

                   }
               }
           }

        return true;
    }

    public int get(int row, int column) {
        return board.get(row).get(column).getFieldValue();
    }

    public void set(int row, int column, int value) {
        this.board.get(row).get(column).setFieldValue(value);

    }

    public SudokuRow getRow(int rowIndex) {
        List<SudokuField> row = Arrays.asList(new SudokuField[size]);
        for (int column = 0; column < size; column++)  {
            row.set(column,this.board.get(rowIndex).get(column));
        }
        return new SudokuRow(row);
    }

    public SudokuColumn getColumn(int columnIndex) {
        List<SudokuField> column = Arrays.asList(new SudokuField[size]);
        for (int row = 0; row < size; row++) {
            column.set(row,this.board.get(row).get(columnIndex));
        }
        return new SudokuColumn(column);
    }

    public SudokuBox getBox(int rowIndex, int columnIndex) {
        List<SudokuField> box = Arrays.asList(new SudokuField[size]);
        int boxindex = 0;
        for (int row = ((int) rowIndex / 3) * 3;
             row < (((int) rowIndex / 3) * 3) + 3; row++) {
            for (int col = ((int) columnIndex / 3) * 3;
                 col < (((int) columnIndex / 3) * 3) + 3; col++) {
                box.set(boxindex,this.board.get(row).get(col));
                boxindex++;
            }
        }
        return new SudokuBox(box);
    }

    boolean checkBoard() {
        for (int index = 0;index < size;index++) {

            if (!getRow(index).verify()) {
                printMessenge(index);
                return false;
            }

            if (!getColumn(index).verify()) {
                printMessenge(index);
                return false;
            }

            if (!getBox(((int) index / 3) * 3,(index % 3) * 3).verify()) {
                printMessenge(index);
                return false;
            }

        }
        return true;
    }

    void printMessenge(int index) {
        System.out.println("Bład w kwadracie " + index + "\n" + toString());
    }

    public boolean checkBoardTest() {

        return checkBoard();
    }


        @Override
        public void propertyChange(PropertyChangeEvent evt) {

            if ((int)(evt.getOldValue()) != 0 && !checkBoardTest()) {
                System.out.println("Wartość " + evt.getNewValue() + " wstawiona nieprawidłowo");
            }



        }

}


