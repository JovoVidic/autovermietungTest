package ch.juventus.autovermietung.repository;

import ch.juventus.autovermietung.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Optional: z.B. findByCustomerId(Long customerId)
}
