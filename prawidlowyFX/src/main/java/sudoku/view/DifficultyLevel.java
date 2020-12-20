package sudoku.view;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import sudoku.model.*;

public class DifficultyLevel {
/// liczba usuniętych pól w zależności od wybranego poziomu
   public static int easy = 10;
   public static int medium = 30;
   public static int hard = 60;

   private Random rand = new Random();
   private Set<FieldCoordinates> randomPositions = new HashSet<>();

   private void fillRandomPositionsList(int capacity) {
        for (int i = 0; i < capacity; i++) {
            boolean isElementAdded = false;

            while (!isElementAdded) {
                int axisX = rand.nextInt(9);
                int axisY = rand.nextInt(9);
                isElementAdded = randomPositions.add(new FieldCoordinates(axisX, axisY));
            }
        }
    }

    public SudokuBoard chooseLevel(SudokuBoard sudokuBoard, String level)
            throws EmptyBoardException {
        switch (level) {

            case "Easy": {
                fillRandomPositionsList(easy);
            }
            case "Medium": {
                fillRandomPositionsList(medium);
            }
            case "Hard": {
                fillRandomPositionsList(hard);
            }
        }

        for (FieldCoordinates it : randomPositions) {
            sudokuBoard.set(it.getAxisX(), it.getAxisY(), 0);
        }

        return sudokuBoard;
    }
}

