package ch.juventus.autovermietung.service;

import ch.juventus.autovermietung.model.Customer;
import ch.juventus.autovermietung.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // CREATE
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // READ – alle Kunden
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // READ – einzelner Kunde
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // UPDATE
    public Optional<Customer> updateCustomer(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(existing -> {
                    existing.setVorname(updatedCustomer.getVorname());
                    existing.setNachname(updatedCustomer.getNachname());
                    existing.setEmail(updatedCustomer.getEmail());
                    existing.setTelefonnummer(updatedCustomer.getTelefonnummer());
                    return customerRepository.save(existing);
                });
    }

    // DELETE
    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
