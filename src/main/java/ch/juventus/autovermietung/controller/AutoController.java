package ch.juventus.autovermietung.controller;

import ch.juventus.autovermietung.model.Auto;
import ch.juventus.autovermietung.service.AutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/autos")
public class AutoController {

    private final AutoService autoService;

    public AutoController(AutoService autoService) {
        this.autoService = autoService;
    }
    
    // GET alle Autos
    @GetMapping
    public List<Auto> getAllAutos() {
        return autoService.getAllAutos();
    }

    // GET Auto nach ID
    @GetMapping("/{id}")
    public Auto getAuto(@PathVariable Long id) {
        return autoService.getAutoById(id)
                .orElseThrow(() -> new RuntimeException("Auto nicht gefunden mit ID " + id));
    }

    // POST – neues Auto hinzufügen
    @PostMapping
    public Auto neuesAuto(@RequestBody Auto auto) {
        return autoService.createAuto(auto);
    }

    // PUT – Auto aktualisieren
    @PutMapping("/{id}")
    public Auto updateAuto(@PathVariable Long id, @RequestBody Auto updatedAuto) {
        return autoService.updateAuto(id, updatedAuto)
                .orElseThrow(() -> new RuntimeException("Auto nicht gefunden mit ID " + id));
    }

    // DELETE – Auto löschen
    @DeleteMapping("/{id}")
    public void deleteAuto(@PathVariable Long id) {
        if (!autoService.deleteAuto(id)) {
            throw new RuntimeException("Auto nicht gefunden mit ID " + id);
        }
    }
}
