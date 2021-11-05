package br.com.hotmart.java.services;

import br.com.hotmart.java.controllers.forms.AdressForm;
import br.com.hotmart.java.controllers.vo.AdressVO;
import br.com.hotmart.java.entities.Adress;
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

    public void saveAdress(AdressForm form) {
        Adress newAdress = new Adress(form);
        adressRepository.save(newAdress);
    }

    public AdressVO getById(Long id) {
        return new AdressVO(findById(id));
    }

    public Adress findById(Long id) {
        return adressRepository.findById(id).orElse(null);
    }

    public void update(Long id, AdressForm form) {
        Adress existingAdress = findById(id);
        existingAdress.update(form);
        adressRepository.save(existingAdress);
    }

    public void delete(Long id) {
        adressRepository.deleteById(id);
    }
}
