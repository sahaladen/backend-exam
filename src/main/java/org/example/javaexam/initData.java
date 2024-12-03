package org.example.javaexam;

import org.example.javaexam.candyco.CandyCoService;
import org.example.javaexam.customer.CustomerService;
import org.example.javaexam.order.OrderService;
import org.example.javaexam.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
