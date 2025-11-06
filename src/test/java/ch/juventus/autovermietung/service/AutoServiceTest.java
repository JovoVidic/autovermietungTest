package ch.juventus.autovermietung.service;

import ch.juventus.autovermietung.model.Auto;
import ch.juventus.autovermietung.repository.AutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AutoServiceTest {

    private AutoRepository autoRepository;
    private AutoService autoService;

    @BeforeEach
    void setUp() {
        autoRepository = Mockito.mock(AutoRepository.class);
        autoService = new AutoService(autoRepository);
    }

    @Test
    void testGetAlleAutos() {
        Auto auto1 = new Auto(1L, "VW", "Golf", "ZH123", true, 50.0);
        Auto auto2 = new Auto(2L, "BMW", "X3", "ZH456", true, 80.0);

        when(autoRepository.findAll()).thenReturn(Arrays.asList(auto1, auto2));

        List<Auto> autos = autoService.getAlleAutos();

        assertEquals(2, autos.size());
        verify(autoRepository, times(1)).findAll();
    }

    @Test
    void testNeuesAutoHinzufuegen() {
        Auto auto = new Auto(2L, "Audi", "A4", "ZH789", true, 70.0);
        Auto gespeichertesAuto = new Auto(1L, "Audi", "A4", "ZH789", true, 70.0);

        when(autoRepository.save(auto)).thenReturn(gespeichertesAuto);

        Auto result = autoService.neuesAutoHinzufuegen(auto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(autoRepository, times(1)).save(auto);
    }
}