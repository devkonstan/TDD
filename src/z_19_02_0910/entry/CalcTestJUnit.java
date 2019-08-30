package z_19_02_0910.entry;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class CalcTestJUnit {

    private Calculator calc;


    @Parameters({
            "2,3,5",
            "4,1,5"
    })

    @Test                 // 1st par    2nd par  3rd par
    public void sumOFTwo(int first, int second, int sum) {
        // Given
        calc = new Calculator();
        // When
        int result = calc.add(first, second);
        // Then
        assertEquals(sum, result);
    }
}
