package br.com.hotmart.java.controllers.vo;

import br.com.hotmart.java.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserVO {
    private Long id;
    private String name;
    private String email;

    public UserVO(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
    }
}
