package ch.juventus.autovermietung.service;

import ch.juventus.autovermietung.model.Auto;
import ch.juventus.autovermietung.repository.AutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AutoServiceTest {

    private AutoRepository autoRepository;
    private AutoService autoService;

    @BeforeEach
    void setup() {
        autoRepository = Mockito.mock(AutoRepository.class);
        autoService = new AutoService(autoRepository);
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
}