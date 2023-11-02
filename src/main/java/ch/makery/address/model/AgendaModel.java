package ch.makery.address.model;

import ch.makery.address.model.repository.PersonRepository;
import ch.makery.address.model.repository.impl.PersonRepositoryImpl;
import ch.makery.address.util.ConversorPerson;

import java.util.ArrayList;

public class AgendaModel {

    private PersonRepository personRepository = new PersonRepositoryImpl();

    public ArrayList<PersonVO> listPersonVO() throws ExeptionPerson {
        return this.personRepository.GetListPersons();
    }

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void addPersonVO(Person person) throws ExeptionPerson {
        PersonVO personVO = ConversorPerson.convertPersonToPersonVO(person);
        personRepository.addPerson(personVO);
    }

    public void deletePersonVO(Person person) throws ExeptionPerson {
        PersonVO personVO = ConversorPerson.convertPersonToPersonVO(person);
        personRepository.deletePerson(personVO.getId());
    }

    public void editPersonVO (Person person) throws ExeptionPerson {
        PersonVO personVO = ConversorPerson.convertPersonToPersonVO(person);
        personRepository.editPerson(personVO, personVO.getId());
    }

    public int getLastId() throws ExeptionPerson {
        return personRepository.lastId()+1;
    }
}
