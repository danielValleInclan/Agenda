package ch.makery.address;

import ch.makery.address.model.ExeptionPerson;
import ch.makery.address.model.PersonVO;
import ch.makery.address.model.repository.impl.ConnectionJDBC;
import ch.makery.address.model.repository.impl.PersonRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class PruebaBBDDMain {
    public static void main(String[] args) throws ExeptionPerson, SQLException {
        PersonVO personVO1 = new PersonVO(1, "Daniel", "Rodriguez", "Dq Talavera",
                    41500, "Sevilla", LocalDate.parse("2003-07-28"));
        PersonVO personVO2 = new PersonVO(2, "Fernando", "Azaña", "Dq Talavera",
                41500, "Sevilla", LocalDate.parse("2003-07-28"));
        PersonVO personVO3 = new PersonVO(3, "Ferran", "Torres", "Dq Talavera",
                41500, "Sevilla", LocalDate.parse("2003-07-28"));

        PersonRepositoryImpl personRepository = new PersonRepositoryImpl();
        personRepository.addPerson(personVO1);
        personRepository.addPerson(personVO2);
        personRepository.addPerson(personVO3);
        for (PersonVO p: personRepository.GetListPersons()) {
            System.out.println("Nombre: " + p.getFirstName());
        }
    }
}
