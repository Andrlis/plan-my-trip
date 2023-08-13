package by.andrlis.planmytrip.service;

import by.andrlis.planmytrip.dto.LocationCreationDto;
import by.andrlis.planmytrip.entity.*;
import by.andrlis.planmytrip.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(LocationService.class);

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

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Page<Location> getLocationsPageable(Pageable pageable, String categoryName, String countryName, String cityName) {

        LocationCategory category = locationCategoryRepository.findByTitle(categoryName).orElse(null);
        Country country = countryRepository.findByName(countryName).orElse(null);
        City city = cityRepository.findByName(cityName).orElse(null);


        return locationRepository.findPageableLocationsByCategoryAndCountryAndCity(category, country, city, pageable);
    }

    public void addLocation(LocationCreationDto locationCreationDto) {

        LOGGER.debug("Creation of new location started");

        LocationCategory locationCategory = null;
        if (locationCreationDto.getCategory().equals("Other")) {
            locationCategory = LocationCategory.builder()
                    .title(locationCreationDto.getNewCategory())
                    .description(locationCreationDto.getNewCategoryDescription())
                    .build();
            LOGGER.debug(String.format("Create new location category with title: %s", locationCategory.getTitle()));
            locationCategoryRepository.save(locationCategory);
        } else {
            LOGGER.debug(String.format("Search for category by title: %s", locationCreationDto.getCategory()));
            Optional<LocationCategory> existingLocationCategory = locationCategoryRepository
                    .findByTitle(locationCreationDto.getCategory());
            if (existingLocationCategory.isPresent()) {
                locationCategory = existingLocationCategory.get();
                LOGGER.debug(String.format("Associate location with category: id - %d, title - %s", locationCategory.getId(), locationCategory.getTitle()));
            }
        }

        GeoPoint geoPoint;
        Optional<GeoPoint> existingGeoPoint = geoPointRepository
                .findByLatitudeAndLongitude(locationCreationDto.getLatitude(), locationCreationDto.getLongitude());

        if (existingGeoPoint.isPresent()) {
            geoPoint = existingGeoPoint.get();
            LOGGER.debug(String.format("GeoPoint with provided longitude %s and latitude %s exists. Associate location with GeoPoint [%d]",
                    geoPoint.getLongitude(), geoPoint.getLatitude(), geoPoint.getId()));
        } else {
            geoPoint = GeoPoint.builder()
                    .longitude(locationCreationDto.getLongitude())
                    .latitude(locationCreationDto.getLatitude())
                    .build();
            LOGGER.debug(String.format("Create new GeoPoint: longitude - %s, latitude - %s", geoPoint.getLongitude(), geoPoint.getLatitude()));
            geoPointRepository.save(geoPoint);
        }

        Country country = null;
        if (locationCreationDto.getCountry().equals("Other")) {
            country = Country.builder()
                    .name(locationCreationDto.getNewCountry())
                    .build();
            LOGGER.debug(String.format("Create new country with name: %s", country.getName()));
            countryRepository.save(country);
        } else {
            LOGGER.debug(String.format("Search for country by name: %s", locationCreationDto.getCountry()));
            Optional<Country> existingCountry = countryRepository
                    .findByName(locationCreationDto.getCountry());
            if (existingCountry.isPresent()) {
                country = existingCountry.get();
                LOGGER.debug(String.format("Associate location with country: id - %d, name - %s", country.getId(), country.getName()));
            }
        }

        City city = null;
        if (locationCreationDto.getCity().equals("Other")) {
            city = City.builder()
                    .name(locationCreationDto.getNewCity())
                    .country(country)
                    .build();
            LOGGER.debug(String.format("Create new city with name: %s", city.getName()));
            cityRepository.save(city);
        } else {
            LOGGER.debug(String.format("Search for city by name: %s", locationCreationDto.getCity()));
            Optional<City> existingCity = cityRepository
                    .findByName(locationCreationDto.getCity());
            if (existingCity.isPresent()) {
                city = existingCity.get();
                LOGGER.debug(String.format("Associate location with city: id - %d, name - %s", city.getId(), city.getName()));
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
        LOGGER.debug(String.format("New location was created: name - %s", location.getName()));
    }

    public Optional<Location> getLocation(Long id) {
        return locationRepository.findById(id);
    }
}
