package br.com.hotmart.java.controllers.forms;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentForm {

    private String name;

    private Integer number;

    private Integer budget;
}
