package examjava.customerAddress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import examjava.customer.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "customer_address_gen")
    @SequenceGenerator(name = "customer_address_gen", sequenceName = "customer_address_sql", allocationSize = 1)
    private long addressId;
    private String address;

    @ManyToMany(mappedBy = "addresses")
    @JsonIgnoreProperties("addresses")
    private List<Customer> customers;

    public CustomerAddress(String address, List<Customer> customers) {
        this.address = address;
    }
}
