package by.andrlis.planmytrip.service;

import by.andrlis.planmytrip.dto.UserRegistrationDto;
import by.andrlis.planmytrip.entity.User;
import by.andrlis.planmytrip.mapper.UserRegistrationDtoMapper;
import by.andrlis.planmytrip.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public void registerNewUser(UserRegistrationDto userRegistrationDto) {
        User user = UserRegistrationDtoMapper.registrationDtoToUser(userRegistrationDto);

        LOGGER.debug(String.format("Create new user account: username - %s, name - %s, email - %s", user.getUsername(), user.getName(), user.getEmail()));

        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        LOGGER.debug(String.format("Search for user with username: %s", username));
        return userRepository.findByUsername(username);
    }
}
