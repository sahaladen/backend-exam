package examjava.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "product_gen")
    @SequenceGenerator(name = "product_gen", sequenceName = "product_sql", allocationSize = 1)
    private Long productId;
    private String productName;
    private String description;
    private int price;
    private ProductStatus status;
    private int stock;


    public Product(String productName, String description, int price, ProductStatus status,int stock) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.status = status;
        this.stock = stock;

    }
}
