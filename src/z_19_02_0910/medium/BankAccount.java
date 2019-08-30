package z_19_02_0910.medium;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {

        balance = initialBalance;
    }

    /**
     * Uzytkownik nie powinien moc wplacic ujemnej ilosci pieniedzy
     */
    public void deposit(double amountToDeposit) {
        if(amountToDeposit <= 0) {  // nie mozna zlozyc w depozyt 0 i ujemnej ilosci pieniedzy
            throw new IllegalArgumentException(String.format("Cannot deposit %f amount of money", amountToDeposit));
        }
        balance += amountToDeposit;
    }

    /**
     * Uzytkownik nie powinien moc wyplacic wiecej pieniedzy niz ma na koncie!
     */
    public void withdraw(double amountToWithdraw) {
        if(balance - amountToWithdraw < 0) {
            String errorMessage = String.format("You don't have enough cash to withdraw %f. Current balance %f",
                                                amountToWithdraw, balance);
            throw new IllegalArgumentException(errorMessage);
        }
        balance -= amountToWithdraw;
    }

    public double getBalance() {
        return balance;
    }
}
