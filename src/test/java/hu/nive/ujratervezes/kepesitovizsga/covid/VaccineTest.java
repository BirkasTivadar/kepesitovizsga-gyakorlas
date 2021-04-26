package hu.nive.ujratervezes.kepesitovizsga.covid;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTest {

    private Vaccine vaccine;
    private MysqlDataSource dataSource;

    @BeforeEach
    public void init() {
        dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/employees?useUnicode=true");
        dataSource.setUser("employees");
        dataSource.setPassword("employees");

        initTable();
        createDummyData();

    }

    @Test
    void testPfizer() {
        vaccine = new Pfizer(dataSource);
        List<Person> vaccinationList = vaccine.getVaccinationList();

        assertEquals(15, vaccinationList.size());
        assertEquals("Nagy Eleonóra", vaccinationList.get(0).getName());
        assertEquals("Németh Béla", vaccinationList.get(3).getName());
        assertEquals("Kiss József", vaccinationList.get(6).getName());
        assertEquals("Kovács Tamás", vaccinationList.get(14).getName());
    }

    @Test
    void testAstraZeneca() {
        vaccine = new AstraZeneca(dataSource);
        List<Person> vaccinationList = vaccine.getVaccinationList();

        assertEquals(12, vaccinationList.size());
        assertEquals("Szép Virág", vaccinationList.get(0).getName());
        assertEquals("Bánkuti Bendegúz", vaccinationList.get(4).getName());
        assertEquals("Németh Béla", vaccinationList.get(5).getName());
        assertEquals("Bíró Rita", vaccinationList.get(11).getName());
    }

    @Test
    void testSinoPharm() {
        vaccine = new SinoPharm(dataSource);
        List<Person> vaccinationList = vaccine.getVaccinationList();

        assertEquals(7, vaccinationList.size());
        assertEquals("Kiss József", vaccinationList.get(0).getName());
        assertEquals("Szabó Veronika", vaccinationList.get(1).getName());
        assertEquals("Németh Béla", vaccinationList.get(5).getName());
        assertEquals("Török István", vaccinationList.get(6).getName());
    }

    @AfterEach
    public void destruct() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String dropHousePoints = "DROP TABLE IF EXISTS registrations";
            Statement statement = connection.createStatement();
            statement.execute(dropHousePoints);
        }
    }

    private void initTable() {
        try (Connection connection = dataSource.getConnection()) {
            String createRegistrationsTable = "CREATE TABLE IF NOT EXISTS registrations (" +
                    "person_name VARCHAR(255), " +
                    "age INT, " +
                    "chronic_disease VARCHAR(255), " +
                    "pregnancy VARCHAR(255)" +
                    ");";
            Statement statement = connection.createStatement();
            statement.execute(createRegistrationsTable);
        } catch (SQLException sqlException) {
            throw new IllegalStateException("Connection failed", sqlException);
        }
    }

    private void createDummyData() {
        try (Connection connection = dataSource.getConnection()) {
            String insertRegistration = "INSERT INTO registrations VALUES ('Kiss József', 45, 'nem', 'nem')";
            Statement stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Nagy Eleonóra', 35, 'nem', 'igen')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Szép Virág', 65, 'igen', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Németh Béla', 72, 'nem', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Gárdos Géza', 25, 'igen', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Szabó Veronika', 32, 'nem', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Vedres Károly', 53, 'nem', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Ökrös Gizella', 33, 'nem', 'igen')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Fekete Dávid', 62, 'nem', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Bíró Rita', 29, 'nem', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Baráth Zita', 58, 'igen', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Kovács Tamás', 42, 'igen', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Török István', 81, 'nem', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Fehér Ágnes', 34, 'igen', 'igen')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
            insertRegistration = "INSERT INTO registrations VALUES ('Bánkuti Bendegúz', 93, 'igen', 'nem')";
            stmt = connection.createStatement();
            stmt.execute(insertRegistration);
        } catch (SQLException sqlException) {
            throw new IllegalStateException("Connection failed");
        }
    }
}