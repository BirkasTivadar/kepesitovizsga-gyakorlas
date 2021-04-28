package hu.nive.ujratervezes.kepesitovizsga.covid;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Vaccine {

    private DataSource dataSource;

    private List<Person> vaccinationList = new ArrayList<>();

    public Vaccine(DataSource dataSource) {
        this.dataSource = dataSource;
        loadVaccinationList();
    }

    private void loadVaccinationList() {

        try (
                Connection conn = dataSource.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM registrations;")
        ) {

            while (rs.next()) {
                String name = rs.getString("person_name");
                int age = rs.getInt("age");
                ChronicDisease chronicDisease = rs.getString("chronic_disease").equals("igen") ? ChronicDisease.YES : ChronicDisease.NO;
                Pregnancy pregnancy = rs.getString("pregnancy").equals("igen") ? Pregnancy.YES : Pregnancy.NO;
                vaccinationList.add(new Person(name, age, chronicDisease, pregnancy));
            }

        } catch (SQLException sqlException) {
            throw new IllegalStateException("Connection failed", sqlException);
        }
    }

    public List<Person> getVaccinationList() {
        return new ArrayList<>(vaccinationList);
    }
}
