package by.andrlis.planmytrip.service;

import by.andrlis.planmytrip.dto.LocationContentDto;
import by.andrlis.planmytrip.dto.LocationCreationDto;
import by.andrlis.planmytrip.entity.*;
import by.andrlis.planmytrip.repository.*;
import by.andrlis.planmytrip.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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

    @Autowired
    private LocationContentRepository locationContentRepository;

    @Autowired
    private ContentSourceRepository contentSourceRepository;

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

    @Transactional
    public Optional<Location> getLocation(Long id) {
        return locationRepository.findById(id);
    }

    @Transactional
    public void addLocationContent(Long locationId, LocationContentDto contentDto) {
        Optional<Location> locationById = locationRepository.findById(locationId);
        if (locationById.isEmpty()) {
            throw new RuntimeException("Attempt to add resource for non-existent location");
        }

        Location location = locationById.get();

        ContentSource contentSource = ContentSource.builder()
                .type(ContentSourceType.valueOf(contentDto.getType().toUpperCase()))
                .build();
        ;
        switch (contentSource.getType()) {
            case TEXT:
                contentSource.setText(contentDto.getText());
            case URL:
                contentSource.setUrl(contentDto.getUrl());
            case IMAGE:
                try {
                    contentSource.setImage(ImageUtil.compressImage(contentDto.getFile().getBytes()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }

        Integer orderNumber = locationContentRepository.countOfLocationContentByLocationAndType(location, contentSource.getType()) + 1;

        LocationContent locationContent = LocationContent.builder()
                .location(location)
                .contentSource(contentSource)
                .orderNumber(orderNumber)
                .build();
        location.getLocationContentItems().add(locationContent);

        contentSourceRepository.save(contentSource);
        locationContentRepository.save(locationContent);
        locationRepository.save(location);
    }
}
