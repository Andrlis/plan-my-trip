package by.andrlis.planmytrip.controller;

import by.andrlis.planmytrip.entity.User;
import by.andrlis.planmytrip.entity.UserRole;
import by.andrlis.planmytrip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

}
