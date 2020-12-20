package sudoku.view;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import sudoku.model.*;

public class DifficultyLevel {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final int BASIC_LEVEL = 5;
    public static final int[] MULTIPLIER_LEVEL_ARRAY = {1, 2, 3, 4};

    private Random rand = new Random();
    private Set<FieldCoordinates> randomPositions = new HashSet<>();

    /*------------------------ METHODS REGION ------------------------*/

    /**
     * Method remove certain number of fields.
     *
     * @param capacity - integer number of field to remove
     */
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

    /**
     * Method set level of difficulty in game.
     *
     * @param sudokuBoard SudokuBoard object
     * @param level       String that holds value of level
     * @return {@exception}  UnknownLevelException - own type of exception
     */
    public SudokuBoard chooseLevel(SudokuBoard sudokuBoard, String level)
            throws EmptyBoardException {


        switch (level) {

            case "Easy": {
                fillRandomPositionsList(BASIC_LEVEL * MULTIPLIER_LEVEL_ARRAY[1]);
                break;
            }
            case "Medium": {
                fillRandomPositionsList(BASIC_LEVEL * MULTIPLIER_LEVEL_ARRAY[2]);
                break;
            }
            case "Hard": {
                fillRandomPositionsList(BASIC_LEVEL * MULTIPLIER_LEVEL_ARRAY[3]);
                break;
            }
            default: {
                fillRandomPositionsList(BASIC_LEVEL * MULTIPLIER_LEVEL_ARRAY[0]);
            }
        }

        for (FieldCoordinates it : randomPositions) {
            sudokuBoard.set(it.getAxisX(), it.getAxisY(), 0);
        }

        return sudokuBoard;
    }
}

