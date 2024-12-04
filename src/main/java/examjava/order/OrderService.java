package examjava.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> getOrder() {
        return orderRepo.findAll();
    }

    public Order getOrderById(long id){
        return orderRepo.findById(id).orElse(null);
    }

    public Order saveOrder(Order order) {
        return orderRepo.save(order);
    }

    public void deleteOrderById(long id){
        orderRepo.deleteById(id);
    }

}
