package br.com.gustavo.springbootwithdocker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gustavo.springbootwithdocker.converter.DozerConverter;
import br.com.gustavo.springbootwithdocker.data.model.Person;
import br.com.gustavo.springbootwithdocker.data.vo.PersonVO;
import br.com.gustavo.springbootwithdocker.exception.ResourceNotFoundException;
import br.com.gustavo.springbootwithdocker.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;
	
	public PersonVO create(PersonVO personVO) {
		Person person = DozerConverter.paserObject(personVO, Person.class);
		person = personRepository.save(person);
		
		PersonVO returnPersonVO = DozerConverter.paserObject(person, PersonVO.class);
		
		return returnPersonVO;
	}
	
	public List<PersonVO> findAll(){
		List<PersonVO> listPersonVO = DozerConverter.paserListObject(personRepository.findAll(), PersonVO.class);
		return listPersonVO;
	}
	
	public PersonVO findByid(Long id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found this ID"));
		PersonVO personVO = DozerConverter.paserObject(person, PersonVO.class);
		return personVO;
	}
	
	public PersonVO update(PersonVO personVO) {
		personRepository.findById(personVO.getId()).orElseThrow(() -> new ResourceNotFoundException("No record found this ID"));
		return create(personVO);
	}
	
	
	public void delete(Long id) {
		Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found this ID"));
		personRepository.delete(entity);
	}

}
