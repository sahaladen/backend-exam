package examjava;

import com.github.javafaker.Faker;
import examjava.customer.Customer;
import examjava.customer.CustomerDto;
import examjava.customer.CustomerService;
import examjava.customerAddress.CustomerAddress;
import examjava.customerAddress.CustomerAddressService;
import examjava.order.OrderService;
import examjava.product.Product;
import examjava.product.ProductDto;
import examjava.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class InitData implements CommandLineRunner {
    private final CustomerService customerService;
    private final CustomerAddressService customerAddressService;
    private final OrderService orderService;
    private final ProductService productService;
    private final Faker faker = Faker.instance();

    @Autowired
    public InitData(CustomerService customerService, CustomerAddressService customerAddressService, OrderService orderService, ProductService productService) {
        this.customerService = customerService;
        this.customerAddressService = customerAddressService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();


        //creating customer address
        List<CustomerAddress> customerAddresses = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            customerAddresses.add(
                    customerAddressService.saveCustomerAddress(
                            new CustomerAddress(
                                    faker.address().fullAddress(),customerService.getCustomers()
                            )
                    )
            );
        }

        //creating product
        List<Product> products = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            products.add(productService.saveProduct(new ProductDto()));
        }




        // Creating customers
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setFirstName(faker.name().firstName());
            customerDto.setLastName(faker.name().lastName());
            customerDto.setPhoneNumber(faker.phoneNumber().phoneNumber());
            customerDto.setEmail(faker.internet().emailAddress());
            customerDto.setAddressIds(new ArrayList<>());

            customers.add(customerService.saveCustomer(customerDto));
        }
    }
}
