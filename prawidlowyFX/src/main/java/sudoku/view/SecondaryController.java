package sudoku.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sudoku.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SecondaryController implements Initializable {
    private Stage thisStage;

    /*------------------------ FIELDS REGION ------------------------*/
    @FXML
    private GridPane sudokuBoardGrid;

    private PopOutWindow popOutWindow = new PopOutWindow();
    private SudokuBoard sudokuBoard = new SudokuBoard();
    private SudokuBoard sudokuBoardCopy = new SudokuBoard();
    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    private DifficultyLevel difficultyLevel = new DifficultyLevel();
    public SecondaryController() {
        // We received the first controller, now let's make it usable throughout this controller


        // Create the new stage
        thisStage = new Stage();

        // Load the FXML file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            thisStage.setTitle("Ekran Gry");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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