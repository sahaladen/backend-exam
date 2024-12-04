package examjava.order;

import examjava.customer.Customer;
import examjava.customer.CustomerService;
import examjava.product.Product;
import examjava.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepo orderRepo, CustomerService customerService, ProductService productService) {

        this.orderRepo = orderRepo;
        this.customerService = customerService;
        this.productService = productService;

    }

    public List<Order> getOrders() {
        return orderRepo.findAll();
    }

    public Order getOrderById(long id){
        return orderRepo.findById(id).orElse(null);
    }

    public Order saveOrder(OrderDto orderDto)
    {
        Customer customer = customerService.getCustomerById(orderDto.getCustomerId());
        List<Product> products = new ArrayList<>();
        for (Long productId: orderDto.getProductIds()) {
            products.add(productService.getProductById(productId));
        }
        int totalPrice = 0;
        for (Product product:products){
            totalPrice+= product.getPrice();
        }
        Order order = new Order(
                orderDto.getShippingCharge(),
                totalPrice,
                OrderStatus.NOT_SHIPPED,
                customer,
                products
        );
        return orderRepo.save(order);
    }

    public void deleteOrderById(long id){
        orderRepo.deleteById(id);
    }

}
