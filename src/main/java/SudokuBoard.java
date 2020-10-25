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

    public static void fillBoard(){
        Random random = new Random();
        boolean flag;
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                //if(board[i][j]==0){
                    flag = checkCell(i, j);
                    int k=0;
                    int randomDigit = 1+random.nextInt(9);
                    int[] digits = new int[10];
                    int s=0;
                    while(randomDigit<10){

                        digits[s]=randomDigit;

                        randomDigit=randomDigit+1;
                        s++;

                    }
                    int l=0;
                    while(s<9){
                        digits[s]=1+l;

                        l++;
                        s++;
                    }
                    System.out.print("||||||||||||||||||||||||||||\n");
                    do{
                        board[i][j] = digits[k];
                        System.out.print("-------------"+k+"----------\n");
                        showBoard();
                        k++;
                        ;
                    }
                    while(checkCell(i,j) ==false && k<10);
                    if (k==10){
                        if(j>0){
                            j=j-2;
                        }
                        else {

                            if(i>1){
                                i=i-1;
                            }

                        }
                    }


                    }
                }
            }
        //}


    public static void main(String[] args) {
        showBoard();
        fillBoard();
        System.out.print("Przerwa\n\n");
        showBoard();
    }

}


