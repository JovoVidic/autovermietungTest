package ch.juventus.autovermietung.service;

import ch.juventus.autovermietung.model.Auto;
import ch.juventus.autovermietung.repository.AutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoService {

    private final AutoRepository autoRepository;

    public AutoService(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    // CREATE
    public Auto createAuto(Auto auto) {
        return autoRepository.save(auto);
    }

    // READ – alle Autos
    public List<Auto> getAllAutos() {
        return autoRepository.findAll();
    }

    // READ – einzelnes Auto
    public Optional<Auto> getAutoById(Long id) {
        return autoRepository.findById(id);
    }

    // UPDATE
    public Optional<Auto> updateAuto(Long id, Auto updatedAuto) {
        return autoRepository.findById(id)
                .map(existingAuto -> {
                    existingAuto.setMarke(updatedAuto.getMarke());
                    existingAuto.setModell(updatedAuto.getModell());
                    existingAuto.setKennzeichen(updatedAuto.getKennzeichen());
                    existingAuto.setVerfuegbar(updatedAuto.isVerfuegbar());
                    existingAuto.setPreisProTag(updatedAuto.getPreisProTag());
                    return autoRepository.save(existingAuto);
                });
    }

    // DELETE
    public boolean deleteAuto(Long id) {
        if (autoRepository.existsById(id)) {
            autoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}