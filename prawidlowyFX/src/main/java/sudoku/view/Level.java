package sudoku.view;


import sudoku.model.SudokuBoard;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Level {
/// liczba usuniętych pól w zależności od wybranego poziomu
   public int easy = 1;
   public int medium = 30;
   public int hard = 60;

   private Random rand = new Random();
   private Set<CellXY> randomPositions = new HashSet<>();

   private void fillRandomPositionsList(int capacity) {
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

    public SudokuBoard chooseLevel(SudokuBoard sudokuBoard, String level)
            throws EmptyBoardException {
        switch (level) {

            case "Easy": {
                fillRandomPositionsList(easy);
                break;
            }
            case "Medium": {
                fillRandomPositionsList(medium);
                break;
            }
            case "Hard": {
                fillRandomPositionsList(hard);
                break;
            }
        }

        for (CellXY it : randomPositions) {
            sudokuBoard.set(it.getAxisX(), it.getAxisY(), 0);
        }

        return sudokuBoard;
    }
}

