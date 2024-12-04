package examjava.customer;

import examjava.customerAddress.CustomerAddress;
import examjava.customerAddress.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerAddressService customerAddressService;


    @Autowired
    public CustomerService(CustomerRepo customerRepo, CustomerAddressService customerAddressService) {
        this.customerRepo = customerRepo;
        this.customerAddressService = customerAddressService;
    }

    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }
    public Customer getCustomerById(long id) {
        return customerRepo.findById(id).orElse(null);
    }

    public Customer saveCustomer(CustomerDto customerDto)
    {
        List<CustomerAddress> customerAddresses = new ArrayList<>();
        for (Long addressId: customerDto.getAddressIds()) {
            customerAddresses.add(customerAddressService.getCustomerAddressById(addressId));
        }

        Customer customer = customerRepo.save(new Customer(
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getPhoneNumber(),
                customerDto.getEmail(),
                customerAddresses

        ));

        return customerRepo.save(customer);
    }

    public void deleteCustomerById(long id) {
        customerRepo.deleteById(id);
    }


}
