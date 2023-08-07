package by.andrlis.planmytrip.controller;

import by.andrlis.planmytrip.dto.UserAuthenticationDto;
import by.andrlis.planmytrip.dto.UserRegistrationDto;
import by.andrlis.planmytrip.entity.User;
import by.andrlis.planmytrip.entity.UserRole;
import by.andrlis.planmytrip.mapper.UserRegistrationDtoMapper;
import by.andrlis.planmytrip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showAuthenticationPage(Model model) {
        model.addAttribute("authenticationdto", new UserAuthenticationDto());
        return "authentication";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("registrationdto", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping("/register")
    public String reg(@ModelAttribute("newUser") @Valid UserRegistrationDto userRegistrationDto,
                      BindingResult bindingResult,
                      Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        User user = UserRegistrationDtoMapper.registrationDtoToUser(userRegistrationDto);
        userRepository.save(user);

        return "redirect:/user/login";
    }
}
