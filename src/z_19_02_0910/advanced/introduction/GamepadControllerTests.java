package z_19_02_0910.advanced.introduction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class GamepadControllerTests {

    @Mock
    private MobileRobot mobileRobot; //mockujemy zaleznosci a nie klasy tworzace

    /**
     * Mocki mozemy rozwniez wstrzyknac za pomoca adnotacji @InjectMocks.
     * Zastepuje nam ona reczne wywolanie konstruktora.
     * Jest rownoznaczna zapisowi:
     * <p>
     * gamepadController = new GamepadController(mobileRobot);
     * <p>
     * Zauwaz, ze gdy korzystamy z adnotacji nigdzie nie uzywamy operatora "new" do stworzenia obiektu
     */
    @InjectMocks
    private GamepadController gamepadController; //to testujemy

    /**
     * Podobnie jak w przypadku testow klasy Computer (ComputerTests) probujemy przetestowac metode, ktora nic nie zwraca.
     * Metode analogPositionChanged mozemy przetestowac poprzez sprawdzenie czy metoda move(double, double) jest
     * wywolana dokladnie raz. Dodatkowe asercje pozwalaja nam upewnic sie czy nasze zalozenie jest poprawne, a nie
     * jest wynikiem bledu niepoprawnego zapisu asercji verify.
     * <p>
     * Metody verify oraz when (bedace czescia biblioteki Mockito) mozemy wykonywac tylko na mockach!
     * Nie testujemy mockow, tylko obiekty, do ktorych je wstrzykujemy!!!
     */
    @Test
    public void moveShouldBeUsedOnMobileRobotWhenAnalogPositionChangedIsInvoked() {
        // When
        gamepadController.analogPositionChanged(10.0, 23.3);

        // Then
        /* Testujemy czy metoda zostala wykonana z jakimikolwiek parametrami. Aby postawic jakikolwiek parametr
         * wykorzystujemy metode anyDouble(). Dlaczego double? Poniewaz metoda "move" przyjmuje parametry tego typu.
         * Biblioteka Mockito posiada matchery dla kazdego typu wedle konwecji anyXXX - gdzie XXX to typ parametru.
         * Dla podstawiania jakiegokolwiek obiektu uzywamy metody any(). Jest to jedyny wyjatek dla tej konwencji */
        verify(mobileRobot, times(1)).move(anyDouble(), anyDouble());
        verify(mobileRobot, atMost(1)).move(anyDouble(), anyDouble());
        verify(mobileRobot, atLeastOnce()).move(anyDouble(), anyDouble());
    }

    /**
     * Poniewaz przetestowalismy juz wywolanie samej metody, mozemy przetestowac czy jej logika jest poprawna.
     * Metoda analogPositionChanged mnozy zadane parametry przez ANALOG_TO_RPM_RATIO (domyslnie = 100).
     * Mozemy, zatem, przetestowac poprawnosc tej logiki rezygnujac z matchera anyDouble oczekujac na wyjsciu
     * poprawnych wartosci po przeliczeniu 10.0 * 100 = 1000.0 itd.
     */
    @Test
    public void moveShouldCalculateMotorValuesGivenTheCalculationsInAnalogPositionChanged() {
        // Given
        double expectedXAxis = 10.0 * GamepadController.ANALOG_TO_RPM_RATIO;
        double expectedYAxis = 23.3 * GamepadController.ANALOG_TO_RPM_RATIO;

        // When
        gamepadController.analogPositionChanged(10.0, 23.3);

        // Then
        verify(mobileRobot, times(1)).move(expectedXAxis, expectedYAxis);
    }

}
