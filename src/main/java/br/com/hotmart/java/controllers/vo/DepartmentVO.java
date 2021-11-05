package br.com.hotmart.java.controllers.vo;

import br.com.hotmart.java.entities.Department;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentVO {

    private String name;

    private Integer number;

    public DepartmentVO(Department department) {
        this.name = department.getName();
        this.number = department.getNumber();
    }
}
