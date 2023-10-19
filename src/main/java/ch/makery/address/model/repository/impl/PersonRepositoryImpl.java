package ch.makery.address.model.repository.impl;

import ch.makery.address.model.ExeptionPerson;
import ch.makery.address.model.PersonVO;
import ch.makery.address.model.repository.PersonRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonRepositoryImpl implements PersonRepository {
    private final ConnectionJDBC connectionJDBC = new ConnectionJDBC();

    private Statement stmt;
    private String sentence;
    private ArrayList<PersonVO> personVOS;
    private PersonVO personVO;

    public PersonRepositoryImpl(){}

    public ArrayList<PersonVO> GetListPersons() throws ExeptionPerson{
        try {
            Connection conn = this.connectionJDBC.connectDB();
            this.personVOS = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentence = "SELECT * FROM Personas";
            ResultSet rs = this.stmt.executeQuery(this.sentence);

            while(rs.next()) {
                Integer id = rs.getInt("id");
                String firstName = rs.getString("name");
                String lastName = rs.getString("lastName");
                String street = rs.getString("street");
                String city = rs.getString("city");
                Integer postalCode = rs.getInt("postalCode");
                LocalDate birthdate = rs.getDate("birthday").toLocalDate();
                this.personVO = new PersonVO(firstName, lastName, street, postalCode, city, birthdate);
                this.personVOS.add(this.personVO);
            }

            this.connectionJDBC.disconnectDB(conn);
            return this.personVOS;
        } catch (SQLException var6) {
            throw new ExeptionPerson("No se ha podido realizar la operación");
        }
    }

    public void addPerson(PersonVO personVO) throws ExeptionPerson {
        try {
            Connection conn = this.connectionJDBC.connectDB();
            this.stmt = conn.createStatement();
            this.sentence = "INSERT INTO Personas (name, lastName, street, city, postalCode, birthday) " +
                    "VALUES ('" + personVO.getFirstName() + "','" + personVO.getLastName() +
                    "','" + personVO.getStreet()+ "','" + personVO.getCity() + "'," + personVO.getPostalCode() +
                    ",'" + personVO.getBirthday() +"');";
            this.stmt.executeUpdate(this.sentence);
            this.stmt.close();
            this.connectionJDBC.disconnectDB(conn);
        } catch (SQLException var3) {
            throw new ExeptionPerson("No se ha podido realizar la operación");
        }
    }

    public void deletePerson(Integer idPerson) throws ExeptionPerson {
        try {
            Connection conn = this.connectionJDBC.connectDB();
            this.stmt = conn.createStatement();
            Statement command = conn.createStatement();
            String sql = String.format("DELETE FROM Personas WHERE id = %d", idPerson);
            command.executeUpdate(sql);
            this.connectionJDBC.disconnectDB(conn);
        } catch (SQLException var5) {
            throw new ExeptionPerson("No se ha podido relaizr la eliminación");
        }
    }

    public void editPerson(PersonVO personVO, Integer id) throws ExeptionPerson {
        try {
            Connection conn = this.connectionJDBC.connectDB();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE Personas SET name = '%s', lastName = '%s', street = '%s', " +
                    "city = '%s', postalCode = '%s', birthday = '%s'  WHERE id = %d",
                    personVO.getFirstName(), personVO.getLastName(), personVO.getStreet(), personVO.getCity(),
                    personVO.getPostalCode(), personVO.getBirthday(), id);
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExeptionPerson("No se ha podido relaizr la edición");
        }
    }

    public int lastId() throws ExeptionPerson {
        int lastPersonId = 0;

        try {
            Connection conn = this.connectionJDBC.connectDB();
            Statement command = conn.createStatement();

            for(ResultSet register = command.executeQuery("SELECT id FROM Personas ORDER BY id DESC LIMIT 1");
                register.next(); lastPersonId = register.getInt("id")) {
            }

            return lastPersonId;
        } catch (SQLException var5) {
            throw new ExeptionPerson("No se ha podido realizar la busqueda del ID");
        }
    }
}
