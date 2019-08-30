package z_19_02_0910.advanced.sandbox;


public class Computer {
    private final Mouse myszka;
    private final Keyboard klawka;


    public Computer(Mouse myszka, Keyboard klawka) {
        this.myszka = myszka;
        this.klawka = klawka;
    }

//  imituje wprowadzanie tekstu z klawiatury (klawisz po klawiszu)
    public void typeText(String tekst) {
        for (int i = 0; i < tekst.length(); i++) {
            klawka.pressKey(tekst.charAt(i));

        }
    }

    public void open() {
        myszka.click(); //click() jest zaleznoscia myszki czyli obiektu klasy Mouse
        myszka.click();
    }
}
