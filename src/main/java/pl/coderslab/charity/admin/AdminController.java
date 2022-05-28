package pl.coderslab.charity.admin;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionRepository;


import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final InstitutionRepository institutionRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;


    public AdminController(InstitutionRepository institutionRepository, UserRepository userRepository, RoleRepository roleRepository, UserService userService) {
        this.institutionRepository = institutionRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @GetMapping("")
    public String admin(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (currentUser != null) {
            User entityUser = currentUser.getUser();
            model.addAttribute("name", userRepository.getById(entityUser.getId()));
        }
        return "admin/admin";
    }

    @GetMapping("/institutions")
    public String institutions(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (currentUser != null) {
            User entityUser = currentUser.getUser();
            model.addAttribute("name", userRepository.getById(entityUser.getId()));
        }
        model.addAttribute("institutions", institutionRepository.findAll());
        return "admin/institutions";
    }

    @GetMapping("/addInst")
    public String addInst(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (currentUser != null) {
            User entityUser = currentUser.getUser();
            model.addAttribute("name", userRepository.getById(entityUser.getId()));
        }
        model.addAttribute("institution", new Institution());
        return "admin/addInst";
    }

    @PostMapping("/addInst")
    public String addInstPost(@AuthenticationPrincipal CurrentUser currentUser, @Valid Institution institution, BindingResult bindingResult, Model model) {
        if (institution.getName().isEmpty()) {
            bindingResult.rejectValue("name", "error.user", "Pole nie może być puste!");
        }
        if (institution.getDescription().isEmpty()) {
            bindingResult.rejectValue("description", "error.user", "Pole nie może być puste!");
        }
        if (bindingResult.hasErrors()) {
            if (currentUser != null) {
                User entityUser = currentUser.getUser();
                model.addAttribute("name", userRepository.getById(entityUser.getId()));
            }
            return "admin/addInst";
        } else {
            institutionRepository.save(institution);
            return "redirect:/admin/institutions";
        }
    }

    @GetMapping("/editInst/{id}")
    public String editInst(@AuthenticationPrincipal CurrentUser currentUser, @PathVariable long id, Model model) {
        if (currentUser != null) {
            User entityUser = currentUser.getUser();
            model.addAttribute("name", userRepository.getById(entityUser.getId()));
        }
        model.addAttribute("institution",institutionRepository.getById(id));
        return "admin/editInst";
    }

    @PostMapping("/updateInst")
    public String update(@AuthenticationPrincipal CurrentUser currentUser, @Valid Institution institution, BindingResult bindingResult, Model model) {
        if (institution.getName().isEmpty()) {
            bindingResult.rejectValue("name", "error.user", "Pole nie może być puste!");
        }
        if (institution.getDescription().isEmpty()) {
            bindingResult.rejectValue("description", "error.user", "Pole nie może być puste!");
        }
        if (bindingResult.hasErrors()) {
            if (currentUser != null) {
                User entityUser = currentUser.getUser();
                model.addAttribute("name", userRepository.getById(entityUser.getId()));
            }
            return "admin/editInst";
        } else {
            institutionRepository.save(institution);
            return "redirect:/admin/institutions";
        }
    }

    @GetMapping("/deleteInst/{id}")
    public String delete(@PathVariable long id) {
        institutionRepository.deleteById(id);
        return "redirect:/admin/institutions";
    }

    @GetMapping("/admins")
    public String admins(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (currentUser != null) {
            User entityUser = currentUser.getUser();
            model.addAttribute("name", userRepository.getById(entityUser.getId()));
        }
        Role role = roleRepository.findByName("ROLE_ADMIN");
        model.addAttribute("admins", userRepository.findByRolesName(role));
        return "admin/admins";
    }

    @GetMapping("/addAdmin")
    public String addAdmin(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (currentUser != null) {
            User entityUser = currentUser.getUser();
            model.addAttribute("name", userRepository.getById(entityUser.getId()));
        }
        model.addAttribute("user",new User());
        return "admin/addAdmin";
    }

    @PostMapping("/addAdmin")
    public String addAdminPost(@AuthenticationPrincipal CurrentUser currentUser, @Valid User user, BindingResult bindingResult, Model model) {
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
            if (currentUser != null) {
                User entityUser = currentUser.getUser();
                model.addAttribute("name", userRepository.getById(entityUser.getId()));
            }
            return "admin/addAdmin";
        } else {
            userService.saveAdmin(user);
            return "redirect:/admin/admins";
        }


    }

    @GetMapping("/editAdmin/{id}")
    public String editAdmin(@AuthenticationPrincipal CurrentUser currentUser, @PathVariable long id, Model model) {
        if (currentUser != null) {
            User entityUser = currentUser.getUser();
            model.addAttribute("name", userRepository.getById(entityUser.getId()));
        }
        model.addAttribute("user",userRepository.getById(id));
        return "admin/editAdmin";
    }

    @PostMapping("/updateAdmin")
    public String updateAdmin(@AuthenticationPrincipal CurrentUser currentUser, @Valid User user, BindingResult result, Model model) {
        User userExists = userService.findByUserName(user.getUsername());
        if (userExists != null) {
            result.rejectValue("username", "error.user",
                    "Użytkownik z taką nazwą już istnieje");
        }
        if (user.getUsername().isEmpty()) {
            result.rejectValue("username","error.user","Pole nie może być puste");
        }
        if (user.getPassword().isEmpty()) {
            result.rejectValue("password","error.user","Pole nie może być puste");
        }
        if (result.hasErrors()) {
            if (currentUser != null) {
                User entityUser = currentUser.getUser();
                model.addAttribute("name", userRepository.getById(entityUser.getId()));
            }
            return "admin/editAdmin";
        } else {
            userService.saveAdmin(user);
            return "redirect:/admin/admins";
        }
    }

    @GetMapping("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/admins";
    }

    @GetMapping("/users")
    public String users(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (currentUser != null) {
            User entityUser = currentUser.getUser();
            model.addAttribute("name", userRepository.getById(entityUser.getId()));
        }
        Role role = roleRepository.findByName("ROLE_USER");
        model.addAttribute("users", userRepository.findByRolesName(role));
        return "admin/users";
    }

    @GetMapping("/addUser")
    public String addUser(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (currentUser != null) {
            User entityUser = currentUser.getUser();
            model.addAttribute("name", userRepository.getById(entityUser.getId()));
        }
        model.addAttribute("user",new User());
        return "admin/addUser";
    }

    @PostMapping("/addUser")
    public String addUserPost(@AuthenticationPrincipal CurrentUser currentUser, @Valid User user, BindingResult result, Model model) {
        User userExists = userService.findByUserName(user.getUsername());
        if (userExists != null) {
            result.rejectValue("username", "error.user",
                    "Użytkownik z taką nazwą już istnieje");
        }
        if (user.getUsername().isEmpty()) {
            result.rejectValue("username","error.user","Pole nie może być puste");
        }
        if (user.getPassword().isEmpty()) {
            result.rejectValue("password","error.user","Pole nie może być puste");
        }
        if (result.hasErrors()) {
            if (currentUser != null) {
                User entityUser = currentUser.getUser();
                model.addAttribute("name", userRepository.getById(entityUser.getId()));
            }
            return "admin/addUser";
        } else {
            userService.saveUser(user);
            return "redirect:/admin/users";
        }
    }

    @GetMapping("/editUser/{id}")
    public String editUser(@AuthenticationPrincipal CurrentUser currentUser, @PathVariable long id, Model model) {
        if (currentUser != null) {
            User entityUser = currentUser.getUser();
            model.addAttribute("name", userRepository.getById(entityUser.getId()));
        }
        model.addAttribute("user",userRepository.getById(id));
        return "admin/editUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(@AuthenticationPrincipal CurrentUser currentUser, @Valid User user, BindingResult result, Model model) {
        User userExists = userService.findByUserName(user.getUsername());
        if (userExists != null) {
            result.rejectValue("username", "error.user",
                    "Użytkownik z taką nazwą już istnieje");
        }
        if (user.getUsername().isEmpty()) {
            result.rejectValue("username","error.user","Pole nie może być puste");
        }
        if (user.getPassword().isEmpty()) {
            result.rejectValue("password","error.user","Pole nie może być puste");
        }
        if (result.hasErrors()) {
            if (currentUser != null) {
                User entityUser = currentUser.getUser();
                model.addAttribute("name", userRepository.getById(entityUser.getId()));
            }
            return "admin/editUser";
        } else {
            userService.saveUser(user);
            return "redirect:/admin/users";
        }
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }
}
