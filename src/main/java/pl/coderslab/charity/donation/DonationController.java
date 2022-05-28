package pl.coderslab.charity.donation;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.admin.CurrentUser;
import pl.coderslab.charity.admin.User;
import pl.coderslab.charity.admin.UserRepository;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.institution.InstitutionRepository;

import javax.validation.Valid;

@Controller
public class DonationController {

    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;


    public DonationController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository, DonationRepository donationRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;

        this.userRepository = userRepository;
    }

    @GetMapping("/donation")
    public String donation(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (currentUser != null) {
            User entityUser = currentUser.getUser();
            model.addAttribute("user", userRepository.getById(entityUser.getId()));
        }
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("donation", new Donation());
        return "donation";
    }

    @PostMapping("/donation")
    public String donationPost(@AuthenticationPrincipal CurrentUser currentUser, @Valid Donation donation, Model model) {
        if (currentUser != null) {
            User entityUser = currentUser.getUser();
            model.addAttribute("user", userRepository.getById(entityUser.getId()));
        }
        donationRepository.save(donation);
        return "formConfirmation";

    }

    @GetMapping("/myDonations")
    public String myDonations(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (currentUser == null) {
            return "403";
        }
        User entityUser = currentUser.getUser();
        model.addAttribute("donations", donationRepository.findAllByUserId(userRepository.getById(entityUser.getId())));
        model.addAttribute("user", userRepository.getById(entityUser.getId()));
        return "myDonations";
    }

    @GetMapping("/deleteMyDonation/{id}")
    public String deleteMyDonation(@AuthenticationPrincipal CurrentUser currentUser, @PathVariable long id) {
        if (currentUser == null) {
            return "403";
        }
        User entityUser = currentUser.getUser();
        if (entityUser.getId() != donationRepository.findById(id).get().getUser().getId()) {
            return "403";
        } else {
            donationRepository.deleteById(id);
            return "redirect:/myDonations";
        }
    }

}
