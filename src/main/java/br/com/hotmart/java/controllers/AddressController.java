package br.com.hotmart.java.controllers;

import br.com.hotmart.java.controllers.forms.AddressForm;
import br.com.hotmart.java.controllers.vo.AddressVO;
import br.com.hotmart.java.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressVO>> getAll() {
        return ResponseEntity.ok().body(addressService.getAll());
    }

    @PostMapping
    public ResponseEntity<AddressVO> save(@RequestBody AddressForm form) {
        return ResponseEntity.ok().body(addressService.save(form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressVO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(addressService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressVO> update(@PathVariable Long id, @RequestBody AddressForm form) {
        return ResponseEntity.ok().body(addressService.update(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
