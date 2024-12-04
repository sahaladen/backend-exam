package examjava.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    private String productName;
    private String description;
    private int price;
    private int stock;
}
