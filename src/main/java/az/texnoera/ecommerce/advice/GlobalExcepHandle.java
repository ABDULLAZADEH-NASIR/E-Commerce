package az.texnoera.ecommerce.advice;

import az.texnoera.ecommerce.ExceptionsHandle.BasedExceptionHandle;
import az.texnoera.ecommerce.model.response.ErrResult;
import az.texnoera.ecommerce.model.response.ErrResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExcepHandle {
    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrResult> handleBasedExceptionHandle(BasedExceptionHandle e) {
        log.error(e.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new ErrResult(e.getExceptionStatusCode().getStatusCode(), e.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ErrResult> handleBasedExceptionHandle(RuntimeException e) {
        log.error(e.getMessage());
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(new ErrResult(2000, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)

    ResponseEntity<ErrResultData> handleBasedExceptionHandle(MethodArgumentNotValidException e) {
        List<String>errors=new ArrayList<>();
        e.getAllErrors().forEach(err->{
            log.error(err.getDefaultMessage());
            errors.add(err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ErrResultData(500,"Field is not valid",errors));
   }
}
