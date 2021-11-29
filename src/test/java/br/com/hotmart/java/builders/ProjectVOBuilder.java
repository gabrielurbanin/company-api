package br.com.hotmart.java.builders;

import br.com.hotmart.java.controllers.vo.ProjectVO;

import java.time.LocalDate;

public class ProjectVOBuilder {
    public static ProjectVO buildProjectVO(String name) {
        ProjectVO projectVO = new ProjectVO();
        projectVO.setId(1L);
        projectVO.setName(name);
        projectVO.setCost(1000);

        return projectVO;
    }
}
