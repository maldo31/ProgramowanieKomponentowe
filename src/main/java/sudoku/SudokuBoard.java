package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    //Sprawdzanie czy wtyczka checkstyle działa
    //Po odkomentowaniu checkstyle informuje o braku spacji między znakami
    for(int i=0;i<1;i++){
    System.out.print("Test");
    }
*/

public class SudokuBoard {

    public final int size = 9;
    private SudokuSolver solver = new BacktrackingSudokuSolver();

    private List<List<SudokuField>> board;

    public SudokuBoard() {
        board = Arrays.asList(new List[size]);
        //Tworzymy dwuwymiarowa liste list o stałych wymiarach przy pomocy implementacji list z:
        // https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html#asList-T...-
        for (int i = 0; i < size; i++) {
            board.set(i, Arrays.asList(new SudokuField[size]));
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board.get(i).set(j,new SudokuField());
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
                this.board.get(i).set(j,new SudokuField());
            }
        }
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


    public void showBoard() {
        System.out.print("\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(get(i,j) + " ");
            }
            System.out.print("\n");
        }
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

    private boolean checkBoard() {
        for (int index = 0;index < size;index++) {

            if (!getRow(index).verify()) {
                System.out.println("Bład w wierszu " + index);
                showBoard();
                return false;
            }

            if (!getColumn(index).verify()) {
                System.out.println("Bład w kolumnie " + index);
                showBoard();
                return false;
            }

            if (!getBox(((int) index / 3) * 3,(index % 3) * 3).verify()) {
                System.out.println("Bład w kwadracie " + index);
                showBoard();
                return false;
            }

        }
        return true;
    }

    public boolean checkBoardTest() {

        return checkBoard();
    }

}


