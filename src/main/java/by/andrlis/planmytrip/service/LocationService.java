package by.andrlis.planmytrip.service;

import by.andrlis.planmytrip.dto.LocationCreationDto;
import by.andrlis.planmytrip.entity.*;
import by.andrlis.planmytrip.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private GeoPointRepository geoPointRepository;

    @Autowired
    private LocationCategoryRepository locationCategoryRepository;

    public List<LocationCategory> getAllLocationCategories() {
        return locationCategoryRepository.findAll();
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    public void addLocation(LocationCreationDto locationCreationDto) {

        LocationCategory locationCategory = null;
        if (locationCreationDto.getCategory().equals("Other")) {
            locationCategory = LocationCategory.builder()
                    .title(locationCreationDto.getNewCategory())
                    .description(locationCreationDto.getNewCategoryDescription())
                    .build();
            locationCategoryRepository.save(locationCategory);
        } else {
            Optional<LocationCategory> existingLocationCategory = locationCategoryRepository
                    .findByTitle(locationCreationDto.getCategory());
            if (existingLocationCategory.isPresent()) {
                locationCategory = existingLocationCategory.get();
            }
        }

        GeoPoint geoPoint;
        Optional<GeoPoint> existingGeoPoint = geoPointRepository
                .findByLatitudeAndLongitude(locationCreationDto.getLatitude(), locationCreationDto.getLongitude());

        if (existingGeoPoint.isPresent()) {
            geoPoint = existingGeoPoint.get();
        } else {
            geoPoint = GeoPoint.builder()
                    .longitude(locationCreationDto.getLongitude())
                    .latitude(locationCreationDto.getLatitude())
                    .build();
            geoPointRepository.save(geoPoint);
        }

        Country country = null;
        if (locationCreationDto.getCountry().equals("Other")) {
            country = Country.builder()
                    .name(locationCreationDto.getNewCountry())
                    .build();
            countryRepository.save(country);
        } else {
            Optional<Country> existingCountry = countryRepository
                    .findByName(locationCreationDto.getCountry());
            if (existingCountry.isPresent()) {
                country = existingCountry.get();
            }
        }

        City city = null;
        if (locationCreationDto.getCity().equals("Other")) {
            city = City.builder()
                    .name(locationCreationDto.getNewCity())
                    .build();
            cityRepository.save(city);
        } else {
            Optional<City> existingCity = cityRepository
                    .findByName(locationCreationDto.getCity());
            if (existingCity.isPresent()) {
                city = existingCity.get();
            }
        }

        Location location = Location.builder()
                .name(locationCreationDto.getName())
                .description(locationCreationDto.getDescription())
                .address(locationCreationDto.getAddress())
                .category(locationCategory)
                .geoPoint(geoPoint)
                .country(country)
                .city(city)
                .build();

        locationRepository.save(location);
    }
}
