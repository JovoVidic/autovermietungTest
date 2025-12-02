package ch.juventus.autovermietung.controller;

import ch.juventus.autovermietung.model.Booking;
import ch.juventus.autovermietung.model.Auto;
import ch.juventus.autovermietung.model.Customer;
import ch.juventus.autovermietung.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingController.class)
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    private Booking booking1;

    @BeforeEach
    void setup() {
        Auto auto = new Auto(1L, "Audi", "A4", "ZH123", true, 50.0);
        Customer customer = new Customer(1L, "Max", "Mustermann", "max@example.com", "0987654321");
        booking1 = new Booking(1L, auto, customer, LocalDate.now(), LocalDate.now().plusDays(2), 150.0);
    }

    @Test
    void testGetAllBookings() throws Exception {
        when(bookingService.getAllBookings()).thenReturn(List.of(booking1));

        mockMvc.perform(get("/api/bookings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].gesamtPreis").value(150.0));

        verify(bookingService, times(1)).getAllBookings();
    }

    @Test
    void testGetBookingById_Found() throws Exception {
        when(bookingService.getBookingById(1L)).thenReturn(Optional.of(booking1));

        mockMvc.perform(get("/api/bookings/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testCreateBooking() throws Exception {
        when(bookingService.createBooking(any(Booking.class))).thenReturn(booking1);

        String json = """
                {
                    "auto": {"id":1},
                    "customer": {"id":1},
                    "startDatum": "2025-12-05",
                    "endDatum": "2025-12-07",
                    "gesamtPreis": 150.0
                }
                """;

        mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gesamtPreis").value(150.0));
    }
}
