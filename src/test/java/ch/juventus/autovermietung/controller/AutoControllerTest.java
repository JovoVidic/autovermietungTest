package ch.juventus.autovermietung.controller;

import ch.juventus.autovermietung.model.Auto;
import ch.juventus.autovermietung.service.AutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AutoController.class)
class AutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutoService autoService;

    private Auto auto1;

    @BeforeEach
    void setup() {
        auto1 = new Auto(1L, "Audi", "A4", "ZH123", true, 50.0);
    }

    @Test
    void testGetAllAutos() throws Exception {
        when(autoService.getAllAutos()).thenReturn(List.of(auto1));

        mockMvc.perform(get("/api/autos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].marke").value("Audi"));

        verify(autoService, times(1)).getAllAutos();
    }

    @Test
    void testGetAutoById_Found() throws Exception {
        when(autoService.getAutoById(1L)).thenReturn(Optional.of(auto1));

        mockMvc.perform(get("/api/autos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modell").value("A4"));
    }

    @Test
    void testGetAutoById_NotFound() throws Exception {
        when(autoService.getAutoById(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/autos/2"))
        .andExpect(status().isNotFound());

    }

    @Test
    void testCreateAuto() throws Exception {
        when(autoService.createAuto(any(Auto.class))).thenReturn(auto1);

        String json = """
                {
                    "marke": "Audi",
                    "modell": "A4",
                    "kennzeichen": "ZH123",
                    "verfuegbar": true,
                    "preisProTag": 50.0
                }
                """;

        mockMvc.perform(post("/api/autos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marke").value("Audi"));
    }

    @Test
    void testDeleteAuto() throws Exception {
        when(autoService.deleteAuto(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/autos/1"))
                .andExpect(status().isOk());
    }
}
