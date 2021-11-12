package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.ProjectForm;
import br.com.hotmart.java.controllers.vo.ProjectVO;
import br.com.hotmart.java.entities.Employee;
import br.com.hotmart.java.entities.Project;
import br.com.hotmart.java.exception.ResourceNotFoundException;
import br.com.hotmart.java.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    public List<ProjectVO> getAll() {
        return projectRepository.findAll().stream()
                .map(ProjectVO::new).collect(Collectors.toList());
    }

    public ProjectVO save(ProjectForm form) {
        Project newProject = new Project(form);
        newProject.setDepartment(departmentService.findById(form.getDepartmentId()));

        return new ProjectVO(projectRepository.save(newProject));
    }

    public Project findById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "Project does not exist!"));

        return project;
    }

    public ProjectVO getById(Long id) {
        return new ProjectVO(findById(id));
    }

    public ProjectVO update(Long id, ProjectForm form) {
        Project existingProject = findById(id);
        existingProject.update(form);

        return new ProjectVO(projectRepository.save(existingProject));
    }

    public void delete(Long id) {
        projectRepository.delete(findById(id));
    }

    public ProjectVO addEmployee(Long id, Long employeeId) {
        Project existingProject = findById(id);
        existingProject.getEmployees().add(employeeService.findById(employeeId));

        return new ProjectVO(projectRepository.save(existingProject));
    }
}
