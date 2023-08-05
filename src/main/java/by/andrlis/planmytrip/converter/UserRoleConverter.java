package by.andrlis.planmytrip.converter;

import by.andrlis.planmytrip.entity.ContentSourceType;
import by.andrlis.planmytrip.entity.UserRole;

import javax.persistence.*;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        if (userRole == null) {
            return null;
        }
        return userRole.toString();
    }

    @Override
    public UserRole convertToEntityAttribute(String role) {
        if (role == null) {
            return null;
        }

        return Stream.of(UserRole.values())
                .filter(c -> c.toString().equals(role))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}