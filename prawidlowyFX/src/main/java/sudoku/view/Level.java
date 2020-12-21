package sudoku.view;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import sudoku.model.*;

public class Level {
/// liczba usuniętych pól w zależności od wybranego poziomu
   public int easy = 10;
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

