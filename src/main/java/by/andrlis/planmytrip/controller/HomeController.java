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
@RequestMapping("/")
public class HomeController {

    @Autowired
    LocationService locationService;

    @GetMapping("/")
    public String showHome(Model model) {
        List<Location> topLocations = locationService.getMostPopularLocations();
        model.addAttribute("topLocationList", topLocations);
        return "home";
    }

    @GetMapping("/about")
    public String showAbout() {
        return "about";
    }
}
