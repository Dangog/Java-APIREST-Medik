package med.voll.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404Exception(){
        return ResponseEntity.notFound().build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400Exception(MethodArgumentNotValidException exception){
        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ExceptionData::new).toList());
    }

    public record ExceptionData(String field, String message){

        public ExceptionData(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }

}
