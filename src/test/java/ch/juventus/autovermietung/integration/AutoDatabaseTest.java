package ch.juventus.autovermietung.integration;

import ch.juventus.autovermietung.model.Auto;
import ch.juventus.autovermietung.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AutoDatabaseTest {

    @Autowired
    private AutoRepository autoRepository;

    @Test
    public void testAutoDatabaseConnection() {
    	
    	 // Datenbank vor dem Test leeren
        autoRepository.deleteAll();
    	
        // 5 Testautos einfügen
    	autoRepository.save(new Auto(null, "Audi", "A4", "AB-1234", true, 50.0));
        autoRepository.save(new Auto(null, "BMW", "X3", "BC-5678", true, 60.0));
        autoRepository.save(new Auto(null, "Mercedes", "C-Klasse", "MB-9012", false, 70.0));
        autoRepository.save(new Auto(null, "VW", "Golf", "VW-3456", true, 40.0));
        autoRepository.save(new Auto(null, "Tesla", "Model 3", "TS-7890", false, 80.0));

        // Alle Autos aus der DB abrufen
        List<Auto> autos = autoRepository.findAll();
        autos.forEach(System.out::println);

        // Einfacher Test, dass die Autos vorhanden sind
        assertThat(autos).hasSize(5);
    }
}