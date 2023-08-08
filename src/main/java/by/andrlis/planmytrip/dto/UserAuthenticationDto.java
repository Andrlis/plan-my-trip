package by.andrlis.planmytrip.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserAuthenticationDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
