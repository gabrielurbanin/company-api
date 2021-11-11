package br.com.hotmart.java.controllers.vo;

import br.com.hotmart.java.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressVO {
    private Long id;

    private String state;

    private String city;

    private String street;

    public AddressVO(Address address) {
        this.id = address.getId();
        this.state = address.getState();
        this.city = address.getCity();
        this.street = address.getStreet();
    }
}
