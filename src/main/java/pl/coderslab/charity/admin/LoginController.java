package pl.coderslab.charity.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class LoginController {

    private final UserService userService;
    private final UserRepository userRepository;


    public LoginController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(@Valid User user, Model model) {
        model.addAttribute("user", user);
        return "login";
    }


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";

    }

    @PostMapping("/registration")
    public String registrationPost(@Valid User user, BindingResult bindingResult) {
        User userExists = userService.findByUserName(user.getUsername());
        if (userExists != null) {
            bindingResult.rejectValue("username", "error.user",
                    "Użytkownik z taką nazwą już istnieje");
        }

        if (user.getUsername().isEmpty()) {
            bindingResult.rejectValue("username", "error.user", "Pole nie może być puste!");
        }
        if (user.getPassword().isEmpty()) {
            bindingResult.rejectValue("password", "error.user", "Pole nie może być puste!");
        }

        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            userService.saveUser(user);

        }
        return "redirect:/login";
    }


    @GetMapping("/edit")
    public String settings(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (currentUser != null) {
            User entityUser = currentUser.getUser();
            model.addAttribute("user", userRepository.getById(entityUser.getId()));
            return "edit";
        } else {
            return "redirect:/403";
        }
    }

    @PostMapping("/edit")
    public String settingsPost(@Valid User user, BindingResult result) {

        User userExists = userService.findByUserName(user.getUsername());
        if (userExists != null) {
            result.rejectValue("username", "error.user",
                    "Użytkownik z taką nazwą już istnieje");
        }
        if (user.getUsername().isEmpty()) {
            result.rejectValue("username", "error.user", "Pole nie może być puste");
        }
        if (user.getPassword().isEmpty()) {
            result.rejectValue("password", "error.user", "Pole nie może być puste");
        }
        if (result.hasErrors()) {
            return "edit";
        } else {
            userService.saveUser(user);
            return "redirect:/";
        }
    }


}
