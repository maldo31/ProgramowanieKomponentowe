package sudoku.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import sudoku.model.BacktrackingSudokuSolver;
import sudoku.model.SudokuBoard;


public class BoardWindowControl {

    /*------------------------ FIELDS REGION ------------------------*/
    @FXML
    private GridPane sudokuBoardGrid;

    private PopOutWindow popOutWindow = new PopOutWindow();
    private SudokuBoard sudokuBoard = new SudokuBoard();

    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

    /*------------------------ METHODS REGION ------------------------*/
    @FXML
    private void initialize(){
        solver.solve(sudokuBoard);

        fillGrid();
    }

    /**
     * Method check if TextField contains permitted number.
     *
     * @param textField - TextField object
     */


    /**
     * Method fill GripPane in .fxml file.
     */
    private void fillGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setMinSize(50, 50);
                textField.setFont(Font.font(18));
                if (sudokuBoard.get(i, j) != 0) {
                    textField.setDisable(true);
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                }
                sudokuBoardGrid.add(textField, i, j);
            }
        }
    }




}
