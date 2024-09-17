package br.com.erudio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.erudio.models.Person;

@Service
public class PersonServices {
	private final AtomicLong counter = new AtomicLong(); 
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	public Person findById(String id) {
		logger.info("Finding one person!");
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Leandro");
		person.setLastName("Costa");
		person.setAddress("Uberlândia");
		person.setGender("Male");
		return person;
	}
	
	public List<Person> findAll(){
		logger.info("Finding all people!");
		List<Person> persons = new ArrayList<>();
		for(int i=0; i<8; i++) {
			persons.add(mockPerson(i));
		}
		return persons;
	}

	private Person mockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " + i);
		person.setLastName("Last name " + i);
		person.setAddress("Some address in Brasil");
		String[] genders = {"Male", "Female"};
		person.setGender(genders[i%2]);
		return person;
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		return person;
	}
	
	public Person update(Person person) {
		logger.info("Updating one person!");
		return person;
	}
	
	public void delete(String id) {
		logger.info(String.format("Deleting person %s!", id));
	}
}
