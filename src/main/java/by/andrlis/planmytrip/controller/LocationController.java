package by.andrlis.planmytrip.controller;

import by.andrlis.planmytrip.dto.LocationCreationDto;
import by.andrlis.planmytrip.entity.*;
import by.andrlis.planmytrip.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/location")
public class LocationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private LocationService locationService;

    @GetMapping("/add")
    public String showAddLocationPage(Model model) {

        LocationCreationDto locationCreationDto = new LocationCreationDto();
        locationCreationDto.setExistingCategories(locationService.getAllLocationCategories());
        locationCreationDto.setExistingCountries(locationService.getAllCountries());

        model.addAttribute("location", locationCreationDto);

        return "add-location";
    }

    @PostMapping("/add")
    public String addLocation(LocationCreationDto locationCreationDto) {
        locationService.addLocation(locationCreationDto);
        return "redirect:/location/list";
    }

    @RequestMapping(value = "/add", params = {"addResource"})
    public String addRow(final LocationCreationDto locationCreationDto, Model model) {
        locationCreationDto.getResources().add(new LocationContent());
        model.addAttribute("location", locationCreationDto);
        return "add-location";
    }

    @GetMapping("/list")
    public String showLocationsList(Model model, @RequestParam(required = false) String category,
                                    @RequestParam(required = false) String country, @RequestParam(required = false) String city,
                                    @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        LOGGER.debug(String.format("Show locations filtered by params: category[%s], country[%s], city[%s]", category, country, city));

        List<LocationCategory> existingCategories = locationService.getAllLocationCategories();
        List<Country> existingCountries = locationService.getAllCountries();

        Pageable paging = PageRequest.of(page - 1, size);
        Page<Location> locationPage = locationService.getLocationsPageable(paging, category, country, city);
        List<Location> locations = locationPage.getContent();

        model.addAttribute("locationsList", locations);
        model.addAttribute("categories", existingCategories);
        model.addAttribute("countries", existingCountries);
        model.addAttribute("currentPage", locationPage.getNumber() + 1);
        model.addAttribute("totalItems", locationPage.getTotalElements());
        model.addAttribute("totalPages", locationPage.getTotalPages());
        model.addAttribute("pageSize", size);
        return "locations";
    }

    @GetMapping("/{id}/details")
    public String showLocationDetails(Model model, @PathVariable Long id) {
        LOGGER.debug(String.format("Show details for location with id %d", id));
        Optional<Location> requestedLocation = locationService.getLocation(id);
        if (requestedLocation.isPresent()) {
            model.addAttribute("location", requestedLocation.get());
            return "location-details";
        }
        return "location-details";
    }
}
