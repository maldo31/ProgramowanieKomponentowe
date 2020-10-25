import java.util.Random;

public class SudokuBoard {
    int[][] board = new int[9][9];
    public void showBoard(){
        for(int i=0; i< 9; i++){
            for(int j=0; j<9; j++){
                System.out.print(board[i][j]+ " ");
            }
            System.out.print("\n");
        }
    }}



