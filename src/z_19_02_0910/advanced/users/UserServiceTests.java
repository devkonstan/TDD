package z_19_02_0910.advanced.users;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    private UserInMemoryDatabase database; //mockujemy baze danych

    @InjectMocks
    private UserService userService; //nie mockujemy klasy, ktora testujemy

    @Before
    public void setUp() {
//        database = Mockito.mock(UserInMemoryDatabase.class);
        userService = new UserService(database);
    }

    //jesli uzytkownik nie istnieje, to moze byc dodany do bazy danych
    @Test
    public void ifUserDoesntExistHeCanBeRegistered()  {
        //given
        String email = "test@email.pl";
        when(database.getByEmail(email)).thenReturn(Optional.empty());

        //when
        userService.register("Jan", "123456", email, 20, Sex.FEMALE);

        //then
        verify(database, times(1)).add(any());
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void ifUserExistsHeCannotBeRegistered() {
        //given
        String email = "test2@email.pl";
        User user = new User("Jan", "123456", email, 20, Sex.FEMALE);
        when(database.getByEmail(email)).thenReturn(Optional.of(user)); //odnosi sie do powyzszego

        //when
        userService.register("Jan", "123456", email, 20, Sex.FEMALE);

        //then
        verify(database, times(1)).add(any());
    }

    @Test
    public void userDoesNotExist()  {

    }

    @Test
    public void passwordIsNotCorrect()  {

    }
}

