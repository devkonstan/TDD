package z_19_02_0910.medium;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class BankAccountTests {
    private BankAccount bankAccount;

    @Before
    public void setUp() {
        bankAccount = new BankAccount(100);
    }

    @Test
    public void depositingMoneyShouldIncreaseTheBalanceByGivenAmount() {
        // Given
        double expectedBalance = 200;

        // When
        bankAccount.deposit(100);

        // Then
        assertEquals(expectedBalance, bankAccount.getBalance(), 0);
    }

    @Parameters({"-100.0", "0.0"})

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenUserTriesToDepositNegativeAmountOfMoney(double invalidAmountToDeposit) {
        bankAccount.deposit(invalidAmountToDeposit);
    }

    @Test
    public void withdrawingMoneyShouldDecreaseBalanceWhenUserHasEnoughMoney() {
        // Given
        double expectedBalance = 48;

        // When
        bankAccount.withdraw(52);

        // Then
        assertEquals(expectedBalance, bankAccount.getBalance(), 0);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenUserTriesToWithdrawMoreMoneyThanHeHas() {
        final double amountToWithdraw = 200;
        bankAccount.withdraw(amountToWithdraw);
    }


}

