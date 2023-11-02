package ch.makery.address.model;

import ch.makery.address.model.repository.PersonRepository;
import ch.makery.address.model.repository.impl.PersonRepositoryImpl;
import ch.makery.address.util.ConversorPerson;

import java.util.ArrayList;

public class AgendaModel {

    private PersonRepository personRepository;

    public ArrayList<PersonVO> listPersonVO() throws ExeptionPerson {
        return this.personRepository.GetListPersons();
    }

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void addPersonVO(Person person) throws ExeptionPerson {
        personRepository = new PersonRepositoryImpl();
        PersonVO personVO = ConversorPerson.convertPersonToPersonVO(person);
        personRepository.addPerson(personVO);
    }

    public void deletePersonVO(Person person) throws ExeptionPerson {
        personRepository = new PersonRepositoryImpl();
        PersonVO personVO = ConversorPerson.convertPersonToPersonVO(person);
        System.out.println(personVO.getId());
        personRepository.deletePerson(personVO.getId());
    }

    public void editPersonVO (Person person) throws ExeptionPerson {
        personRepository = new PersonRepositoryImpl();
        PersonVO personVO = ConversorPerson.convertPersonToPersonVO(person);
        personRepository.editPerson(personVO, personVO.getId());
    }
}
