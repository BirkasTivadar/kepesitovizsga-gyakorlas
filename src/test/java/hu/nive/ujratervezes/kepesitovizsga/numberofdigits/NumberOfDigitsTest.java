package hu.nive.ujratervezes.kepesitovizsga.numberofdigits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberOfDigitsTest {

    @Test
    void testGetNumberOfDigits() {
       assertEquals(13, new NumberOfDigits().getNumberOfDigits(11));
    }
}