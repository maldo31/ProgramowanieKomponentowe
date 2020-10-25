import java.util.Random;

public class SudokuBoard {
    private int[][] board = new int[9][9];

    public int[][] getCopyOfBoard(){
        int[][] copiedBoard = new int[9][9];
        for (int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                copiedBoard[i][j] = board[i][j];
            }
        }
        return copiedBoard;
    }
    
    public void showBoard(){
        for(int i=0; i< 9; i++){
            for(int j=0; j<9; j++){
                System.out.print(board[i][j]+ " ");
            }
            System.out.print("\n");
        }
    }

}



