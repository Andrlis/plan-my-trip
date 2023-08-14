package by.andrlis.planmytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationContentDto {
    private String type;
    private String url;
    private String text;
    private byte[] image;
}
