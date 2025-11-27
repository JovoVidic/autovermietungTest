package ch.juventus.carbooking.repository;

import ch.juventus.autovermietung.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class AutoRepositor {
	
	@PersistenceContext
    private EntityManager entityManager;

    // CREATE
    public void insertAuto(Auto auto) {
        entityManager.persist(auto);
    }

    // READ ALL
    public List<Auto> getAllAutos() {
        return entityManager.createQuery("SELECT a FROM Auto a", Auto.class)
                .getResultList();
    }

    // READ BY ID
    public Auto getAutoById(Long id) {
        return entityManager.find(Auto.class, id);
    }

    // UPDATE
    public void updateAuto(Auto auto) {
        entityManager.merge(auto);
    }

    // DELETE
    public void deleteAuto(Long id) {
        Auto auto = getAutoById(id);
        if (auto != null) {
            entityManager.remove(auto);
        }
    }

}
