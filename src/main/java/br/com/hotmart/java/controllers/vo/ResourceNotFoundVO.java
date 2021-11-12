package br.com.hotmart.java.controllers.vo;

import br.com.hotmart.java.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceNotFoundVO {
    private String field;

    private String message;

    public ResourceNotFoundVO(ResourceNotFoundException exception) {
        this.field = exception.getField();
        this.message = exception.getMessage();
    }
}
