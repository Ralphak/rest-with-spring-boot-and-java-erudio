package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.models.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	private final String noRecordsFoundMessage = "No records found for this ID!";
	
	@Autowired
	PersonRepository repository;

	public Person findById(Long id) {
		logger.info("Finding one person!");
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(noRecordsFoundMessage));
	}
	
	public List<Person> findAll(){
		logger.info("Finding all people!");
		return repository.findAll();
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		return repository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Updating one person!");
		if(repository.existsById(person.getId()))
			return repository.save(person);
		else throw new ResourceNotFoundException(noRecordsFoundMessage);
	}
	
	public void delete(Long id) {
		logger.info(String.format("Deleting person %s!", id));
		if(repository.existsById(id))
			repository.deleteById(id);
		else throw new ResourceNotFoundException(noRecordsFoundMessage);
	}
}
