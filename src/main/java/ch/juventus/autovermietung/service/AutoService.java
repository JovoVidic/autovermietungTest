package ch.juventus.autovermietung.service;

import ch.juventus.autovermietung.model.Auto;
import ch.juventus.autovermietung.repository.AutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoService {

    private static final Logger logger = LoggerFactory.getLogger(AutoService.class);

    private final AutoRepository autoRepository;

    public AutoService(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    public List<Auto> getAlleAutos() {
        logger.info("Alle Autos werden aus der Datenbank geladen.");
        return autoRepository.findAll();
    }

    public Auto neuesAutoHinzufuegen(Auto auto) {
        logger.info("Neues Auto wird gespeichert: {}", auto.getKennzeichen());
        return autoRepository.save(auto);
    }
}