package hu.nive.ujratervezes.kepesitovizsga.dogs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class DogManager {

    private static final String SEPARATOR = ";";

    private List<Dog> dogs = new ArrayList<>();


    public String getCountryByExactDogSpecies(String name) {

        loadDogs();

        for (Dog dog : dogs) {
            if (name.equalsIgnoreCase(dog.getName())) {
                return dog.getCountry();
            }
        }

        throw new IllegalArgumentException("No such dog name found.");
    }


    public List<Dog> getDogsInAlphabeticalOrderByName() {

        loadDogs();

        List<Dog> result = dogs;

        result.sort(Comparator.comparing(Dog::getName));

        return result;
    }


    public Map<String, Long> getDogStatistics() {
        loadDogs();
        return dogs.stream().collect(Collectors.groupingBy(Dog::getCountry, HashMap::new, Collectors.counting()));
    }


    private void loadDogs() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                DogManager.class.getResourceAsStream("/dogs.csv")))) {

            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String name = line.split(SEPARATOR)[1];
                String country = line.split(SEPARATOR)[4];
                String url = line.split(SEPARATOR)[5];

                dogs.add(new Dog(name, country, url));
            }

        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
    }

}
