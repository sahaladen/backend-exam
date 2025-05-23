package examjava.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private String productName;
    private String description;
    private int price;
    private int stock;

    public ProductDto(String productName, String description, int price, int stock) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
