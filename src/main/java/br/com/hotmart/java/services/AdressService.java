package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.AdressForm;
import br.com.hotmart.java.controllers.vo.AdressVO;
import br.com.hotmart.java.entities.Adress;
import br.com.hotmart.java.exception.ResourceNotFoundException;
import br.com.hotmart.java.repositories.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdressService {

    @Autowired
    private AdressRepository adressRepository;

    public List<AdressVO> getAll() {
        return adressRepository.findAll().stream().map(AdressVO::new).collect(Collectors.toList());
    }

    public void save(AdressForm form) {
        Adress newAdress = new Adress(form);
        adressRepository.save(newAdress);
    }

    public AdressVO getById(Long id) {
        Adress adress = adressRepository.findById(id).orElse(null);

        if (adress == null) {
            throw new ResourceNotFoundException("id", "Adress does not exist!");
        }

        return new AdressVO(adress);
    }

    public Adress findById(Long id) {
        Adress adress = adressRepository.findById(id).orElse(null);

        if (adress == null) {
            throw new ResourceNotFoundException("id", "Adress does not exist!");
        }

        return adress;
    }

    public void update(Long id, AdressForm form) {
        Adress existingAdress = adressRepository.findById(id).orElse(null);

        if (existingAdress == null) {
            throw new ResourceNotFoundException("id", "Adress does not exist!");
        }

        existingAdress.update(form);
        adressRepository.save(existingAdress);
    }

    public void delete(Long id) {
        Adress adress = adressRepository.findById(id).orElse(null);

        if (adress == null) {
            throw new ResourceNotFoundException("id", "Adress does not exist!");
        }

        adressRepository.deleteById(id);
    }
}
