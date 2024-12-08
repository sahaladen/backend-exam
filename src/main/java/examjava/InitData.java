package examjava;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import examjava.customer.Customer;
import examjava.customer.CustomerDto;
import examjava.customer.CustomerService;
import examjava.customerAddress.CustomerAddress;
import examjava.customerAddress.CustomerAddressService;
import examjava.order.OrderDto;
import examjava.order.OrderService;
import examjava.product.Product;
import examjava.product.ProductDto;
import examjava.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class InitData  {
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

    public void createData () {
        Random random = new Random();


        //creating customer address
        for(int i = 0; i < 20; i++){
            customerAddressService.saveCustomerAddress(
                new CustomerAddress(
                        faker.address().fullAddress()
                )
            );
        }

        //creating product
        List<Product> products = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            products.add(productService.saveProduct(new ProductDto(
                    faker.food().fruit(),
                    faker.lorem().characters(),
                    random.nextInt(20,100),
                    random.nextInt(100,1000)
            )));
        }




        // Creating customers
        for (int i = 0; i < 20; i++) {
            List<Long> addresses = new ArrayList<>();
            for(int l =0; l< random.nextInt(1,2);l++){
                addresses.add(random.nextLong(1,20));
            }

            customerService.saveCustomer(new CustomerDto(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.phoneNumber().cellPhone(),
                    faker.internet().emailAddress(),
                    addresses));
        }

        //creating orders
        for (int i = 0; i < 20; i++) {
            List<Long> orderproducts = new ArrayList<>();
            for (int j = 0; j < random.nextInt(1,20); j++) {
                orderproducts.add(random.nextLong(1, 20));
            }
            long personId = random.nextLong(1,20);
            orderService.saveOrder(new OrderDto(
                    random.nextInt(100,500),
                    personId,
                    orderproducts,
                    customerService.getCustomerById(personId).getAddresses().get(0).getAddressId()
                    )
            );
        }

    }
}
