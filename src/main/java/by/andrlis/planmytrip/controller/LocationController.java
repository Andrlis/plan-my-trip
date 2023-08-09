package by.andrlis.planmytrip.controller;

import by.andrlis.planmytrip.dto.LocationCreationDto;
import by.andrlis.planmytrip.entity.LocationCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/location")
public class LocationController {

    @GetMapping("/add")
    public String showAddLocationPage(Model model){
        model.addAttribute("location", new LocationCreationDto());
        return "add-location";
    }

    @PostMapping("/add")
    public String addLocation(LocationCreationDto locationCreationDto){
        System.out.println(locationCreationDto.getCategory());
        return "redirect:/";
    }
}
