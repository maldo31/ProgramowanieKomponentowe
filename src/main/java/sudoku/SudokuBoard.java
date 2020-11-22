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

    // Punkt 3
    // Glowna funkcja do sprawdzenia dzialania tablicy na szybko
    public static void main(String[] args) {
        //Stworzenie listy o stałym rozmiarze
        List<Integer> fixedList = Arrays.asList(new Integer[5]);

        //Settery:
        fixedList.set(0, 1);

        //Jeśli spróbujemy użyć indexu spoza zakresu uzyskamy błąd:
        //"ArrayIndexOutOfBoundsException"
        //fixedList.set(10, 5);

        //W przypadku próby dodania/odjęcia pola do tej tablicy wyskoczy błąd:
        //"UnsupportedOperationException"
        //fixedList.add(2);
        //fixedList.remove(3);
    }

    public final int size = 9;
    private SudokuSolver solver = new BacktrackingSudokuSolver();
    private SudokuField[][] board = new SudokuField[size][size];

    public SudokuBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new SudokuField();
            }
        }
    }

    private SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new SudokuField();
            }
        }
    }

    public void solveGame() {
        SudokuBoard sudoku = new SudokuBoard(this.solver);
        solver.solve(sudoku);
        this.board = sudoku.getCopyOfBoard();


    }

    public SudokuField[][] getCopyOfBoard() {
        SudokuField[][] copiedBoard = new SudokuField[9][9];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copiedBoard[i][j] = new SudokuField();
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copiedBoard[i][j].setFieldValue(board[i][j].getFieldValue());
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
            if (value == board[row][i].getFieldValue()) {

                    return false;

            }
        }
        for (int i = 0; i < size; i++) {
            if (value == board[i][column].getFieldValue()) {

                    return false;

            }
        }
       for (int i = 0; i < 3; i++) {
           for (int j = 0;j < 3; j++) {
               if (value == board[subrow + i][subcol + j].getFieldValue()) {
                           return false;

                   }
               }
           }

        return true;
    }

    public int get(int row, int column) {
        return board[row][column].getFieldValue();
    }

    public void set(int row, int column, int value) {
        this.board[row][column].setFieldValue(value);
    }

    public SudokuRow getRow(int rowIndex) {
        List<SudokuField> row = Arrays.asList(new SudokuField[size]);
        for (int column = 0; column < size; column++)  {
            row.set(column,this.board[rowIndex][column]);
        }
        return new SudokuRow(row);
    }

    public SudokuColumn getColumn(int columnIndex) {
        List<SudokuField> column = Arrays.asList(new SudokuField[size]);
        for (int row = 0; row < size; row++) {
            column.set(row,this.board[row][columnIndex]);
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
                box.set(boxindex,this.board[row][col]);
                boxindex++;
            }
        }
        return new SudokuBox(box);
    }

    private boolean checkBoard() {
        boolean valid = true;
        for (int index = 0;index < size;index++) {
            valid = getRow(index).verify();
            if (valid == false) {
                this.showBoard();
                break;
            }
            valid = getColumn(index).verify();
            if (valid == false) {
                this.showBoard();
                break;
            }

            valid = getBox(((int) index / 3) * 3,(index % 3) * 3).verify();
            if (valid == false) {
                this.showBoard();
                break;
            }

        }
        return valid;
    }

    public boolean checkBoardTest() {
        return checkBoard();
    }

}


