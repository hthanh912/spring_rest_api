package exception;

import com.example.springrest.dto.ErrorObject;
import com.example.springrest.dto.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class CustomExceptionHandler {
  @ExceptionHandler(value = ResourceNotFoundException.class)
  public ResponseEntity<ErrorObject> handlerResourceNotFoundException(ResourceNotFoundException ex){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorObject(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
  }
}
