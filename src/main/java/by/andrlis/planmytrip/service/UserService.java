package by.andrlis.planmytrip.service;

import by.andrlis.planmytrip.dto.UserRegistrationDto;
import by.andrlis.planmytrip.entity.User;
import by.andrlis.planmytrip.mapper.UserRegistrationDtoMapper;
import by.andrlis.planmytrip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerNewUser(UserRegistrationDto userRegistrationDto){
        User user = UserRegistrationDtoMapper.registrationDtoToUser(userRegistrationDto);
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
