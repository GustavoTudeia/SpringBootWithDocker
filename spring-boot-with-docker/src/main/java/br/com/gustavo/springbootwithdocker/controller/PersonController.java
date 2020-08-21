package br.com.gustavo.springbootwithdocker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gustavo.springbootwithdocker.data.vo.PersonVO;
import br.com.gustavo.springbootwithdocker.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "PersonEndpoint")
@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService  personService;
	
	@ApiOperation(value = "Find all people" ) 
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<PersonVO> findAll(){
		return personService.findAll();
	}
	
	@ApiOperation(value = "Find a specific person by your ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findById(@PathVariable("id") String id) {
		return personService.findByid(Long.parseLong(id));
	}
	
	@ApiOperation(value = "Create a new person") 
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO person) {
		return personService.create(person);
	}
	
	@ApiOperation(value = "Update a specific person")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO person) {
		return personService.update(person);
	}
	
	@ApiOperation(value = "Delete a specific person by your ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		personService.delete(Long.parseLong(id));
		return ResponseEntity.ok().build();
	}
	
}
