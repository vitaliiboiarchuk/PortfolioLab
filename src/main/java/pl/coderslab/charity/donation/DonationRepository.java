package pl.coderslab.charity.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.admin.User;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation,Long> {

    @Query("select count (all id) from Donation")
    long countDonations();

    @Query("select sum(quantity) from Donation")
    long countQuantity();

    @Query("select d from Donation d where d.user = ?1")
    List<Donation> findAllByUserId(User user);
}
