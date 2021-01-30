package sudoku.model;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class SudokuBoardRepository {
    private final EntityManager entityManager;

    public SudokuBoardRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    public Optional<SudokuBoard> findById(Integer id) {
        SudokuBoard sudokuBoard = entityManager.find(SudokuBoard.class, id);
        return sudokuBoard != null ?
                Optional.of(sudokuBoard) :
                Optional.empty();
    }
    public List<SudokuBoard> findAll() {
        return  entityManager.
                createQuery("select a from SudokuBoard a", SudokuBoard.class).
                getResultList();
    }
    public Optional<SudokuBoard> save(SudokuBoard sudokuBoard){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(sudokuBoard);
            entityManager.getTransaction().commit();
            return Optional.of(sudokuBoard);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
