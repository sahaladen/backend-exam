package org.example.javaexam.candyco;

import org.example.javaexam.customer.CustomerService;
import org.example.javaexam.order.OrderService;
import org.example.javaexam.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandyCoService {
    private CustomerService customerService;
    private OrderService orderService;
    private ProductService productService;
    private CandCoRepo candCoRepo;

    @Autowired
    public CandyCoService(CustomerService customerService, OrderService orderService, ProductService productService, CandCoRepo candCoRepo) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.productService = productService;
        this.candCoRepo = candCoRepo;
    }
}
