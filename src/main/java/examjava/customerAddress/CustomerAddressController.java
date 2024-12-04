package examjava.customerAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customerAddress")
public class CustomerAddressController {

    private final CustomerAddressService customerAddressService;

    @Autowired
    public CustomerAddressController(CustomerAddressService customerAddressService){
        this.customerAddressService = customerAddressService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerAddress> getCustomerAddressByID(@PathVariable long id){
        return new ResponseEntity<>(customerAddressService.getCustomerAddressById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerAddress>> getCustomerAddresses(){
        return new ResponseEntity<>(customerAddressService.getCustomerAddresses(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerAddress> createCustomerAddress(@RequestBody CustomerAddress customerAddress){
        return new ResponseEntity<>(customerAddressService.saveCustomerAddress(customerAddress), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerAddressById(@PathVariable long id) {
        customerAddressService.deleteCustomerAddressById(id);
        return new ResponseEntity<>("Your shit is gone", HttpStatus.OK);
    }

}
