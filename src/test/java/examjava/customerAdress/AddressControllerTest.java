package examjava.customerAdress;

import examjava.customerAddress.CustomerAddress;
import examjava.customerAddress.CustomerAddressService;
import examjava.customerAddress.CustomerAddressController;
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

@WebMvcTest(controllers = CustomerAddressController.class)

public class AddressControllerTest {

    static List<CustomerAddress> addresses = new ArrayList<>();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerAddressService service;

    @BeforeAll
    static void setUpBeforeClass() {
        for (int i = 0; i < 10; i++) {
            addresses.add(new CustomerAddress("Address " + i));
        }
    }

    @Test
    void getAddresses() throws Exception {
        when(service.getCustomerAddresses()).thenReturn(addresses);
        this.mockMvc.perform(get("/api/customerAddress"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void getAddressById() throws Exception {
        when(service.getCustomerAddressById(1)).thenReturn(addresses.get(1));
        System.out.println(new ObjectMapper().writeValueAsString(service.getCustomerAddressById(1)));
        this.mockMvc.perform(
                        get("/api/customerAddress/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("Address 1"));

    }
    @Test
    void saveAddress() throws Exception {
        when(service.saveCustomerAddress(new CustomerAddress())).thenReturn(addresses.get(1));
        this.mockMvc.perform(
                post("/api/customerAddress")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(addresses.get(1)))
        ).andExpect(status().isCreated());
    }

}
