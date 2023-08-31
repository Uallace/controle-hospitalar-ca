package br.com.uallace.controle.exception.handler;

import br.com.uallace.controle.exception.DataIntegrationViolationException;
import br.com.uallace.controle.exception.ObjectNotFound;
import br.com.uallace.controle.exception.StandarError;
import br.com.uallace.controle.exception.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFound.class)
    public ResponseEntity<StandarError> objectNotFoundException(Exception ex, HttpServletRequest request){

        StandarError error = new StandarError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Object Not Found", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(DataIntegrationViolationException.class)
    private ResponseEntity<StandarError> dataIntegrationViolationException(Exception ex, HttpServletRequest request){
        StandarError error = new StandarError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Data Integration Violation", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ValidationError> validationErros(MethodArgumentNotValidException ex, HttpServletRequest request){
        ValidationError erros = new ValidationError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Data Integration Violation","Erro na validação dos campos",request.getRequestURI());

        for(FieldError erro : ex.getBindingResult().getFieldErrors()){
            erros.addErros(erro.getField(), erro.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

}
