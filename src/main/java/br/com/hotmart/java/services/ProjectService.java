package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.ProjectForm;
import br.com.hotmart.java.controllers.vo.ProjectVO;
import br.com.hotmart.java.entities.Project;
import br.com.hotmart.java.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectVO> getAll() {
        return projectRepository.findAll().stream().map(ProjectVO::new).collect(Collectors.toList());
    }

    public void save(ProjectForm form) {
        Project newProject = new Project(form);
        projectRepository.save(newProject);
    }

    public ProjectVO getById(Long id) {
        return new ProjectVO(projectRepository.findById(id).orElse(null));
    }

    public void update(Long id, ProjectForm form) {
        Project existingProject = projectRepository.findById(id).orElse(null);
        existingProject.update(form);
        projectRepository.save(existingProject);
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
