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
                "pk.eyJ1IjoiYW5kcmxpcyIsImEiOiJjbGtxMnpyNGEwZzh6M3Nwbmx3NHhodmZmIn0.5F81NRM7G7K-4IjyQWN_hw");
        return "map";
    }
}
