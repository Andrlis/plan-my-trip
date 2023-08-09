package by.andrlis.planmytrip.controller;

import by.andrlis.planmytrip.dto.LocationCreationDto;
import by.andrlis.planmytrip.entity.City;
import by.andrlis.planmytrip.entity.Country;
import by.andrlis.planmytrip.entity.LocationCategory;
import by.andrlis.planmytrip.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/add")
    public String showAddLocationPage(Model model){
        List<LocationCategory> existingCategories = locationService.getAllLocationCategories();
        List<Country> existingCountries = locationService.getAllCountries();
        List<City> existingCities = locationService.getAllCities();


        model.addAttribute("location", new LocationCreationDto());
        model.addAttribute("categoryList", existingCategories);
        model.addAttribute("countryList", existingCountries);
        model.addAttribute("cityList", existingCities);

        return "add-location";
    }

    @PostMapping("/add")
    public String addLocation(LocationCreationDto locationCreationDto){
        locationService.addLocation(locationCreationDto);
        return "redirect:/";
    }
}
