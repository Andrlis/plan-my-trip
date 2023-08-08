package by.andrlis.planmytrip.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserRegistrationDto {
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "The password short or contain invalid characters")
    private String password;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "The email field does not contain a valid email address")
    private String email;

}
