package org.example.javaexam.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    private Long productId;
    private String productName;
    private String productDescription;
    private int productPrice;
    private int productQuantity;
    private Status status;

    public Product(String productName, String productDescription, int productPrice, int productQuantity, Status status) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.status = status;
    }
}
