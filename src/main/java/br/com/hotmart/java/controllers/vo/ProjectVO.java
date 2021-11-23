package br.com.hotmart.java.controllers.vo;

import br.com.hotmart.java.entities.Project;
import com.google.j2objc.annotations.ObjectiveCName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectVO {
    private Long id;

    private String name;

    private Integer cost;

    public ProjectVO(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.cost = project.getCost();

    }

    @Override
    public boolean equals(Object toCompare) {
        if (!(toCompare instanceof ProjectVO))
            return false;

        ProjectVO projectVO = (ProjectVO) toCompare;

        if (id == projectVO.getId() && name == projectVO.getName() && cost == projectVO.getCost())
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return 5 + (id != null ? id.hashCode() : 0);
    }
}
