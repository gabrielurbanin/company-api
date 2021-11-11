package br.com.hotmart.java.repositories;

import br.com.hotmart.java.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<Address, Long> {

}
