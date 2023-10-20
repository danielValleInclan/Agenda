package ch.makery.address;

import ch.makery.address.model.ExeptionPerson;
import ch.makery.address.model.PersonVO;
import ch.makery.address.model.repository.impl.PersonRepositoryImpl;
import java.sql.SQLException;
import java.time.LocalDate;

public class PruebaBBDDMain {
    public static void main(String[] args) throws ExeptionPerson, SQLException {

        PersonVO personVO1 = new PersonVO("Daniel", "Rodriguez", "Dq Talavera",
                    41500, "Sevilla", LocalDate.parse("2003-07-28"));
        PersonVO personVO2 = new PersonVO("Fernando", "Azaña", "Dq Talavera",
                41500, "Sevilla", LocalDate.parse("2003-07-28"));
        PersonVO personVO3 = new PersonVO("Ferran", "Torres", "Dq Talavera",
                41500, "Sevilla", LocalDate.parse("2003-07-28"));
        PersonVO personVO4 = new PersonVO("Editado", "Editado", "Editado",
                12344, "Madrid", LocalDate.parse("2015-01-01"));

        PersonRepositoryImpl personRepository = new PersonRepositoryImpl();
        personRepository.addPerson(personVO1);
        personRepository.addPerson(personVO2);
        personRepository.addPerson(personVO3);
        personRepository.addPerson(personVO4);
        personRepository.editPerson(personVO4, personRepository.lastId() -1 );
        personRepository.deletePerson(personRepository.lastId() - 3);
        for (PersonVO p: personRepository.GetListPersons()) {
            System.out.println("Id: " + p.getId() + " Nombre: " + p.getFirstName() + " Apellido: " + p.getLastName());
        }
    }
}
