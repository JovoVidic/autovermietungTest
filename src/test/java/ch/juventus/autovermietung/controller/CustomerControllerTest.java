package ch.juventus.autovermietung.controller;

import ch.juventus.autovermietung.model.Customer;
import ch.juventus.autovermietung.service.CustomerService;
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

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private Customer customer1;

    @BeforeEach
    void setup() {
        customer1 = new Customer(1L, "Max", "Mustermann", "max@example.com", "0123456798");
    }

    @Test
    void testGetAllCustomers() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(List.of(customer1));

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].vorname").value("Max"));

        verify(customerService, times(1)).getAllCustomers();
    }

    @Test
    void testGetCustomerById_Found() throws Exception {
        when(customerService.getCustomerById(1L)).thenReturn(Optional.of(customer1));

        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nachname").value("Mustermann"));
    }
}
