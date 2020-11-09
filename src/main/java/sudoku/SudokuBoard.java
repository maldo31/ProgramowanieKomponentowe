package sudoku;

/*
    //Sprawdzanie czy wtyczka checkstyle działa
    //Po odkomentowaniu checkstyle informuje o braku spacji między znakami
    for(int i=0;i<1;i++){
    System.out.print("Test");
    }
*/

public class SudokuBoard {
    public final int size = 9;
    //private final int[][] board = new int[size][size];
    private SudokuField[][] board = new SudokuField[size][size];

    public SudokuBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new SudokuField();
            }
        }
    }

    public void solveGame() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(this);
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
                if (column != i) {
                    return false;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (value == board[i][column].getFieldValue()) {
                if (row != i) {
                    return false;
                }
            }
        }
       for (int i = 0; i < 3; i++) {
           for (int j = 0;j < 3; j++) {
               if (value == board[subrow + i][subcol + j].getFieldValue()) {
                   if (subrow + i != row) {
                       if (subcol + j != column) {
                           return false;
                       }
                   }
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
        SudokuField[] row = new SudokuField[size];
        for (int column = 0; column < size; column++)  {
            row[column] = new SudokuField();
            row[column].setFieldValue(this.board[rowIndex][column].getFieldValue());
        }
        return new SudokuRow(row);
    }

    public SudokuColumn getColumn(int columnIndex) {
        SudokuField[] column = new SudokuField[size];
        for (int row = 0; row < size; row++) {
            column[row] = new SudokuField();
            column[row].setFieldValue(this.board[row][columnIndex].getFieldValue());
        }
        return new SudokuColumn(column);
    }

    public SudokuBox getBox(int rowIndex, int columnIndex) {
        SudokuField[] box = new SudokuField[size];
        int boxindex = 0;
        for (int row = ((int) rowIndex / 3) * 3;
             row < (((int) rowIndex / 3) * 3) + 3; row++) {
            for (int col = ((int) columnIndex / 3) * 3;
                 col < (((int) columnIndex / 3) * 3) + 3; col++) {
                box[boxindex] = new SudokuField();
                box[boxindex].setFieldValue(this.board[row][col].getFieldValue());
                boxindex++;
            }
        }
        return new SudokuBox(box);
    }

    protected boolean checkBoard() {
        boolean valid = true;
        for (int index = 0;index < size;index++) {
            valid = getRow(index).verify();
            if (valid == false) {
                break;
            }
            valid = getColumn(index).verify();
            if (valid == false) {
                break;
            }

            valid = getBox(((int) index / 3) * 3,(index % 3) * 3).verify();
            if (valid == false) {
                break;
            }

        }
        return valid;
    }

}


