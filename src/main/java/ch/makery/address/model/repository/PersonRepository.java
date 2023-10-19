package ch.makery.address.model.repository;

import ch.makery.address.model.ExeptionPerson;
import ch.makery.address.model.PersonVO;

import java.util.ArrayList;

public interface PersonRepository {
    ArrayList<PersonVO> GetListPersons() throws ExeptionPerson;

    void addPerson(PersonVO var1) throws ExeptionPerson;

    void deletePerson(Integer var1) throws ExeptionPerson;

    void editPerson(PersonVO var1, Integer id) throws ExeptionPerson;

    int lastId() throws ExeptionPerson;
}
