package examjava.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import examjava.customer.Customer;
import examjava.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "order_gen")
    @SequenceGenerator(name = "order_gen", sequenceName = "order_sql", allocationSize = 1)
    private long orderId;
    private int shippingCharge;
    private int totalPrice;
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("order")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    @JsonIgnoreProperties("order")
    private List<Product> products;


    public Order(int shippingCharge, int totalPrice, OrderStatus status, Customer customer, List<Product> products) {
        this.shippingCharge = shippingCharge;
        this.totalPrice = totalPrice;
        this.status = status;
        this.customer = customer;
        this.products = products;
    }
}
