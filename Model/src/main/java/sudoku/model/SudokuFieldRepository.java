package sudoku.model;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class SudokuFieldRepository {
    private EntityManager entityManager;
    public SudokuFieldRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    public Optional<SudokuField> findById(Integer id) {
        SudokuField sudokuField = entityManager.find(SudokuField.class, id);
        return sudokuField != null ? Optional.of(sudokuField) : Optional.empty();
    }

    public List<SudokuField> findAll() {
        return entityManager.createQuery("from SudokuField").getResultList();
    }
    public Optional<SudokuField> findByName(String name) {
        SudokuField sudokuField = entityManager.createQuery("SELECT b FROM SudokuField b WHERE b.name = :name",
                SudokuField.class).setParameter("name", name).getSingleResult();
        return sudokuField != null ? Optional.of(sudokuField) : Optional.empty();
    }

    public Optional<SudokuField> save(SudokuField sudokuField) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(sudokuField);
            entityManager.getTransaction().commit();
            return Optional.of(sudokuField);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
