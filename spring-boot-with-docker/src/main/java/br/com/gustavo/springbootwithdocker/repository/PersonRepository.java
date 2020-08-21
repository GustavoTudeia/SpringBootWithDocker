package br.com.gustavo.springbootwithdocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gustavo.springbootwithdocker.data.model.Person;

@Repository
public interface PersonRepository  extends JpaRepository<Person, Long>{

}
