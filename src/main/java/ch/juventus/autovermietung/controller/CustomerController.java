package ch.juventus.autovermietung.controller;

import ch.juventus.autovermietung.model.Customer;
import ch.juventus.autovermietung.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private static final Logger APP_LOG = LoggerFactory.getLogger("APP");
	private static final Logger ERROR_LOG = LoggerFactory.getLogger("org.springframework");
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // GET alle Kunden
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // GET Kunde nach ID
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        APP_LOG.debug("GET-Anfrage für Kunde ID {}", id);
        return customerService.getCustomerById(id)
                .orElseThrow(() -> {
                    ERROR_LOG.warn("Kunde ID {} nicht gefunden", id);
                    return new RuntimeException("Kunde nicht gefunden");
                });
    }

    // POST – neuen Kunden hinzufügen
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        Customer saved = customerService.createCustomer(customer);
        APP_LOG.info("Neuer Kunde erstellt: {} {}", saved.getVorname(), saved.getNachname());
        return saved;
    }

    // PUT – Kunde aktualisieren
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomer(id, updatedCustomer)
                .orElseThrow(() -> new RuntimeException("Kunde nicht gefunden mit ID " + id));
    }

    // DELETE – Kunde löschen
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        if (!customerService.deleteCustomer(id)) {
            throw new RuntimeException("Kunde nicht gefunden mit ID " + id);
        }
    }
}
