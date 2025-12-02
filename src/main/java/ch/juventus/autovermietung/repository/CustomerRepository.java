package ch.juventus.autovermietung.repository;

import ch.juventus.autovermietung.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Optional: z.B. findByEmail(String email)
}