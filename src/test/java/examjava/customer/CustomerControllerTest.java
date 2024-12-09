package examjava.customer;

import examjava.customerAddress.CustomerAddress;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)

public class CustomerControllerTest {

    static List<Customer> customers = new ArrayList<>();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService service;

    @BeforeAll
    static void setUpBeforeClass() {
        List<CustomerAddress> address = new ArrayList<>();
        address.add(new CustomerAddress("Address"));
        for (int i = 0; i < 10; i++) {
            customers.add(new Customer(
                    "FirstName",
                    "LastName",
                    "phoneNumber",
                    "email",
                    address
            ));
        }
    }

    @Test
    void getCustomers() throws Exception {
        when(service.getCustomers()).thenReturn(customers);
        this.mockMvc.perform(get("/api/customer"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getCustomerById() throws Exception {
        when(service.getCustomerById(1)).thenReturn(customers.get(1));
        System.out.println(new ObjectMapper().writeValueAsString(service.getCustomerById(1)));
        this.mockMvc.perform(
                        get("/api/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("FirstName"));

    }

    @Test
        void saveCustomer () throws Exception {
            when(service.saveCustomer(new CustomerDto())).thenReturn(customers.get(1));
            this.mockMvc.perform(
                    post("/api/customer")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(customers.get(1)))
            ).andExpect(status().isCreated());
        }

    }

