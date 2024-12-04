package examjava.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import examjava.customerAddress.CustomerAddress;
import examjava.order.CustomerOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "customer_gen")
    @SequenceGenerator(name = "customer_gen", sequenceName = "customer_sql", allocationSize = 1)
    private long customerId;
    private String firstName;
    private String lastName;
    //string in case customer wants to enter area code with characters
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties("customer")
    private List<CustomerOrder> orders;

    @ManyToMany
    @JoinTable(
            name = "customer_address_customer",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    @JsonIgnoreProperties("customers")
    private List<CustomerAddress> addresses;


    public Customer(String firstName, String lastName, String phoneNumber, String email, List<CustomerAddress> addresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addresses = addresses;
    }
}
