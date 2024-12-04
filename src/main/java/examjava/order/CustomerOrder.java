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
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "customer_order_gen")
    @SequenceGenerator(name = "customer_order_gen", sequenceName = "customer_order_sql", allocationSize = 1)
    private long orderId;
    private int shippingCharge;
    private int totalPrice;
    private OrderStatus shippingStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("orders")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    @JsonIgnoreProperties("orders")
    private List<Product> products;


    public CustomerOrder(int shippingCharge, int totalPrice, OrderStatus status, Customer customer, List<Product> products) {
        this.shippingCharge = shippingCharge;
        this.totalPrice = totalPrice;
        this.shippingStatus = status;
        this.customer = customer;
        this.products = products;
    }
}
