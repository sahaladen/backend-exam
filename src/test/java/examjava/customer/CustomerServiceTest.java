package examjava.customer;

import examjava.customer.Customer;
import examjava.customer.CustomerService;
import examjava.customerAddress.CustomerAddress;
import examjava.customerAddress.CustomerAddressService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerServiceTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:alpine");

    @Autowired
    CustomerService customerService;
    CustomerAddressService customerAddressService;

    @Test
    @Order(1)
    void setup(){
        List<Long> address = new ArrayList<>();
        long fakeid =1;
        address.add(fakeid);
        for (int i = 0; i < 10; i++) {
            customerService.saveCustomer(new CustomerDto("FirstName",
                    "LastName",
                    "phoneNumber",
                    "email",
                    address));
        }
    }

    @Test
    @Order(2)
    void getCustomers() {
        List<Customer> customers = customerService.getCustomers();
        assert customers.size() == 10;
    }

    @Test
    @Order(3)
    void getCustomerById() {
        var customer = customerService.getCustomerById(1);
        assert customer != null;
        assert customer.getFirstName().equals("FirstName");
    }



    @Test
    @Order(4)
    void saveCustomer() {
        List<Long> address = new ArrayList<>();
        long fakeid =1;
        address.add(fakeid);
        assert customerService.saveCustomer(new CustomerDto("thisName",
                "LastName",
                "phoneNumber",
                "email",
                address)).getFirstName().equals("thisName");
    }

    @Test
    @Order(5)
    void deleteCustomerById() {
        customerService.deleteCustomerById(1);
        assert customerService.getCustomers().size() == 10;
    }

}
