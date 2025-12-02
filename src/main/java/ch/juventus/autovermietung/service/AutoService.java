package ch.juventus.autovermietung.service;

import ch.juventus.autovermietung.model.*;
import ch.juventus.autovermietung.repository.AutoRepository;
import ch.juventus.autovermietung.repository.BookingRepository;
import ch.juventus.autovermietung.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class AutoService {
	
	private static final Logger APP_LOG = LoggerFactory.getLogger("APP");
    private static final Logger ERROR_LOG = LoggerFactory.getLogger("org.springframework");
    private static final Logger DATABASE_LOG = LoggerFactory.getLogger("DATABASE_LOG");

    private final AutoRepository autoRepository;
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;

    public AutoService(AutoRepository autoRepository,
            CustomerRepository customerRepository,
            BookingRepository bookingRepository) {
    	this.autoRepository = autoRepository;
    	this.customerRepository = customerRepository;
    	this.bookingRepository = bookingRepository;
    }

    // CREATE
    public Auto createAuto(Auto auto) {
        Auto saved = autoRepository.save(auto);
        APP_LOG.info("Auto erstellt: {} {}", saved.getMarke(), saved.getModell());
        DATABASE_LOG.info("INSERT Auto ID {} in DB", saved.getId());
        return saved;
    }

    // READ – alle Autos
    public List<Auto> getAllAutos() {
        return autoRepository.findAll();
    }

    // READ – einzelnes Auto
    public Optional<Auto> getAutoById(Long id) {
        APP_LOG.debug("Lese Auto mit ID {}", id);
        return autoRepository.findById(id);
    }

    // UPDATE
    public Optional<Auto> updateAuto(Long id, Auto updatedAuto) {
        APP_LOG.info("Update-Anfrage für Auto ID {}", id);
        return autoRepository.findById(id)
                .map(existingAuto -> {
                    existingAuto.setMarke(updatedAuto.getMarke());
                    existingAuto.setModell(updatedAuto.getModell());
                    existingAuto.setKennzeichen(updatedAuto.getKennzeichen());
                    existingAuto.setVerfuegbar(updatedAuto.isVerfuegbar());
                    existingAuto.setPreisProTag(updatedAuto.getPreisProTag());
                    Auto saved = autoRepository.save(existingAuto);
                    APP_LOG.info("Auto aktualisiert: {} {}", saved.getMarke(), saved.getModell());
                    DATABASE_LOG.info("UPDATE Auto ID {} in DB", saved.getId());
                    return saved;
                });
    }
    
    // DELETE
    public boolean deleteAuto(Long id) {
        if (autoRepository.existsById(id)) {
            autoRepository.deleteById(id);
            APP_LOG.warn("Auto mit ID {} gelöscht", id);
            DATABASE_LOG.info("DELETE Auto ID {} aus DB", id);
            return true;
        }
        ERROR_LOG.warn("Löschen fehlgeschlagen: Auto mit ID {} nicht gefunden", id);
        return false;
    }
    
    //Auto Vermietung
    
    public double vermieteAuto(Long autoId, Long customerId, LocalDate startDatum, LocalDate endDatum) {
        Auto auto = autoRepository.findById(autoId)
                .orElseThrow(() -> {
                    ERROR_LOG.error("Auto ID {} nicht gefunden für Vermietung", autoId);
                    return new RuntimeException("Auto nicht gefunden");
                });
        if (!auto.isVerfuegbar()) {
            ERROR_LOG.warn("Vermietung fehlgeschlagen: Auto ID {} bereits vermietet", autoId);
            throw new RuntimeException("Auto ist bereits vermietet");
        }

        long tage = ChronoUnit.DAYS.between(startDatum, endDatum) + 1;
        double gesamtPreis = tage * auto.getPreisProTag();
        APP_LOG.info("Auto ID {} wird vermietet für {} Tage, Gesamtpreis: {}", autoId, tage, gesamtPreis);

        auto.setVerfuegbar(false);
        autoRepository.save(auto);
        DATABASE_LOG.info("UPDATE Auto ID {}: Verfügbarkeit=false", autoId);

        Booking booking = new Booking();
        booking.setAuto(auto);
        booking.setCustomer(customerRepository.findById(customerId).orElseThrow());
        booking.setStartDatum(startDatum);
        booking.setEndDatum(endDatum);
        booking.setGesamtPreis(gesamtPreis);
        bookingRepository.save(booking);
        LoggerFactory.getLogger("BOOKING_LOG").info("Neue Buchung: Auto ID {}, Kunde ID {}, Preis {}", autoId, customerId, gesamtPreis);

        return gesamtPreis;
    }
    
    //Rückgabe-Logik
    public void rueckgabeAuto(Long autoId) {
        Auto auto = autoRepository.findById(autoId)
                .orElseThrow(() -> {
                    ERROR_LOG.error("Auto ID {} nicht gefunden für Rückgabe", autoId);
                    return new RuntimeException("Auto nicht gefunden");
                });
        auto.setVerfuegbar(true);
        autoRepository.save(auto);
        APP_LOG.info("Auto ID {} zurückgegeben, wieder verfügbar", autoId);
        DATABASE_LOG.info("UPDATE Auto ID {}: Verfügbarkeit=true", autoId);
    }

}