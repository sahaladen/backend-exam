package examjava.product;

import examjava.product.Product;
import examjava.product.ProductController;
import examjava.product.ProductDto;
import examjava.product.ProductService;
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

@WebMvcTest(controllers = ProductController.class)

public class ProductControllerTest {

    static List<Product> products = new ArrayList<>();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService service;

    @BeforeAll
    static void setUpBeforeClass() {
        for (int i = 0; i < 10; i++) {
            products.add(new Product(
                "TastyCandy",
                    "Tasty",
                    123,
                    ProductStatus.IN_STOCK,
                    123
            ));
        }
    }

    @Test
    void getProducts() throws Exception {
        when(service.getProducts()).thenReturn(products);
        this.mockMvc.perform(get("/api/product"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void getProductById() throws Exception {
        when(service.getProductById(1)).thenReturn(products.get(1));
        System.out.println(new ObjectMapper().writeValueAsString(service.getProductById(1)));
        this.mockMvc.perform(
                        get("/api/product/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("TastyCandy"));

    }
    @Test
    void saveProduct() throws Exception {
        when(service.saveProduct(new ProductDto())).thenReturn(products.get(1));
        this.mockMvc.perform(
                post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(products.get(1)))
        ).andExpect(status().isCreated());
    }

}
