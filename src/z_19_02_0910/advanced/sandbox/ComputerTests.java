package z_19_02_0910.advanced.sandbox;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyChar;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atMost;

@RunWith(MockitoJUnitRunner.class)
public class ComputerTests {
    private Computer computer;
    private Keyboard keyboard;

    @Mock //dzieki temu korzystamy z atrapy dla myszki
    private Mouse mouse;

    @Before
    public void setUp() {
        keyboard = Mockito.mock(Keyboard.class); //klawke mockujemy przez Mockito.mock()
//        mouse = Mockito.mock(Mouse.class); //analogicznie do powyzszej
        computer = new Computer(mouse, keyboard);
    }

    @Test
    public void proofClick() {

        mouse.click();
    }

    @Test
    public void proofOpen() {

        computer.open();
    }

    @Test
    public void mouseShouldBeClickedTwiceWhenOpenIsExecuted() {
        // given
        final int expectedAmoutOfClicks = 2;

        // when
        computer.open();

        // then
        verify(mouse, times(expectedAmoutOfClicks)).click(); //dokladnie 2 razy
        verify(mouse, atLeastOnce()).click(); //przynajmniej raz
        verify(mouse, atMost(expectedAmoutOfClicks)).click(); //najwyzej 2 razy
    }

    @Test
    public void typeTextShouldInvokePressKeyForEveryCharacterInTheText() {
        // given
        final String textToBeTyped = "Some text to be tested";

        // when
        computer.typeText(textToBeTyped);

        // then
        verify(keyboard, times(textToBeTyped.length())).pressKey(anyChar()); //dokladnie caly ciag znakow
        verify(keyboard, atLeastOnce()).pressKey(anyChar()); //przynajmniej raz jakikolwiek znak
        verify(keyboard, atMost(textToBeTyped.length())).pressKey(anyChar()); //najwyzej wypisze caly wyraz
    }

}
