package br.com.hotmart.java.controllers.vo;

import br.com.hotmart.java.entities.Adress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdressVO {

    private String country;

    private String state;

    private String city;

    private String street;

    private String adressCode;

    public AdressVO(Adress adress) {
        this.country = adress.getCountry();
        this.state = adress.getState();
        this.city = adress.getCity();
        this.street = adress.getStreet();
        this.adressCode = adress.getAdressCode();
    }
}
