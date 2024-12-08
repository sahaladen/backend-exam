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
    private long addressId;

    public OrderDto(int shippingCharge, long customerId, List<Long> productIds, long addressId) {
        this.shippingCharge = shippingCharge;
        this.customerId = customerId;
        this.productIds = productIds;
        this.addressId = addressId;
    }
}
