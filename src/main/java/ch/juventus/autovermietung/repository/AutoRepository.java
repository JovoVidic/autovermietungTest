package ch.juventus.autovermietung.repository;

import ch.juventus.autovermietung.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {
    // Optional: eigene Methoden wie findByMarke(String marke) etc.
}
