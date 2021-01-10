package sudoku.view;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import sudoku.model.SudokuBoard;

public class Level { // liczba usuniętych pól w zależności od wybranego poziomu

   public ChoosedLevel choosedLevel;

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
            case "Easy": case "Łatwy": case "Fácil":  {
                ChoosedLevel choosedLevel = ChoosedLevel.EASY;
                fillRandomPositionsList(choosedLevel.fieldsToRemove);
                break;
            }
            case "Medium": case "Średni": case "Medio": {
                ChoosedLevel choosedLevel = ChoosedLevel.MEDIUM;
                fillRandomPositionsList(choosedLevel.fieldsToRemove);
                break;
            }
            case "Hard": case "Trudny": case "Difícil": {
                ChoosedLevel choosedLevel = ChoosedLevel.HARD;
                fillRandomPositionsList(choosedLevel.fieldsToRemove);
                break;
            }
            default:
                break;
        }

        for (CellXY it : randomPositions) {
            sudokuBoard.set(it.getAxisX(), it.getAxisY(), 0);
        }

        return sudokuBoard;
    }
}

