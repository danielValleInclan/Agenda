package ch.makery.address.util;

import ch.makery.address.model.Person;
import ch.makery.address.model.PersonVO;

import java.util.ArrayList;

public class ConversorPerson {
    public static Person convertPersonVO_Person(PersonVO personVO){
        Person person = new Person();
        person.setId(person.getId());
        person.setFirstName(personVO.getFirstName());
        person.setLastName(personVO.getLastName());
        person.setCity(personVO.getCity());
        person.setStreet(personVO.getStreet());
        person.setPostalCode(personVO.getPostalCode());
        person.setBirthday(personVO.getBirthday());
        return person;
    }
    public static ArrayList<Person> convertListPersonVO_Person(ArrayList<PersonVO> personVOS){
        ArrayList<Person> listPerson = new ArrayList<>();
        for (PersonVO pV: personVOS) {
            Person person;
            person = ConversorPerson.convertPersonVO_Person(pV);
            listPerson.add(person);
        }
        return listPerson;
    }
}
