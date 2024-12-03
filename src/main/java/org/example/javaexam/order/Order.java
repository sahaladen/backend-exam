package org.example.javaexam.order;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.javaexam.product.Product;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    private long orderId;
}
