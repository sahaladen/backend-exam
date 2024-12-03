package examjava;

import examjava.candyco.CandyCoService;
import examjava.customer.CustomerService;
import examjava.order.OrderService;
import examjava.product.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class initData implements CommandLineRunner {
    private final CandyCoService candyCoService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderService orderService;

    public initData(CandyCoService candyCoService, CustomerService customerService, ProductService productService, OrderService orderService) {
        this.candyCoService = candyCoService;
        this.customerService = customerService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) throws Exception {}
}
