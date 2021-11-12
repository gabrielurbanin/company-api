package br.com.hotmart.java.controllers;

import br.com.hotmart.java.controllers.forms.EmployeeForm;
import br.com.hotmart.java.controllers.vo.EmployeeVO;
import br.com.hotmart.java.controllers.vo.ProjectVO;
import br.com.hotmart.java.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("rest/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeVO>> getAll(@RequestParam String name) {
        if (name != null) {
            return ResponseEntity.ok().body(employeeService.getAllByName(name));
        }

        return ResponseEntity.ok().body(employeeService.getAll());
    }

    @PostMapping
    public ResponseEntity<EmployeeVO> save(@RequestBody EmployeeForm form, UriComponentsBuilder uriBuilder) {
        EmployeeVO savedEmployee = employeeService.save(form);
        URI uri = uriBuilder.path("/departments/{id}").buildAndExpand(savedEmployee.getId()).toUri();

        return ResponseEntity.created(uri).body(savedEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeVO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(employeeService.getById(id));
    }

    @GetMapping("/{id}/subordinates")
    public ResponseEntity<List<EmployeeVO>> getAllSubordinates(@PathVariable Long id) {
        return ResponseEntity.ok().body(employeeService.getAllSubordinates(id));
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<List<ProjectVO>> getAllProjects(@PathVariable Long id) {
        return ResponseEntity.ok().body(employeeService.getAllProjects(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeVO> update(@PathVariable Long id, @RequestBody EmployeeForm form) {
        return ResponseEntity.ok().body(employeeService.update(id,form));
    }

    @PatchMapping("/{id}/supervisors/{supervisorId}")
    public ResponseEntity<EmployeeVO> updateSupervisor(@PathVariable Long id, @PathVariable Long supervisorId) {
        return ResponseEntity.accepted().body(employeeService.updateSupervisor(id, supervisorId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
