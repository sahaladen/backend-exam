package examjava.product;

import examjava.product.Product;
import examjava.product.ProductDto;
import examjava.product.ProductService;
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
public class ProductServiceTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:alpine");

    @Autowired
    ProductService productService;

    @Test
    @Order(1)
    void setup(){
        for (int i = 0; i < 10; i++) {
            productService.saveProduct(new ProductDto(
                    "TastyCandy",
                    "Tasty",
                    123,
                    123
            ));
        }
    }

    @Test
    @Order(2)
    void getProducts() {
        List<Product> products = productService.getProducts();
        assert products.size() == 10;
    }

    @Test
    @Order(3)
    void getProductById() {
        var product = productService.getProductById(1);
        assert product != null;
        assert product.getProductName().equals("TastyCandy");
    }

    @Test
    @Order(4)
    void saveProduct() {
        assert productService.saveProduct(new ProductDto(
                "TastyCandy",
                "Tasty",
                123,
                123
        )).getProductName().equals("TastyCandy");
    }

    @Test
    @Order(5)
    void deleteProductById() {
        productService.deleteProductById(1);
        assert productService.getProducts().size() == 10;
    }

}
