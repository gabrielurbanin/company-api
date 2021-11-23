package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.ProjectForm;
import br.com.hotmart.java.controllers.vo.ProjectVO;
import br.com.hotmart.java.entities.Project;
import br.com.hotmart.java.exception.ResourceNotFoundException;
import br.com.hotmart.java.repositories.ProjectRepository;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.hotmart.java.builders.ProjectBuilder.buildProject;
import static br.com.hotmart.java.builders.ProjectFormBuilder.buildProjectForm;
import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;

    @Test
    void givenProjectId_whenFindById_thenProject() {
        Project project = buildProject("project");

        when(projectRepository.findById(eq(project.getId())))
                .thenReturn(Optional.of(project));

        Project response = projectService.findById(project.getId());

        assertThat(response)
                .isNotNull()
                .matches(projectResp -> project.getId().equals(projectResp.getId()))
                .matches(projectResp -> project.getName().equals(projectResp.getName()))
                .matches(projectResp -> project.getCost().equals(projectResp.getCost()))
                .matches(projectResp -> project.getStartDate().equals(projectResp.getStartDate()))
                .matches(projectResp -> project.getDueDate().equals(projectResp.getDueDate()))
                .matches(projectResp -> project.getDepartment().equals(projectResp.getDepartment()))
                .matches(projectResp -> project.getEmployees().equals(projectResp.getEmployees())); // Funciona porem tá errado.
    }

    @Test
    void givenProjectId_whenFindById_thenResourceNotFoundException() {
        when(projectRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> projectService.findById(1L));
    }

    @Test
    void whenGetAll_thenListProjectVO() {
        List<Project> projects = ImmutableList.of(buildProject("project1"), buildProject("project2"));

        when(projectRepository.findAll())
                .thenReturn(projects);

        List<ProjectVO> projectVOS = projects.stream()
                .map(ProjectVO::new).collect(Collectors.toList());

        List<ProjectVO> response = projectService.getAll();

        assertArrayEquals(projectVOS.toArray(), response.toArray());
    }

    @Test
    void whenGetAll_thenEmptyList() {
        when(projectRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<ProjectVO> response = projectService.getAll();

        assertArrayEquals(Collections.emptyList().toArray(), response.toArray());
    }

}
