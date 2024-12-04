package examjava.customerAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAddressService {

    private final CustomerAddressRepo customerAddressRepo;

    @Autowired
    private CustomerAddressService(CustomerAddressRepo customerAddressRepo){
        this.customerAddressRepo = customerAddressRepo;
    }

    public List<CustomerAddress> getCustomerAddresses(){return customerAddressRepo.findAll();}

    public CustomerAddress getCustomerAddressById(long id) {
        return customerAddressRepo.findById(id).orElse(null);
    }

    public CustomerAddress saveCustomerAddress(CustomerAddress address) {
        return customerAddressRepo.save(address);
    }

    public void deleteCustomerAddressById(long id) {
        customerAddressRepo.deleteById(id);
    }
}
