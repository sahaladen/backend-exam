package examjava.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderByID(@PathVariable long id){
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(){
        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        return new ResponseEntity<>(orderService.saveOrder(order), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable long id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>("Your shit is gone", HttpStatus.OK);
    }

}
