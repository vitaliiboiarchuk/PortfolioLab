package pl.coderslab.charity.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT distinct u FROM User u join FETCH u.roles WHERE :role member of u.roles")
    List<User> findByRolesName(Role role);

}
