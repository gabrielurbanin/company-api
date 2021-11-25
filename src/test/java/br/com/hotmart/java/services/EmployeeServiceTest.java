package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.EmployeeForm;
import br.com.hotmart.java.controllers.vo.EmployeeVO;
import br.com.hotmart.java.controllers.vo.ProjectVO;
import br.com.hotmart.java.entities.Employee;
import br.com.hotmart.java.entities.Project;
import br.com.hotmart.java.repositories.EmployeeRepository;
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

import static br.com.hotmart.java.builders.EmployeeBuilder.buildBasicEmployee;
import static br.com.hotmart.java.builders.EmployeeBuilder.buildEmployee;
import static br.com.hotmart.java.builders.EmployeeFormBuilder.buildEmployeeForm;
import static br.com.hotmart.java.builders.ProjectBuilder.buildProject;
import static br.com.hotmart.java.builders.ProjectVOBuilder.buildProjectVO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {

    @Spy
    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ProjectService projectService;

    @Test
    void whenGetAll_thenListEmployeeVO() {
        Project project = buildProject("project1");
        List<Employee> employees = ImmutableList.of(buildEmployee("employee1", project), buildEmployee("employee2", project));
        List<EmployeeVO> employeeVOS = employees.stream()
                .map(EmployeeVO::new).collect(Collectors.toList());

        when(employeeRepository.findAll())
                .thenReturn(employees);

        List<EmployeeVO> response = employeeService.getAll();

        assertArrayEquals(employeeVOS.toArray(), response.toArray());
    }

    @Test
    void whenGetAll_thenEmptyList() {
        when(employeeRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<EmployeeVO> response = employeeService.getAll();

        assertArrayEquals(Collections.emptyList().toArray(), response.toArray());
    }

    @Test
    void givenEmployeeForm_whenSave_thenCallSaveFromEmployeeRepository() {
        Employee employee = buildBasicEmployee("employee");
        EmployeeForm employeeForm = buildEmployeeForm("employee");

        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(employee);

        employeeService.save(employeeForm);

        verify(employeeRepository, times(1)).save(any(Employee.class));
    }


    @Test
    void givenEmployeeId_whenFindById_thenEmployeeVO() {
        Employee employee = buildBasicEmployee("employee");

        when(employeeRepository.findById(eq(1L)))
                .thenReturn(Optional.of(employee));

        ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);

        Employee response = employeeService.findById(1L);

        verify(employeeRepository, times(1)).findById(1L);

        assertThat(response)
                .isNotNull()
                .matches(employeeResp -> employee.getId().equals(employeeResp.getId()))
                .matches(employeeResp -> employee.getName().equals(employeeResp.getName()))
                .matches(employeeResp -> employee.getSalaryPerHour().equals(employeeResp.getSalaryPerHour()))
                .matches(employeeResp -> employee.getCpf().equals(employeeResp.getCpf()));
    }

    @Test
    void givenEmployeeId_whenGetById_thenCallFindByIdFromEmployeeService() {
        Employee employee = buildBasicEmployee("employee");

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        employeeService.getById(1L);

        verify(employeeService, times(1)).findById(1L);
    }

    @Test
    void givenName_whenGetAllByName_thenCallFindAllByNameFromEmployeeRepository() {
        List<Employee> employees = ImmutableList
                .of(buildBasicEmployee("employee"), buildBasicEmployee("employee"));

        when(employeeRepository.findAllByName("employee"))
                .thenReturn(employees);

        employeeService.getAllByName("employee");

        verify(employeeRepository, times(1)).findAllByName("employee");
    }

    @Test
    void givenSupervisorId_whenGetAllSubordinates_thenCallFindAllBySupervisorIdFromEmployeeRepository() {
        List<Employee> employees = ImmutableList
                .of(buildBasicEmployee("employee"), buildBasicEmployee("employee"));

        when(employeeRepository.findAllBySupervisorId(2L))
                .thenReturn(employees);

        employeeService.getAllSubordinates(2L);

        verify(employeeRepository, times(1)).findAllBySupervisorId(2L);
    }

    @Test
    void givenEmployeeId_whenGetAllProjects_thenCallGetAllProjectsFromEmployeeId_FromProjectService() {
        List<ProjectVO> projectVOS = ImmutableList
                .of(buildProjectVO("project1"), buildProjectVO("project2"));

        when(projectService.getAllProjectsFromEmployee(1L))
                .thenReturn(projectVOS);

        employeeService.getAllProjects(1L);

        verify(projectService, times(1)).getAllProjectsFromEmployee(1L);
    }

    @Test
    void givenEmployeeIdAndEmployeeForm_whenUpdate_thenEmployeeVO() {
        Employee employee = buildBasicEmployee("employee");
        EmployeeForm employeeForm = buildEmployeeForm("employeeUpdated");

        when(employeeRepository.findById(anyLong()))
                .thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(employee);

        ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);

        employeeService.update(1L, employeeForm);

        verify(employeeRepository, times(1)).save(employeeCaptor.capture());

        assertThat(employeeCaptor.getValue())
                .isNotNull()
                .matches(employeeSaved -> employeeForm.getName().equals(employeeSaved.getName()));
    }

    @Test
    void givenEmployeeIdAndSupervisorId_whenUpdateSupervisor_thenCheckForUpdatedSupervisor() {
        Employee employee = buildBasicEmployee("employee");
        Employee supervisor = buildBasicEmployee("supervisorUpdated");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.findById(2L)).thenReturn(Optional.of(supervisor));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);

        employeeService.updateSupervisor(1L, 2L);

        verify(employeeRepository, times(1)).save(employeeCaptor.capture());

        assertThat(employeeCaptor.getValue())
                .isNotNull()
                .matches(employeeSaved -> supervisor.getName().equals(employeeSaved.getSupervisor().getName()));
    }

    @Test
    void givenEmployeeId_whenDelete_thenCallDeleteFromEmployeeRepository() {
        Employee employee = buildBasicEmployee("employee");
        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        employeeService.delete(1L);

        verify(employeeRepository, times(1)).delete(any(Employee.class));
    }

    @Test
    void givenDepartmentId_whenGetAllEmployeesFromDepartment_thenListEmployeesVO() {
        List<Employee> employees = ImmutableList
                .of(buildBasicEmployee("employee1"), buildBasicEmployee("employee2"));

        when(employeeRepository.findAllByProjectsDepartmentId(1L))
                .thenReturn(employees);

        employeeService.getAllEmployeesFromDepartment(1L);

        verify(employeeRepository, times(1)).findAllByProjectsDepartmentId(1L);
    }

}
