package sudoku.view;

public enum ChoosedLevel {
    EASY(1),MEDIUM(51),HARD(58);
    int fieldsToRemove;
    ChoosedLevel(int fieldsToRemove){
        this.fieldsToRemove=fieldsToRemove;
    }
}
