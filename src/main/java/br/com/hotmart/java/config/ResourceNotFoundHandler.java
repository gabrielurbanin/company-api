package br.com.hotmart.java.config;

import br.com.hotmart.java.controllers.vo.ResourceNotFoundVO;
import br.com.hotmart.java.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceNotFoundHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResourceNotFoundVO handle(ResourceNotFoundException exception) {
        return new ResourceNotFoundVO(exception);
    }
}
