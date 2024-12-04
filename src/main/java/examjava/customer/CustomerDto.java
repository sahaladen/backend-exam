package examjava.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private List<Long> addressIds;

}
