package Configuration;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Deposit {
    private int accountNo;
    private int depAmount;
    private String depDescription;
}
