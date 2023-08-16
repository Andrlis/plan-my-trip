package by.andrlis.planmytrip.controller;

import by.andrlis.planmytrip.entity.Location;
import by.andrlis.planmytrip.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/map")
public class MapController {

    @Autowired
    LocationService locationService;

    @GetMapping
    public String showMap(Model model) {
//        List<Location> locations = locationService.getAllLocations();
//
//        model.addAttribute("map_objects", locations);
        model.addAttribute("mapbox_key",
                "<MAPBOX_KEY>");
        return "map";
    }
}
