package sudoku;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class SecondaryController {

    @FXML
    private GridPane sudokuBoardGrid;
    private SudokuBoard sudokuBoard = new SudokuBoard();
    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    @FXML
    private void initialize() throws CloneNotSupportedException{
        solver.solve(sudokuBoard);
        fillGrid();
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    private void fillGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <0; j++) {
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