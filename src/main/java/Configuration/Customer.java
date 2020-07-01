package Configuration;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
@Builder

public class Customer {
    private String customerName;
    private String dateOfBirth;
    private String customerAddress;
    private String customerCity;
    private String customerState;
    private String customerPin;
    private String customerMobile;
    private String customerEmail;
    private String customerPassword;
}


