package br.com.hotmart.java.entities;

import br.com.hotmart.java.controllers.forms.AddressForm;
import lombok.*;

import javax.persistence.*;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "adress")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String state;

    private String city;

    private String street;

    private String addressCode;

    public Address(AddressForm form) {
        this.country = form.getCountry();
        this.state = form.getState();
        this.city = form.getCity();
        this.street = form.getStreet();
        this.addressCode = form.getAddressCode();
    }

    public void update(AddressForm form) {
        this.country = form.getCountry();
        this.state = form.getState();
        this.city = form.getCity();
        this.street = form.getStreet();
        this.addressCode = form.getAddressCode();
    }
}
