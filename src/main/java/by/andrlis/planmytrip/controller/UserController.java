package by.andrlis.planmytrip.controller;

import by.andrlis.planmytrip.dto.UserAuthenticationDto;
import by.andrlis.planmytrip.dto.UserRegistrationDto;
import by.andrlis.planmytrip.entity.User;
import by.andrlis.planmytrip.entity.UserRole;
import by.andrlis.planmytrip.mapper.UserRegistrationDtoMapper;
import by.andrlis.planmytrip.repository.UserRepository;
import by.andrlis.planmytrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showAuthenticationPage(Model model) {
        model.addAttribute("authenticationdto", new UserAuthenticationDto());
        return "authentication";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("authenticationdto") @Valid UserAuthenticationDto userAuthenticationDto,
                        BindingResult bindingResult,
                        HttpSession session,
                        Model model) {
        if (bindingResult.hasErrors()) {
            setValidationErrors(bindingResult, model);
            model.addAttribute("authenticationdto", userAuthenticationDto);
            return "login";
        }

        Optional<User> byUsername = userService.findByUsername(userAuthenticationDto.getUsername());

        if (byUsername.isPresent()) {
            User user = byUsername.get();
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            model.addAttribute("errorLogin", "Wrong username or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("registrationdto", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registrationdto") @Valid UserRegistrationDto userRegistrationDto,
                           BindingResult bindingResult,
                           Model model) {
        Optional<User> existing = userService.findByUsername(userRegistrationDto.getUsername());
        if (existing.isPresent()) {
            bindingResult.rejectValue("username", null, "There is already an account registered with that username");
        }

        if (bindingResult.hasErrors()) {
            setValidationErrors(bindingResult, model);
            model.addAttribute("registrationdto", userRegistrationDto);
            return "registration";
        }

        userService.registerNewUser(userRegistrationDto);

        return "redirect:/user/login";
    }

    private void setValidationErrors(BindingResult bindingResult, Model model) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            model.addAttribute(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
