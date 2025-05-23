package examjava.order;

import examjava.customer.Customer;
import examjava.customer.CustomerService;
import examjava.customerAddress.CustomerAddress;
import examjava.customerAddress.CustomerAddressService;
import examjava.error.CustomerNotFoundException;
import examjava.error.OrderNotFoundException;
import examjava.product.Product;
import examjava.product.ProductService;
import examjava.product.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final CustomerService customerService;
    private final CustomerAddressService customerAddressService;
    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepo orderRepo, CustomerService customerService, CustomerAddressService customerAddressService, ProductService productService) {

        this.orderRepo = orderRepo;
        this.customerService = customerService;
        this.customerAddressService = customerAddressService;
        this.productService = productService;

    }

    public List<CustomerOrder> getOrders() {
        return orderRepo.findAll();
    }

    public CustomerOrder getOrderById(long id){
        CustomerOrder customerOrder;
        try {
            customerOrder= orderRepo.findById(id).orElseThrow();
        }catch (NoSuchElementException e){
            throw new OrderNotFoundException("Order"  +id + "not found");
        }
        return customerOrder;
    }

    public CustomerOrder saveOrder(OrderDto orderDto)
    {
        Customer customer = customerService.getCustomerById(orderDto.getCustomerId());
        CustomerAddress address = customerAddressService.getCustomerAddressById(orderDto.getAddressId());
        List<Product> products = new ArrayList<>();
        for (Long productId: orderDto.getProductIds()) {

            if (productService.getProductById(productId).getStatus().equals(ProductStatus.IN_STOCK)){
                Product product = productService.getProductById(productId);
                product.setStock(product.getStock()-1);
                if(product.getStock()==0){
                    product.setStatus(ProductStatus.OUT_STOCK);
                }
                products.add(product);
            }

        }
        int totalPrice = 0;
        for (Product product:products){
            totalPrice+= product.getPrice();
        }
        CustomerOrder customerOrder = new CustomerOrder(
                orderDto.getShippingCharge(),
                totalPrice,
                OrderStatus.NOT_SHIPPED,
                customer,
                products,
                address
        );
        return orderRepo.save(customerOrder);
    }

    public void deleteOrderById(long id){
        orderRepo.deleteById(id);
    }

    public CustomerOrder shipOrder(Long id){
        CustomerOrder customerOrder;
        try {
            customerOrder = orderRepo.findById(id).orElse(null);
            customerOrder.setShippingStatus(OrderStatus.SHIPPED);
            orderRepo.save(customerOrder);
        } catch (NoSuchElementException e) {
            throw new OrderNotFoundException("Order" + id + "not found");

        }
        return customerOrder;
    }

}
