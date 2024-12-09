package examjava.customer;

import examjava.customer.Customer;
import examjava.customer.CustomerDto;
import examjava.customer.CustomerRepo;
import examjava.customerAddress.CustomerAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepoTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:alpine");

    @Autowired
    CustomerRepo customerRepo;
    @Test
    void connectionEstablished(){
        assert postgres.isCreated();
        assert postgres.isRunning();
    }

    @Test
    void shouldInsertCustomer(){
        List<CustomerAddress> address = new ArrayList<>();
        address.add(new CustomerAddress("Address"));
        var customer = new Customer("FirstName",
                "LastName",
                "phoneNumber",
                "email",
                address);
        customerRepo.save(customer);
        assert customerRepo.findById(customer.getCustomerId()).isPresent();
    }



    @Test
    void shouldDeleteCustomer(){
        List<CustomerAddress> address = new ArrayList<>();
        address.add(new CustomerAddress("Address"));
        var customer = customerRepo.save(new Customer("FirstName",
                "LastName",
                "phoneNumber",
                "email",
                address));
        customerRepo.delete(customer);
        assert customerRepo.findById(customer.getCustomerId()).isEmpty();
    }




}
