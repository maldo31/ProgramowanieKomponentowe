import java.util.Random;

public class SudokuBoard {
    private static int[][] board = new int[9][9];

    public int[][] getCopyOfBoard() {
        int[][] copiedBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copiedBoard[i][j] = board[i][j];
            }
        }
        return copiedBoard;
    }

    public static void showBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }



    private static boolean checkCell(int row, int column) {
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
       for (int i = 0; i<3;i++){
           for(int j=0;j<3;j++){
               if(active == board[subrow + i][subcol + j]){
                   if(subrow+i!=row){
                       if(subcol+j!=column){
                           return false;
                       }
                   }
               }
           }
       }
        return true;
    }

    public static void fillBoard() {
        int[] randomDigits = new int[81];

        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boolean valid=false;
                if (randomDigits[i * 9 +j] == 0) {
                    randomDigits[i * 9 +j]= 1 + random.nextInt(9);
                    board[i][j] = randomDigits[i * 9 +j];

                    do {
                        if (checkCell(i, j) == true) {
                            valid=true;
                            break;
                        }

                        board[i][j] = board[i][j] % 9 + 1;
                    } while (randomDigits[i * 9 +j] != board[i][j]);
                } else {
                    board[i][j] = board[i][j] % 9 + 1;
                    while (board[i][j] != randomDigits[i * 9 +j]) {
                        if (checkCell(i, j) == true) {
                            valid=true;
                            break;
                        }
                        board[i][j] = board[i][j] % 9 + 1;

                    }
                }
                if (!valid){
                    board[i][j]=0;
                    randomDigits[i * 9 +j]=0;
                    if (j > 0) {

                        j = j - 2;

                    }
                    else if (j == 0) {

                        i = i - 1;
                        j = 8;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        showBoard();
        fillBoard();
        System.out.print("Przerwa\n\n");
        showBoard();

    }

}


