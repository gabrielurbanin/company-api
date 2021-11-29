package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.UserForm;
import br.com.hotmart.java.controllers.vo.UserVO;
import br.com.hotmart.java.entities.User;
import br.com.hotmart.java.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return user.get();
    }

    public UserVO save(UserForm form) {
        User newUser = new User(form);
        newUser.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));

        return new UserVO(userRepository.save(newUser));
    }
}
