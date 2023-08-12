package by.andrlis.planmytrip.dto;

import by.andrlis.planmytrip.entity.City;
import by.andrlis.planmytrip.entity.Country;
import by.andrlis.planmytrip.entity.LocationCategory;
import by.andrlis.planmytrip.entity.LocationContent;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationCreationDto {

    private String name;
    private String description;
    private String address;
    private String latitude;
    private String longitude;
    private String category;
    private String newCategory;
    private String newCategoryDescription;
    private String country;
    private String newCountry;
    private String city;
    private String newCity;
    private List<LocationContent> resources;
    private List<LocationCategory> existingCategories;
    private List<Country> existingCountries;
}
