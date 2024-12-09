package examjava.customerAdress;

import examjava.customerAddress.CustomerAddress;
import examjava.customerAddress.CustomerAddressRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AddressRepoTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:alpine");

    @Autowired
    CustomerAddressRepo customerAddressRepo;
    @Test
    void connectionEstablished(){
        assert postgres.isCreated();
        assert postgres.isRunning();
    }

    @Test
    void shouldInsertAddress(){
        var address = customerAddressRepo.save(new CustomerAddress("Address 100"));
        assert customerAddressRepo.findById(address.getAddressId()).isPresent();
    }

    @Test
    void shouldUpdateAddress(){
        var address = customerAddressRepo.save(new CustomerAddress("Address 100"));
        address.setAddress("Address 224");
        customerAddressRepo.save(address);
        assert customerAddressRepo.findById(address.getAddressId()).get().getAddress().equals("Address 224");
    }

    @Test
    void shouldDeleteAddress(){
        var address = customerAddressRepo.save(new CustomerAddress("Address 100"));
        customerAddressRepo.delete(address);
        assert customerAddressRepo.findById(address.getAddressId()).isEmpty();
    }




}
