package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.models.Person;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	private final String noRecordsFoundMessage = "No records found for this ID!";
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;

	public PersonVO findById(Long id) {
		logger.info("Finding one person!");
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(noRecordsFoundMessage));
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public List<PersonVO> findAll(){
		logger.info("Finding all people!");
		return DozerMapper.parseList(repository.findAll(), PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!");
		var entity = DozerMapper.parseObject(person, Person.class);
		return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating one person with V2!");
		var entity = mapper.convertVoTOEntity(person);
		return mapper.convertEntityToVo(repository.save(entity));
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating one person!");
		if(repository.existsById(person.getId())) {
			var entity = DozerMapper.parseObject(person, Person.class);
			return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		}
		else throw new ResourceNotFoundException(noRecordsFoundMessage);
	}
	
	public void delete(Long id) {
		logger.info(String.format("Deleting person %s!", id));
		if(repository.existsById(id))
			repository.deleteById(id);
		else throw new ResourceNotFoundException(noRecordsFoundMessage);
	}
}
