package ch.juventus.autovermietung.service;

import ch.juventus.autovermietung.model.Booking;
import ch.juventus.autovermietung.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BookingService {

	private static final Logger APP_LOG = LoggerFactory.getLogger("APP");
    private static final Logger BOOKING_LOG = LoggerFactory.getLogger("BOOKING_LOG");
    private static final Logger ERROR_LOG = LoggerFactory.getLogger("org.springframework");
	private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // CREATE – Buchung speichern
    public Booking createBooking(Booking booking) {
        Booking saved = bookingRepository.save(booking);
        BOOKING_LOG.info("Buchung erstellt: Auto ID {}, Kunde ID {}, Preis {}", 
                         saved.getAuto().getId(), saved.getCustomer().getId(), saved.getGesamtPreis());
        return saved;
    }

    // READ – alle Buchungen
    public List<Booking> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        APP_LOG.info("Alle Buchungen abgerufen, Anzahl: {}", bookings.size());
        return bookings;
    }

    // READ – einzelne Buchung
    public Optional<Booking> getBookingById(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            APP_LOG.info("Buchung abgerufen: ID {}", id);
        } else {
            ERROR_LOG.warn("Buchung nicht gefunden: ID {}", id);
        }
        return booking;
    }

 // DELETE – Buchung löschen
    public boolean deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            APP_LOG.warn("Buchung gelöscht: ID {}", id);
            return true;
        } else {
            ERROR_LOG.warn("Löschen fehlgeschlagen, Buchung nicht gefunden: ID {}", id);
            return false;
        }
    }
}
