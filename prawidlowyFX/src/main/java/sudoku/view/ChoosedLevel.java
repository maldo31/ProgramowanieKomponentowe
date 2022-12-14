package sudoku.view;

import sudoku.model.SudokuBoard;
import sudoku.view.exception.EmptyBoardException;

import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

public enum ChoosedLevel {
    EASY(1),MEDIUM(51),HARD(58);
    int fieldsToRemove;
    ChoosedLevel(int fieldsToRemove) {
        this.fieldsToRemove = fieldsToRemove;
    }
    private Random rand = new Random();
    private Set<CellXY> randomPositions;


    private void fillRandomPositionsList(int capacity) {
        randomPositions = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            boolean isElementAdded = false;

            while (!isElementAdded) {
                int axisX = rand.nextInt(9);
                int axisY = rand.nextInt(9);
                /// Hashshet pozwala pominąc sytuacje gdy usuniemy dwa razy to samo pole
                isElementAdded = randomPositions.add(new CellXY(axisX, axisY));
            }
        }
    }
    public SudokuBoard chooseLevel(SudokuBoard sudokuBoard)
            throws EmptyBoardException {
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.languages");
        fillRandomPositionsList(fieldsToRemove);

        for (CellXY it : randomPositions) {
            sudokuBoard.set(it.getAxisX(), it.getAxisY(), 0);
        }

        return sudokuBoard;
    }
}

