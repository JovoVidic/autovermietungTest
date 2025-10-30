package ch.juventus.autovermietung.controller;

import ch.juventus.autovermietung.model.Auto;
import ch.juventus.autovermietung.service.AutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autos")
public class AutoController {

    private final AutoService autoService;

    public AutoController(AutoService autoService) {
        this.autoService = autoService;
    }

    @GetMapping
    public List<Auto> alleAutos() {
        return autoService.getAlleAutos();
    }

    @PostMapping
    public Auto neuesAuto(@RequestBody Auto auto) {
        return autoService.neuesAutoHinzufuegen(auto);
    }
}