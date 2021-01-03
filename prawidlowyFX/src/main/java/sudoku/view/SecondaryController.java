package sudoku.view;


import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import sudoku.model.BacktrackingSudokuSolver;
import sudoku.model.SudokuBoard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SecondaryController implements Initializable {
    private Stage thisStage;

    @FXML

    private GridPane sudokuBoardGrid;

    private PopOutWindow popOutWindow = new PopOutWindow();
    private SudokuBoard sudokuBoard = new SudokuBoard();
    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    private Level difficultyLevel = new Level();
    public SecondaryController() {

        thisStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Plansza Sudoku");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void fillGrid() {
        StringConverter<Number> converter = new NumberStringConverter();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setMinSize(50, 50);
                textField.setFont(Font.font(18));
                Bindings.bindBidirectional(textField.textProperty(), sudokuBoard.getProperty(i,j),converter);
                if (sudokuBoard.get(i, j) != 0) {
                    textField.setDisable(true);
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                }
                sudokuBoardGrid.add(textField, i, j);

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        solver.solve(sudokuBoard);
        try {
            difficultyLevel.chooseLevel(sudokuBoard, PrimaryController.getLevel());
        } catch (EmptyBoardException e) {
            e.printStackTrace();
        }
        fillGrid();
    }
    public void showStage() {
        thisStage.showAndWait();
    }
}