package br.com.hotmart.java.controllers;

import br.com.hotmart.java.controllers.forms.DepartmentForm;
import br.com.hotmart.java.controllers.vo.DepartmentVO;
import br.com.hotmart.java.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rest/v1/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentVO>> getAll() {
        return ResponseEntity.ok().body(departmentService.getAll());
    }

    @PostMapping
    public ResponseEntity<DepartmentVO> save(@RequestBody DepartmentForm form, UriComponentsBuilder uriBuilder) {

        DepartmentVO savedDepartment = departmentService.save(form);
        URI uri = uriBuilder.path("/departments/{id}").buildAndExpand(savedDepartment.getId()).toUri();

        return ResponseEntity.created(uri).body(savedDepartment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentVO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(departmentService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentVO> update(@PathVariable Long id, @RequestBody DepartmentForm form) {
        return ResponseEntity.ok().body(departmentService.update(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
