package z_19_02_0910.entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//niby lepsza metoda, bo brak konstruktora i mniej kodu
@RunWith(Parameterized.class)
public class CalcTests2 {

    private Calculator calc;

    //dlaczego public? czy to ma zwiazek z pominieciem konstruktora?
    @Parameterized.Parameter(0)
    public int first;
    @Parameterized.Parameter(1)
    public int second;
    @Parameterized.Parameter(2)
    public int sum;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {2, 3, 5}, {9, 8, 17}
        });
    }

    @Test
    public void sumOfTwo() {
        calc = new Calculator();
        int result = calc.add(first, second);
        assertEquals(sum, result);
    }
}
