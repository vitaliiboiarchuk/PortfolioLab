package pl.coderslab.charity.admin;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {
    private final pl.coderslab.charity.admin.User user;
    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.coderslab.charity.admin.User user) {
        super(username, password, authorities);
        this.user = user;
    }
    public pl.coderslab.charity.admin.User getUser() {return user;}

}
