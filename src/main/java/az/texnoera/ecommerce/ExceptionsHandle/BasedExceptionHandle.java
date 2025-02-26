package az.texnoera.ecommerce.ExceptionsHandle;

import az.texnoera.ecommerce.model.enums.ExceptionStatusCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class BasedExceptionHandle extends RuntimeException {
    private HttpStatus statusCode;
    private ExceptionStatusCode exceptionStatusCode;

    public BasedExceptionHandle(HttpStatus statusCode, ExceptionStatusCode exceptionStatusCode) {
      super(exceptionStatusCode.getMessage());
      this.statusCode = statusCode;
      this.exceptionStatusCode=exceptionStatusCode;
    }

}
