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


    public CustomerDto(String firstName, String lastName, String phoneNumber, String email, List<Long> addressIds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addressIds = addressIds;
    }
}
