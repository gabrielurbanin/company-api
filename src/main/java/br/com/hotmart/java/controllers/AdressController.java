package br.com.hotmart.java.controllers;

import br.com.hotmart.java.controllers.forms.AdressForm;
import br.com.hotmart.java.controllers.vo.AdressVO;
import br.com.hotmart.java.services.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/adresses")
public class AdressController {

    @Autowired
    private AdressService adressService;

    @GetMapping
    public ResponseEntity<List<AdressVO>> getAll() {
        return ResponseEntity.ok().body(adressService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody AdressForm form) {
        adressService.save(form);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdressVO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(adressService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody AdressForm form) {
        adressService.update(id, form);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adressService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
