package ch.makery.address.model;

import ch.makery.address.model.repository.PersonRepository;

import java.util.ArrayList;

public class AgendaModel {

    private PersonRepository personRepository;

    public ArrayList<PersonVO> listPersonVO() throws ExeptionPerson {
        return this.personRepository.GetListPersons();
    }

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
