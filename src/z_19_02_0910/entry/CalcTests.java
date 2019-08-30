package z_19_02_0910.entry;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CalcTests {

    private final Calculator calc;
    private final int first;
    private final int second;
    private final int sum;

    public CalcTests(int first, int second, int sum) {
        calc = new Calculator();
        this.first = first;
        this.second = second;
        this.sum = sum;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {2, 3, 5}, {9, 8, 17}
        });
    }

    @Test
    public void sumOfTwo() {
        int result = calc.add(first, second);
        assertEquals(sum, result);
    }
}
