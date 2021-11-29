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

    @Override
    public boolean equals(Object toCompare) {
        if (!(toCompare instanceof DepartmentVO))
            return false;

        DepartmentVO departmentVO = (DepartmentVO) toCompare;

        if (id == departmentVO.getId() && name == departmentVO.getName())
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return 5 + (id != null ? id.hashCode() : 0);
    }

}
