package ch.juventus.autovermietung.service;

import ch.juventus.autovermietung.model.Booking;
import ch.juventus.autovermietung.model.Auto;
import ch.juventus.autovermietung.model.Customer;
import ch.juventus.autovermietung.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    private BookingRepository bookingRepository;
    private BookingService bookingService;

    @BeforeEach
    void setup() {
        bookingRepository = Mockito.mock(BookingRepository.class);
        bookingService = new BookingService(bookingRepository);
    }

    @Test
    void testCreateBooking() {
        Auto auto = new Auto(1L, "Audi", "A4", "ZH1234", true, 50.0);
        Customer customer = new Customer(1L, "Max", "Mustermann", "max@example.com", "0123456789");
        Booking booking = new Booking(null, auto, customer, LocalDate.now(), LocalDate.now().plusDays(2), 150.0);

        when(bookingRepository.save(booking)).thenReturn(new Booking(1L, auto, customer, LocalDate.now(), LocalDate.now().plusDays(2), 150.0));

        Booking result = bookingService.createBooking(booking);

        assertNotNull(result.getId());
        assertEquals(150.0, result.getGesamtPreis());
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void testGetAllBookings() {
        Booking b1 = new Booking(1L, null, null, LocalDate.now(), LocalDate.now().plusDays(1), 50.0);
        Booking b2 = new Booking(2L, null, null, LocalDate.now(), LocalDate.now().plusDays(3), 150.0);

        when(bookingRepository.findAll()).thenReturn(List.of(b1, b2));

        List<Booking> result = bookingService.getAllBookings();

        assertEquals(2, result.size());
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    void testGetBookingById() {
        Booking booking = new Booking(1L, null, null, LocalDate.now(), LocalDate.now().plusDays(2), 100.0);
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        Optional<Booking> result = bookingService.getBookingById(1L);

        assertTrue(result.isPresent());
        assertEquals(100.0, result.get().getGesamtPreis());
    }

    @Test
    void testDeleteBooking() {
        when(bookingRepository.existsById(1L)).thenReturn(true);

        boolean deleted = bookingService.deleteBooking(1L);

        assertTrue(deleted);
        verify(bookingRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteBooking_NotFound() {
        when(bookingRepository.existsById(1L)).thenReturn(false);

        boolean deleted = bookingService.deleteBooking(1L);

        assertFalse(deleted);
        verify(bookingRepository, never()).deleteById(anyLong());
    }
}
