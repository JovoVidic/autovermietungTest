package ch.juventus.autovermietung.service;

import ch.juventus.autovermietung.model.Customer;
import ch.juventus.autovermietung.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @BeforeEach
    void setup() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerService = new CustomerService(customerRepository);
    }

    @Test
    void testCreateCustomer() {
        Customer customer = new Customer(null, "Max", "Mustermann", "max@example.com", "0123456789");
        when(customerRepository.save(customer)).thenReturn(new Customer(1L, "Max", "Mustermann", "max@example.com", "0123456789"));

        Customer result = customerService.createCustomer(customer);

        assertNotNull(result.getId());
        assertEquals("Max", result.getVorname());
        assertEquals("0123456789", result.getTelefonnummer());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> customers = List.of(
                new Customer(1L, "Max", "Mustermann", "max@example.com", "0123456789"),
                new Customer(2L, "Anna", "Müller", "anna@example.com", "0123456789")
        );
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = customerService.getAllCustomers();

        assertEquals(2, result.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testGetCustomerById() {
        Customer customer = new Customer(1L, "Max", "Mustermann", "max@example.com", "0123456789");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerService.getCustomerById(1L);

        assertTrue(result.isPresent());
        assertEquals("Max", result.get().getVorname());
    }

    @Test
    void testUpdateCustomer() {
        Customer existing = new Customer(1L, "Max", "Mustermann", "max@example.com", "0123456789");
        Customer updated = new Customer(null, "Maximilian", "Mustermann", "maximilian@example.com", "9876543210");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(customerRepository.save(existing)).thenReturn(existing);

        Optional<Customer> result = customerService.updateCustomer(1L, updated);

        assertTrue(result.isPresent());
        assertEquals("Maximilian", result.get().getVorname());
        assertEquals("9876543210", result.get().getTelefonnummer());
    }

    @Test
    void testDeleteCustomer() {
        when(customerRepository.existsById(1L)).thenReturn(true);

        boolean deleted = customerService.deleteCustomer(1L);

        assertTrue(deleted);
        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCustomer_NotFound() {
        when(customerRepository.existsById(1L)).thenReturn(false);

        boolean deleted = customerService.deleteCustomer(1L);

        assertFalse(deleted);
        verify(customerRepository, never()).deleteById(anyLong());
    }
}
