package examjava.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private int shippingCharge;
    private long customerId;
    private List<Long> productIds;
}
