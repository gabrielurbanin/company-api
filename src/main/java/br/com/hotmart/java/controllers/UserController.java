package br.com.hotmart.java.controllers;

import br.com.hotmart.java.controllers.forms.UserForm;
import br.com.hotmart.java.controllers.vo.UserVO;
import br.com.hotmart.java.entities.User;
import br.com.hotmart.java.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/rest/v1/users")
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<UserVO> save(@RequestBody UserForm form, UriComponentsBuilder uriBuilder) {
        UserVO savedUser = authenticationService.save(form);
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(uri).body(savedUser);
    }
}
