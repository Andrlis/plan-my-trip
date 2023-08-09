package by.andrlis.planmytrip.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationCreationDto {

    private String name;
    private String description;
    private String latitude;
    private String longitude;
    private String category;
    private String newCategory;
    private String country;
    private String newCountry;
    private String city;
    private String newCity;
}