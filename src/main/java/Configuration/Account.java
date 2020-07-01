package Configuration;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.openqa.selenium.support.ui.Select;

@Getter
@Value
@Builder
public class Account {
    private String customerId;
    private Select accountType;
    private double depositAmount;
}
