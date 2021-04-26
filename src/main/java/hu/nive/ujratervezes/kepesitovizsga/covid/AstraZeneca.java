package hu.nive.ujratervezes.kepesitovizsga.covid;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AstraZeneca extends Vaccine {

    public AstraZeneca(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Person> getVaccinationList() {

        Set<Person> temp = new LinkedHashSet<>();

        temp.addAll(super.getVaccinationList().stream().filter(person -> person.getChronic() == ChronicDisease.YES).collect(Collectors.toList()));
        temp.addAll(super.getVaccinationList().stream().filter(person -> person.getAge() > 65).collect(Collectors.toList()));
        temp.addAll(super.getVaccinationList());

        List<Person> pregnancies = super.getVaccinationList().stream().filter(person -> person.getPregnant() == Pregnancy.YES).collect(Collectors.toList());
        temp.removeAll(pregnancies);

        return new ArrayList<>(temp);
    }
}
