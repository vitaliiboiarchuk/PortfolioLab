package pl.coderslab.charity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.admin.CurrentUser;
import pl.coderslab.charity.admin.User;
import pl.coderslab.charity.admin.UserRepository;
import pl.coderslab.charity.donation.DonationRepository;
import pl.coderslab.charity.institution.InstitutionRepository;


@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;


    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, UserRepository userRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
    }


    @RequestMapping("/")
    public String homeAction(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        if (currentUser != null) {
            User entityUser = currentUser.getUser();
            model.addAttribute("user", userRepository.getById(entityUser.getId()));
        }
        model.addAttribute("institutions",institutionRepository.findAll());
        model.addAttribute("donations",donationRepository.countDonations());
        model.addAttribute("bags",donationRepository.countQuantity());
        return "index";
    }


}
