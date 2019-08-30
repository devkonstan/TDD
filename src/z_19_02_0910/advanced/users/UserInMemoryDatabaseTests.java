package z_19_02_0910.advanced.users;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

public class UserInMemoryDatabaseTests {
    private UserInMemoryDatabase database;

    private User user = new User("Ferdek", "1234", "ferdinando@gmail.com", 52, Sex.MALE);
    private User user2 = new User("Marian", "1111", "mariano@gmail.com", 54, Sex.MALE);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        database = new UserInMemoryDatabase();
    }

    @Test
    public void addingNewUserShouldGiveTotalNumberOf1Users() {
        //When
        database.add(user);
        //Then
        assertEquals(1, database.getUsersCount());
        assertNotEquals(2, database.getUsersCount()); //to sie nie spelni po dodaniu 1 uzytkownika
        assertFalse(database.getUsersCount() != 1); //to rowniez, gdyz liczba bedzie wynosila 1
    }

    @Test
    public void addingNewUserShouldGiveTotalNumberOfUsersDifferentThan2() {
        //When
        database.add(user);
        //Then
        assertNotEquals(2, database.getUsersCount());
    }

    @Test(expected = InvalidStateException.class)
    public void adding2TimesTheSameNewUserShouldThrowException() {
        //When
        database.add(user);
        database.add(user);
    }

    @Test
    public void addingNullUserShouldGiveTotalNumberOf0Users() {
        //When
        database.add(null);
        //Then
        assertEquals(0, database.getUsersCount());
    }

    @Test
    public void gettingByIDShouldGiveProperUser() {
        //When
        database.add(user);
        //Then
        assertEquals(user, database.getById(user.getId()));
    }

    @Test
    public void gettingByIDShouldNotGiveDifferentUser() {
        //When
        database.add(user);
        //Then
        assertNotEquals(user2, database.getById(user.getId()));
    }

    @Test(expected = InvalidStateException.class)
    public void gettingByIDShouldThrowExceptionWhenUserNotFound() {
        //Then
        database.getById(user.getId());
    }

    @Test
    public void gettingByEmailShouldGiveProperUser() {
        //When
        database.add(user);
        //Then
        assertEquals(user, database.getByEmail(user.getEmail()).get());
    }

    @Test
    public void gettingByEmailShouldNotGiveDifferentUser() {
        //When
        database.add(user);
        //Then
        assertNotEquals(user2, database.getByEmail(user.getEmail()).get());
    }

    @Test
    public void gettingByEmailShouldGiveOptionalEmptyWhenUserNotFound() {
        //When
        database.add(user);
        //Then
        assertEquals(Optional.empty(), database.getByEmail(user2.getEmail()));
    }

    @Test
    public void getByIdShouldReturnGivenUserByItsId() {
        // Given
        database.add(user);

        // When
        User givenUser = database.getById(user.getId());

        // Then
        assertSame(user, givenUser); //givenUser == user
        assertEquals(user.getId(), givenUser.getId());
    }

    @Test(expected = InvalidStateException.class)
    public void ifThereisNoUserWithGivenIdExceptionShouldBeThrown() {
        // When
        User givenUser = database.getById(UUID.randomUUID());

        // Then
        assertNull(givenUser);
    }

    @Test
    public void inMemoryDatabaseShouldInitiallyBeEmpty() {
        // When
        int currentUsersCount = database.getUsersCount();

        // Then
        assertEquals(0, currentUsersCount);
        assertFalse(database.getUsersCount() != 0);

    }
}