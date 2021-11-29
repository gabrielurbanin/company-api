package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.ProjectForm;
import br.com.hotmart.java.controllers.vo.ProjectVO;
import br.com.hotmart.java.entities.Department;
import br.com.hotmart.java.entities.Employee;
import br.com.hotmart.java.entities.Project;
import br.com.hotmart.java.exception.ResourceNotFoundException;
import br.com.hotmart.java.repositories.ProjectRepository;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.hotmart.java.builders.DepartmentBuilder.buildDepartment;
import static br.com.hotmart.java.builders.EmployeeBuilder.buildEmployee;
import static br.com.hotmart.java.builders.ProjectBuilder.buildProject;
import static br.com.hotmart.java.builders.ProjectFormBuilder.buildProjectForm;
import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ProjectServiceTest {

    @Spy
    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private DepartmentService departmentService;

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

    @Test
    void givenProjectId_whenGetById_thenProjectVO() {
        Project project = buildProject("project");

        when(projectRepository.findById(anyLong()))
                .thenReturn(Optional.of(project));

        projectService.getById(1L);

        verify(projectService, times(1)).findById(anyLong());
    }

    @Test
    void givenProjectForm_whenSave_thenProjectVO() {
        Project project = buildProject("project");
        Department department = buildDepartment("department");
        ProjectForm projectForm = buildProjectForm("project");

        when(projectRepository.save(any(Project.class)))
                .thenReturn(project);
        when(departmentService.findById(anyLong()))
                .thenReturn(department);

        projectService.save(projectForm);

        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    void givenProjectIdAndProjectForm_whenUpdate_thenProjectVO() {
        Project project = buildProject("projectName");
        ProjectForm projectForm = buildProjectForm("newProjectName");

        when(projectRepository.findById(anyLong()))
                .thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class)))
                .thenReturn(project);

        ArgumentCaptor<Project> projectCaptor = ArgumentCaptor.forClass(Project.class);

        projectService.update(1L, projectForm);

        verify(projectRepository, times(1)).save(projectCaptor.capture());

        assertThat(projectCaptor.getValue())
                .isNotNull()
                .matches(projectSaved -> projectForm.getName().equals(projectSaved.getName()));
    }

    @Test
    void givenProjectId_whenDelete_thenCallDeleteFromRepository() {
        Project project = buildProject("project");
        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));

        projectService.delete(1L);

        verify(projectRepository, times(1)).delete(any(Project.class));
    }

    @Test
    void givenProjectIdAndEmployeeId_whenAddEmployee_thenProjectVO() {
        Project project = buildProject("project1");
        Employee employee = buildEmployee("employee1", project);

        when(projectRepository.findById(eq(1L)))
                .thenReturn(Optional.of(project));
        when(employeeService.findById(eq(1L)))
                .thenReturn(employee);
        when(projectRepository.save(any(Project.class)))
                .thenReturn(project);

        ArgumentCaptor<Project> projectCaptor = ArgumentCaptor.forClass(Project.class);

        projectService.addEmployee(1L, 1L);

        verify(projectRepository, times(1)).save(projectCaptor.capture());

        assertThat(projectCaptor.getValue())
                .isNotNull()
                .matches(projectSaved -> Integer.valueOf(1080).equals(projectSaved.getCost()));
    }

    @Test
    void givenEmployeeId_whenGetAllProjectsFromEmployee_thenListProjectVO() {
        List<Project> projects = ImmutableList.of(buildProject("project1"), buildProject("project2"));

        when(projectRepository.findAllByEmployeesId(anyLong()))
                .thenReturn(projects);

        List<ProjectVO> projectVOS = projects.stream()
                .map(ProjectVO::new).collect(Collectors.toList());

        List<ProjectVO> response = projectService.getAllProjectsFromEmployee(1L);

        assertArrayEquals(projectVOS.toArray(), response.toArray());
    }

    @Test
    void givenEmployeeId_whenGetAllProjectsFromEmployee_thenEmptyList() {
        when(projectRepository.findAllByEmployeesId(anyLong()))
                .thenReturn(Collections.emptyList());

        List<ProjectVO> response = projectService.getAllProjectsFromEmployee(1L);

        assertArrayEquals(Collections.emptyList().toArray(), response.toArray());
    }

    @Test
    void givenDepartmentId_whenGetAllByDepartment_thenListProject() {
        List<Project> projects = ImmutableList.of(buildProject("project1"), buildProject("project2"));

        when(projectRepository.findAllByDepartmentId(anyLong()))
                .thenReturn(projects);

        List<Project> response = projectService.getAllByDepartment(1L);

        assertArrayEquals(projects.toArray(), response.toArray());
    }

    @Test
    void givenDepartmentId_whenGetAllByDepartment_thenEmptyList() {
        when(projectRepository.findAllByEmployeesId(anyLong()))
                .thenReturn(Collections.emptyList());

        List<Project> response = projectService.getAllByDepartment(1L);

        assertArrayEquals(Collections.emptyList().toArray(), response.toArray());
    }

}
