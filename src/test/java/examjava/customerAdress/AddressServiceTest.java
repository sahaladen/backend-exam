package examjava.customerAdress;

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

import java.util.List;

@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AddressServiceTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:alpine");

    @Autowired
    CustomerAddressService customerAddressService;

    @Test
    @Order(1)
    void setup(){
        for (int i = 0; i < 10; i++) {
            customerAddressService.saveCustomerAddress(new CustomerAddress("Address " + i));
        }
    }

    @Test
    @Order(2)
    void getAddresses() {
        List<CustomerAddress> addresses = customerAddressService.getCustomerAddresses();
        assert addresses.size() == 10;
    }

    @Test
    @Order(3)
    void getAddressById() {
        var address = customerAddressService.getCustomerAddressById(1);
        assert address != null;
        assert address.getAddress().equals("Address 0");
    }

    @Test
    @Order(4)
    void saveAddress() {
        assert customerAddressService.saveCustomerAddress(new CustomerAddress("Address 110")).getAddress().equals("Address 110");
    }

    @Test
    @Order(5)
    void deleteAddressById() {
        customerAddressService.deleteCustomerAddressById(1);
        assert customerAddressService.getCustomerAddresses().size() == 10;
    }

}
