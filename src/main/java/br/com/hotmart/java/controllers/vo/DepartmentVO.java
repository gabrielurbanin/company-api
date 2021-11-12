package br.com.hotmart.java.controllers.vo;

import br.com.hotmart.java.entities.Department;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentVO {
    private Long id;

    private String name;

    public DepartmentVO(Department department) {
        this.id = department.getId();
        this.name = department.getName();
    }
}
