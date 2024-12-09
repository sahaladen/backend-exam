package examjava.product;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;


@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepoTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:alpine");

    @Autowired
    ProductRepo productRepo;
    @Test
    void connectionEstablished(){
        assert postgres.isCreated();
        assert postgres.isRunning();
    }

    @Test
    void shouldInsertProduct(){
        var product = new Product("TastyCandy",
                "Tasty",
                123,
                ProductStatus.IN_STOCK,
                123);
        productRepo.save(product);
        assert productRepo.findById(product.getProductId()).isPresent();
    }



    @Test
    void shouldDeleteProduct(){
        var product = productRepo.save(new Product("TastyCandy",
                "Tasty",
                123,
                ProductStatus.IN_STOCK,
                123));
        productRepo.delete(product);
        assert productRepo.findById(product.getProductId()).isEmpty();
    }




}
