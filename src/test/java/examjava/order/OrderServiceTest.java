package examjava.order;


import examjava.customer.Customer;
import examjava.customerAddress.CustomerAddress;
import examjava.order.OrderDto;
import examjava.order.OrderService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderServiceTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:alpine");

    @Autowired
    OrderService orderService;

    @Test
    @Order(1)
    void setup(){
        for (int i = 0; i < 10; i++) {
            List<Long> products = new ArrayList<>();
            orderService.saveOrder(new OrderDto(
                    100,
                    1,
                    products,
                    1
                    ));
        }
    }

    @Test
    @Order(2)
    void getOrders() {
        List<CustomerOrder> orders = orderService.getOrders();
        assert orders.size() == 10;
    }

    @Test
    @Order(3)
    void getOrderById() {
        var order = orderService.getOrderById(1);
        assert order != null;
        assert order.getShippingCharge() == 100;
    }

    @Test
    @Order(4)
    void saveOrder() {
        List<Long> products = new ArrayList<>();
        assert orderService.saveOrder(new OrderDto(
                100,
                1,
                products,
                1
        )).getShippingCharge() == (100);
    }

    @Test
    @Order(5)
    void deleteOrderById() {
        orderService.deleteOrderById(1);
        assert orderService.getOrders().size() == 10;
    }

}
