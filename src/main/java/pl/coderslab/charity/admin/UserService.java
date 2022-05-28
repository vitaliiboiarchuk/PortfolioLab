package pl.coderslab.charity.admin;

public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);

    void saveAdmin(User user);
}
