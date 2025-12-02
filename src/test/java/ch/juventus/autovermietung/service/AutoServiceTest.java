package ch.juventus.autovermietung.service;

import ch.juventus.autovermietung.model.*;
import ch.juventus.autovermietung.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AutoServiceTest {

	private AutoRepository autoRepository;
	private CustomerRepository customerRepository;
	private BookingRepository bookingRepository;
	private AutoService autoService;


	@BeforeEach
	void setup() {
	    autoRepository = Mockito.mock(AutoRepository.class);
	    customerRepository = Mockito.mock(CustomerRepository.class);
	    bookingRepository = Mockito.mock(BookingRepository.class);
	    autoService = new AutoService(autoRepository, customerRepository, bookingRepository);
	}

    @Test
    void testCreateAuto() {
        Auto auto = new Auto(null, "BMW", "X5", "ZH123456", true, 99.0);

        when(autoRepository.save(auto)).thenReturn(new Auto(1L, "BMW", "X5", "ZH123456", true, 99.0));

        Auto result = autoService.createAuto(auto);

        assertNotNull(result.getId());
        assertEquals("BMW", result.getMarke());
        verify(autoRepository, times(1)).save(auto);
    }

    @Test
    void testGetAllAutos() {
        List<Auto> autos = List.of(
                new Auto(1L, "Audi", "A4", "ZH111111", true, 50.0),
                new Auto(2L, "VW", "Golf", "ZH222222", true, 60.0)
        );

        when(autoRepository.findAll()).thenReturn(autos);

        List<Auto> result = autoService.getAllAutos();

        assertEquals(2, result.size());
        verify(autoRepository, times(1)).findAll();
    }

    @Test
    void testGetAutoById() {
        Auto auto = new Auto(1L, "Tesla", "Model 3", "ZH333333", true, 120.0);

        when(autoRepository.findById(1L)).thenReturn(Optional.of(auto));

        Optional<Auto> result = autoService.getAutoById(1L);

        assertTrue(result.isPresent());
        assertEquals("Tesla", result.get().getMarke());
    }

    @Test
    void testUpdateAuto() {
        Auto existing = new Auto(1L, "BMW", "X1", "ZH123", true, 80.0);
        Auto updated  = new Auto(null, "BMW", "X5", "ZH123", false, 99.0);

        when(autoRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(autoRepository.save(existing)).thenReturn(existing);

        Optional<Auto> result = autoService.updateAuto(1L, updated);

        assertTrue(result.isPresent());
        assertEquals("X5", result.get().getModell());
        assertFalse(result.get().isVerfuegbar());
    }

    @Test
    void testDeleteAuto() {
        when(autoRepository.existsById(1L)).thenReturn(true);

        boolean deleted = autoService.deleteAuto(1L);

        assertTrue(deleted);
        verify(autoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteAuto_NotFound() {
        when(autoRepository.existsById(1L)).thenReturn(false);

        boolean deleted = autoService.deleteAuto(1L);

        assertFalse(deleted);
        verify(autoRepository, never()).deleteById(anyLong());
    }
    
    @Test
    void testVermieteAuto() {
        Auto auto = new Auto(1L, "BMW", "X5", "ZH123456", true, 100.0);
        Customer customer = new Customer(1L, "Max", "Mustermann", "max@test.ch", "0123456789");

        LocalDate start = LocalDate.of(2025, 12, 10);
        LocalDate end = LocalDate.of(2025, 12, 12); // 3 Tage

        when(autoRepository.findById(1L)).thenReturn(Optional.of(auto));
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(autoRepository.save(auto)).thenReturn(auto);
        when(bookingRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        double preis = autoService.vermieteAuto(1L, 1L, start, end);

        assertEquals(300.0, preis);
        assertFalse(auto.isVerfuegbar());
        verify(autoRepository, times(1)).save(auto);
        verify(bookingRepository, times(1)).save(any());
    }
    
    @Test
    void testRueckgabeAuto() {
        Auto auto = new Auto(1L, "BMW", "X5", "ZH123456", false, 100.0);

        when(autoRepository.findById(1L)).thenReturn(Optional.of(auto));
        when(autoRepository.save(auto)).thenReturn(auto);

        autoService.rueckgabeAuto(1L);

        assertTrue(auto.isVerfuegbar());
        verify(autoRepository, times(1)).save(auto);
    }


}