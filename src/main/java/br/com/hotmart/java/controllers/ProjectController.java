package br.com.hotmart.java.controllers;

import br.com.hotmart.java.controllers.forms.ProjectForm;
import br.com.hotmart.java.controllers.vo.ProjectVO;
import br.com.hotmart.java.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectVO>> getAll() {
        return ResponseEntity.ok().body(projectService.getAll());
    }

    @PostMapping
    public ResponseEntity<ProjectVO> save(@RequestBody ProjectForm form) {
        return ResponseEntity.ok().body(projectService.save(form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectVO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(projectService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectVO> update(@PathVariable Long id, @RequestBody ProjectForm form) {
        return ResponseEntity.ok().body(projectService.update(id, form));
    }

    @PatchMapping("/{id}/employee/{employeeId}")
    public ResponseEntity<ProjectVO> addEmployee(@PathVariable Long id, @PathVariable Long employeeId) {
        return ResponseEntity.ok().body(projectService.addEmployee(id, employeeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
