package z_19_02_0910.advanced.users;

import java.util.Optional;

public class UserService {
    private final UserInMemoryDatabase userDatabase;

    public UserService(UserInMemoryDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    // register
    public void register(String name, String password, String email, int age, Sex sex) {
        User user = new User(name, password, email, age, sex);

        if (userDatabase.getByEmail(email).equals(Optional.empty())) {
            userDatabase.add(user);
        } else throw new UserAlreadyExistsException("User already exists");
    }

    // login
    public void login(String email, String password) {
        User user = userDatabase.getByEmail(email).orElse(null);

        if (user == null || !user.getPassword().equals(password)) { //podane haslo nie zgadza sie
            throw new IllegalArgumentException("Invalid credentials");
        }
        if (password.equals(userDatabase.getByEmail(email).get().getPassword())) {
            System.out.println("Login succesfull");
        }

    }
}

