package ch.makery.address.util;

import ch.makery.address.model.Person;
import ch.makery.address.model.PersonVO;

import java.util.ArrayList;

public class ConversorPerson {

    public static PersonVO convertPersonToPersonVO(Person person){
        return new PersonVO(person.getId(), person.getFirstName(), person.getLastName(), person.getStreet(), person.getPostalCode(),
                person.getCity(), person.getBirthday());
    }
    public static Person convertPersonVO_Person(PersonVO personVO){
        Person person = new Person();
        person.setId(personVO.getId());
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
