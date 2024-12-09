package examjava.order;


import examjava.customer.Customer;
import examjava.customerAddress.CustomerAddress;

import examjava.order.OrderRepo;
import examjava.order.OrderStatus;
import examjava.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;


@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepoTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:alpine");

    @Autowired
    OrderRepo orderRepo;
    @Test
    void connectionEstablished(){
        assert postgres.isCreated();
        assert postgres.isRunning();
    }

    @Test
    void shouldInsertOrder(){
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        var order = new CustomerOrder(
                100,
                2000,
                OrderStatus.NOT_SHIPPED,
                new Customer(),
                products,
                new CustomerAddress());
        orderRepo.save(order);
        assert orderRepo.findById(order.getOrderId()).isPresent();
    }



    @Test
    void shouldDeleteOrder(){
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        var order = orderRepo.save(new CustomerOrder(
                100,
                2000,
                OrderStatus.NOT_SHIPPED,
                new Customer(),
                products,
                new CustomerAddress()));
        orderRepo.delete(order);
        assert orderRepo.findById(order.getOrderId()).isEmpty();
    }




}
