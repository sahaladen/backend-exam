package examjava.order;

import examjava.customer.Customer;
import examjava.customerAddress.CustomerAddress;
import examjava.order.*;
import examjava.product.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)

public class OrderControllerTest {

    static List<CustomerOrder> orders = new ArrayList<>();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrderService service;

    @BeforeAll
    static void setUpBeforeClass() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        for (int i = 0; i < 10; i++) {
            orders.add(new CustomerOrder(
               100,
                    2000,
                    OrderStatus.NOT_SHIPPED,
                    new Customer(),
                   products,
                    new CustomerAddress()

            ));
        }
    }

    @Test
    void getOrders() throws Exception {
        when(service.getOrders()).thenReturn(orders);
        this.mockMvc.perform(get("/api/order"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void getOrderById() throws Exception {
        when(service.getOrderById(1)).thenReturn(orders.get(1));
        System.out.println(new ObjectMapper().writeValueAsString(service.getOrderById(1)));
        this.mockMvc.perform(
                        get("/api/order/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shippingCharge").value(100));

    }
    @Test
    void saveOrder() throws Exception {
        when(service.saveOrder(new OrderDto())).thenReturn(orders.get(1));
        this.mockMvc.perform(
                post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(orders.get(1)))
        ).andExpect(status().isCreated());
    }



}
