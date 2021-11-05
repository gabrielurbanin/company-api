package br.com.hotmart.java.entities;

import br.com.hotmart.java.controllers.forms.AdressForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "adress")
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String state;

    private String city;

    private String street;

    private String adressCode;

    public Adress(AdressForm form) {
        this.country = form.getCountry();
        this.state = form.getState();
        this.city = form.getCity();
        this.street = form.getStreet();
        this.adressCode = form.getAdressCode();
    }

    public void update(AdressForm form) {
        this.country = form.getCountry();
        this.state = form.getState();
        this.city = form.getCity();
        this.street = form.getStreet();
        this.adressCode = form.getAdressCode();
    }
}
