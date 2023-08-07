package by.andrlis.planmytrip.mapper;

import by.andrlis.planmytrip.dto.UserRegistrationDto;
import by.andrlis.planmytrip.entity.User;
import by.andrlis.planmytrip.entity.UserRole;

public class UserRegistrationDtoMapper {

    public static final User registrationDtoToUser(UserRegistrationDto registrationDto){
        return User.builder()
                .name(registrationDto.getName())
                .username(registrationDto.getUsername())
                .email(registrationDto.getEmail())
                .password(registrationDto.getPassword())
                .role(UserRole.BASE)
                .build();
    }
}
