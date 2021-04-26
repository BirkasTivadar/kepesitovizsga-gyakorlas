package hu.nive.ujratervezes.kepesitovizsga.covid;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SinoPharm extends Vaccine {

    public SinoPharm(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Person> getVaccinationList() {

        Set<Person> temp = new LinkedHashSet<>();

        temp.addAll(super.getVaccinationList().stream().filter(person -> person.getAge() < 65).collect(Collectors.toList()));
        temp.addAll(super.getVaccinationList());

        List<Person> pregnanciesOrChronics = super.getVaccinationList().stream().filter(person -> person.getPregnant() == Pregnancy.YES || person.getChronic() == ChronicDisease.YES).collect(Collectors.toList());
        temp.removeAll(pregnanciesOrChronics);

        return new ArrayList<>(temp);
    }
}
