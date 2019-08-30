package z_19_02_0910.entry;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class CalculatorTests {

    private Calculator calculator;

    /**
     * Uzylismy adnotacji Before aby stworzyc metode tworzaca swieza instancje klasy Calculator w każdym teście
     * Dzieki temu nie musimy pisac kodu tej metody w kazdym tescie (DRY)
     * <p>
     * W tym przypadku metoda setUp staje sie czescia sekcji "Given" kazdego testu
     */
    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void addingTwoNumbersShouldReturnExpectedResult() {
        // Given
        final int expectedResult = 5;

        // When
        int result = calculator.add(2, 3);

        // Then
        assertEquals(expectedResult, result);
    }

    @Test
    public void subtractingTwoNumbersShouldReturnExpectedResult() {
        // Given
        final int expectedResult = -1;

        // When
        int result = calculator.subtract(4, 5);

        // Then
        assertEquals(expectedResult, result);
    }

    @Test
    public void multiplyingTwoNumbersShouldReturnExpectedResult() {
        // Given
        final int expectedResult = 20;

        // When
        int result = calculator.multiply(4, 5);

        // Then
        assertEquals(expectedResult, result);
    }

    @Test
    public void dividingWhenDenominatorIsNotZeroShouldReturnExpectedResult() {
        // Given
        /*
            Aby uzyskac poprawny wynik, licznik musi byc zapisany jako double.
            Dlatego zapisujemy 2.0, a nie 2
         */
        // licznik -> counter
        final double expectedResult = 2.0 / 3;

        // When
        double result = calculator.divide(2, 3);

        // Then
        /*
            Gdy porownujemy dwie liczby zmiennoprzecinkowe (float lub double) musimy podac
            do metody assertEqual trzeci parametr - delte. Powodem potrzeby wprowadzenia jest
            niedokladnosc reprezentacji liczb zmiennoprzecinkowych przez komputer.
            Parametr ten pozwala okreslic tolerancje bledu (0 = nie dopuszczamy bledu)
            Delta powinna byc relatywnie mala np. 1e-15
         */
        assertEquals(expectedResult, result, 0.5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void dividingByZeroShouldThrowSuitableException() {
        calculator.divide(2, 0);
    }

    @Parameters({"2", "4", "50", "124"})

    @Test
    public void isEvenShouldReturnTrueWhenTheNumberIsEven(int number) {
        // When
        boolean isEven = calculator.isEven(number);

        // Then
        assertTrue(isEven);
    }

    @Parameters({"3", "5", "51", "125"})

    @Test
    public void isEvenShouldReturnFalseWhenTheNumberIsNotEven(int number) {
        // When
        boolean isEven = calculator.isEven(number);

        // Then
        assertFalse(isEven);
    }

    @Test
    public void isOddShouldReturnTrueIfTheNumberIsOdd() {
        // When
        boolean isOdd = calculator.isOdd(3);
        // Then
        assertTrue(isOdd);
    }

    @Test
    public void isOddShouldReturnFalseIfTheNumberIsNotOdd() {
        // When
        boolean isOdd = calculator.isOdd(6);
        // Then
        assertFalse(isOdd);
    }


}


