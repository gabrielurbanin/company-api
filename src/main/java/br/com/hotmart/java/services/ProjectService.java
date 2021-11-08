package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.ProjectForm;
import br.com.hotmart.java.controllers.vo.ProjectVO;
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

    public List<ProjectVO> getAll() {
        return projectRepository.findAll().stream().map(ProjectVO::new).collect(Collectors.toList());
    }

    public void save(ProjectForm form) {
        Project newProject = new Project(form);

        newProject.setDepartment(departmentService.findById(form.getDepartmentId()));

        projectRepository.save(newProject);
    }

    public ProjectVO getById(Long id) {
        Project project = projectRepository.findById(id).orElse(null);

        if (project == null) {
            throw new ResourceNotFoundException("id", "Project does not exist!");
        }

        return new ProjectVO(project);
    }

    public void update(Long id, ProjectForm form) {
        Project existingProject = projectRepository.findById(id).orElse(null);

        if (existingProject == null) {
            throw new ResourceNotFoundException("id", "Project does not exist!");
        }

        existingProject.update(form);
        projectRepository.save(existingProject);
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    public List<Project> findAllById(List<Long> projectId) {
        try {
            return projectRepository.findAllById(projectId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("id", "Was not able to fetch all ids!");
        }

    }
}
