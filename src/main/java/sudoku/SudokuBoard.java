package sudoku;



/*
    //Sprawdzanie czy wtyczka checkstyle działa
    //Po odkomentowaniu checkstyle informuje o braku spacji między znakami
    for(int i=0;i<1;i++){
    System.out.print("Test");
    }
*/

public class SudokuBoard {
    private int[][] board = new int[9][9];
    public void solveGame() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(this);
    }


    public int[][] getCopyOfBoard() {
        int[][] copiedBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copiedBoard[i][j] = board[i][j];
            }
        }
        return copiedBoard;
    }

    public void showBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(get(i,j) + " ");
            }
            System.out.print("\n");
        }
    }

    public boolean checkCell(int row, int column) {
        int active = board[row][column];
        int subrow = row - row % 3;
        int subcol = column - column % 3;
        for (int i = 0; i < 9; i++) {
            if (active == board[row][i]) {
                if (column != i) {
                    return false;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            if (active == board[i][column]) {
                if (row != i) {
                    return false;
                }
            }
        }
       for (int i = 0; i < 3; i++) {
           for (int j = 0;j < 3; j++) {
               if (active == board [subrow + i][subcol + j]) {
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
        return board[row][column];
    }

    public void set(int row, int column, int value) {
        this.board[row][column] = value;
    }




}


