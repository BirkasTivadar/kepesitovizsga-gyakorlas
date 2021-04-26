package hu.nive.ujratervezes.kepesitovizsga.numberofdigits;

public class NumberOfDigits {


    public int getNumberOfDigits(int number) {
        int multiplier = 1;
        int counter = 0;

        while (number > 0) {
            counter += number*(multiplier);
            multiplier++;
            number /= 10;
        }
        return counter;
    }
}
