package by.andrlis.planmytrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/map")
public class MapController {

    @GetMapping
    public String showMap(Model model) {
        model.addAttribute("mapbox_key",
                "<MAP_BOX_KEY>");
        return "map";
    }
}
