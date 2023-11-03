package ch.makery.address.model;

import ch.makery.address.model.repository.PersonRepository;
import ch.makery.address.model.repository.impl.PersonRepositoryImpl;
import ch.makery.address.util.ConversorPerson;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class AgendaModel {

    private PersonRepository personRepository = new PersonRepositoryImpl();
    IntegerProperty numPersonVO = new SimpleIntegerProperty();

    public IntegerProperty getNumPersonVO() {
        return this.numPersonVO;
    }

    public void setNumPersonVO(Integer num) {
        this.numPersonVO.set(num);
    }

    public void incNumPersonVO(){
        this.numPersonVO.set(numPersonVO.get()+1);
    }

    public void decNumPersonVO(){
        this.numPersonVO.set(numPersonVO.get()-1);
    }

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
