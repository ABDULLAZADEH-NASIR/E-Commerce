package az.texnoera.ecommerce.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@Getter
@ToString
public enum ExceptionStatusCode {
    USER_NOT_FOUND(1000, "User Not Found"),
    ORDER_NOT_FOUND(1001, "Order Not Found"),
    USER_EMAIL_NOT_FOUND(1003, "User Email Not Found"),
    PRODUCT_NOT_FOUND(1004, "Product Not Found"),
    PRODUCT_NOT_FOUND_IN_ORDER(1005, "Product Not Found in Order");

    private final int StatusCode;
    private final String Message;
}
