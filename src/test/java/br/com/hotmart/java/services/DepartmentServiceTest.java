package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.BudgetForm;
import br.com.hotmart.java.controllers.forms.DepartmentForm;
import br.com.hotmart.java.controllers.vo.DepartmentVO;
import br.com.hotmart.java.controllers.vo.EmployeeVO;
import br.com.hotmart.java.entities.Department;
import br.com.hotmart.java.entities.Project;
import br.com.hotmart.java.exception.ResourceNotFoundException;
import br.com.hotmart.java.repositories.DepartmentRepository;
import com.google.common.collect.ImmutableList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.hotmart.java.builders.DepartmentBuilder.buildDepartmentWithBudget;
import static br.com.hotmart.java.builders.DepartmentBuilder.buildDepartment;
import static br.com.hotmart.java.builders.DepartmentFormBuilder.buildDepartmentForm;
import static br.com.hotmart.java.builders.EmployeeVOBuilder.buildEmployeeVO;
import static br.com.hotmart.java.builders.ProjectBuilder.buildProjectWithCost;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class DepartmentServiceTest {

    @Spy
    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private ProjectService projectService;

    @Mock
    private EmployeeService employeeService;

    @Test
    void whenGetAll_thenListProjectVO() {
        List<Department> departments = ImmutableList
                .of(buildDepartment("department1"), buildDepartment("department2"));

        Pageable pageable = PageRequest.of(0,2);
        Page<Department> departmentsPage = new PageImpl<Department>(departments, pageable, departments.size());

        when(departmentRepository.findAll(pageable))
                .thenReturn(departmentsPage);

        Page<DepartmentVO> departmentVOS = departmentsPage.map(DepartmentVO::new);

        Page<DepartmentVO> response = departmentService.getAll(pageable);

        assertArrayEquals(departmentVOS.stream().toArray(), response.stream().toArray());
    }

    @Test
    void whenGetAll_thenEmptyList() {
        Pageable pageable = PageRequest.of(0,2);

        when(departmentRepository.findAll(pageable))
                .thenReturn(Page.empty());

        Page<DepartmentVO> response = departmentService.getAll(pageable);

        assertArrayEquals(Page.empty().stream().toArray(), response.stream().toArray());
    }

    @Test
    void givenDepartmentForm_whenSave_thenCallSaveFromDepartmentRepository() {
        Department department = buildDepartment("department");
        DepartmentForm departmentForm = buildDepartmentForm("department");

        when(departmentRepository.save(any(Department.class)))
                .thenReturn(department);

        departmentService.save(departmentForm);

        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    void givenDepartmentId_whenFindById_thenDepartment() {
        Department department = buildDepartment("department1");
        when(departmentRepository.findById(anyLong()))
                .thenReturn(Optional.of(department));

        Department response = departmentService.findById(1L);

        assertThat(response)
                .isNotNull()
                .matches(departmentResp -> department.getId().equals(departmentResp.getId()))
                .matches(departmentResp -> department.getName().equals(departmentResp.getName()))
                .matches(departmentResp -> department.getBudget().equals(departmentResp.getBudget()))
                .matches(departmentResp -> department.getNumber().equals(departmentResp.getNumber()));
    }

    @Test
    void givenDepartmentId_whenFindById_thenResourceNotFoundException() {
        when(departmentRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> departmentService.findById(1L));
    }

    @Test
    void givenDepartmentId_whenGetById_thenCallFindByIdFromDepartmentService() {
        Department department = buildDepartment("department");
        when(departmentRepository.findById(1L))
                .thenReturn(Optional.of(department));

        departmentService.getById(1L);

        verify(departmentService, times(1)).findById(1L);
    }

    @Test
    void givenDepartmentId_whenGetAllEmployees_thenCallGetAllEmployeesFromDepartment_FromEmployeeService() {
        List<EmployeeVO> employeeVOS = ImmutableList
                .of(buildEmployeeVO("employee1"), buildEmployeeVO("employee2"));

        when(employeeService.getAllEmployeesFromDepartment(1L))
                .thenReturn(employeeVOS);

        departmentService.getAllEmployees(1L);

        verify(employeeService, times(1)).getAllEmployeesFromDepartment(1L);
    }

    @Test
    void givenDepartmentIdAndDepartmentForm_whenUpdate_thenDepartmentVO() {
        Department department = buildDepartment("department");
        DepartmentForm departmentForm = buildDepartmentForm("departmentUpdated");

        when(departmentRepository.findById(1L))
                .thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class)))
                .thenReturn(department);

        ArgumentCaptor<Department> departmentCaptor = ArgumentCaptor.forClass(Department.class);

        departmentService.update(1L, departmentForm);

        verify(departmentRepository, times(1)).save(departmentCaptor.capture());

        Assertions.assertThat(departmentCaptor.getValue())
                .isNotNull()
                .matches(addressSaved -> departmentForm.getName().equals(addressSaved.getName()));
    }

    @Test
    void givenDepartmentIdAndBudgetForm_whenAddBudget_thenCheckForCorrectBudget() {
        Department department = buildDepartmentWithBudget("department", 1000);
        BudgetForm budgetForm = new BudgetForm(500);

        when(departmentRepository.findById(1L))
                .thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class)))
                .thenReturn(department);

        ArgumentCaptor<Department> departmentCaptor = ArgumentCaptor.forClass(Department.class);

        departmentService.addBudget(1L, budgetForm);

        verify(departmentRepository, times(1)).save(departmentCaptor.capture());

        Integer updatedBudget = 500;

        assertThat(departmentCaptor.getValue())
                .isNotNull()
                .matches(departmentSaved -> updatedBudget.equals(departmentSaved.getBudget()));
    }

    @Test
    void givenDepartmentId_whenDelete_thenCallDeleteFromDepartmentRepository() {
        Department department = buildDepartment("street");

        when(departmentRepository.findById(1L))
                .thenReturn(Optional.of(department));

        departmentService.delete(1L);

        verify(departmentRepository, times(1)).delete(any(Department.class));
    }

    @Test
    void givenDepartmentId_whenGetBudgetStatus_thenStringGREEN() {
        Department department = buildDepartmentWithBudget("department", 5000);
        List<Project> projects = ImmutableList
                .of(buildProjectWithCost("project1", 2000), buildProjectWithCost("project2", 1000));
        when(departmentRepository.findById(anyLong()))
                .thenReturn(Optional.of(department));
        when(projectService.getAllByDepartment(anyLong()))
                .thenReturn(projects);

        String response = departmentService.getBudgetStatus(1L);

        assertThat(response)
                .isNotNull()
                .matches(status -> "GREEN".equals(status));
    }

    @Test
    void givenDepartmentId_whenGetBudgetStatus_thenStringYELLOW() {
        Department department = buildDepartmentWithBudget("department", 5000);
        List<Project> projects = ImmutableList
                .of(buildProjectWithCost("project1", 2000), buildProjectWithCost("project2", 3049));
        when(departmentRepository.findById(anyLong()))
                .thenReturn(Optional.of(department));
        when(projectService.getAllByDepartment(anyLong()))
                .thenReturn(projects);

        String response = departmentService.getBudgetStatus(1L);

        assertThat(response)
                .isNotNull()
                .matches(status -> "YELLOW".equals(status));
    }

    @Test
    void givenDepartmentId_whenGetBudgetStatus_thenStringRED() {
        Department department = buildDepartmentWithBudget("department", 5000);
        List<Project> projects = ImmutableList
                .of(buildProjectWithCost("project1", 2000), buildProjectWithCost("project2", 5000));
        when(departmentRepository.findById(anyLong()))
                .thenReturn(Optional.of(department));
        when(projectService.getAllByDepartment(anyLong()))
                .thenReturn(projects);

        String response = departmentService.getBudgetStatus(1L);

        assertThat(response)
                .isNotNull()
                .matches(status -> "RED".equals(status));
    }
}
