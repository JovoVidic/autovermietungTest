package ch.juventus.autovermietung.controller;

import ch.juventus.autovermietung.model.Booking;
import ch.juventus.autovermietung.service.BookingService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	
	private static final Logger APP_LOG = LoggerFactory.getLogger("APP");
    private static final Logger BOOKING_LOG = LoggerFactory.getLogger("BOOKING_LOG");
    private static final Logger ERROR_LOG = LoggerFactory.getLogger("org.springframework");
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // GET – alle Buchungen
    @GetMapping
    public List<Booking> getAllBookings() {
        APP_LOG.debug("GET alle Buchungen aufgerufen");
        return bookingService.getAllBookings();
    }

    // GET – einzelne Buchung nach ID
    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable Long id) {
        APP_LOG.debug("GET Buchung ID {}", id);
        return bookingService.getBookingById(id)
                .orElseThrow(() -> {
                    ERROR_LOG.warn("Buchung ID {} nicht gefunden", id);
                    return new RuntimeException("Buchung nicht gefunden");
                });
    }

    // POST – neue Buchung erstellen
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        Booking saved = bookingService.createBooking(booking);
        BOOKING_LOG.info("Neue Buchung erstellt: ID {}, Auto ID {}, Kunde ID {}", 
                         saved.getId(), saved.getAuto().getId(), saved.getCustomer().getId());
        APP_LOG.info("Buchung erfolgreich erstellt: ID {}", saved.getId());
        return saved;
    }
    
    // DELETE – Buchung löschen
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        boolean deleted = bookingService.deleteBooking(id);
        if (deleted) {
            APP_LOG.warn("Buchung gelöscht: ID {}", id);
            BOOKING_LOG.info("Buchung gelöscht: ID {}", id);
        } else {
            ERROR_LOG.warn("Buchung konnte nicht gelöscht werden: ID {}", id);
            throw new RuntimeException("Buchung nicht gefunden");
        }
    }
}
