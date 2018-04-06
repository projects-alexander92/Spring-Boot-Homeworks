package app.controllers;

import app.services.interfaces.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController
{
    private final VirusService virusService;

    @Autowired
    public MapController(VirusService virusService)
    {
        this.virusService = virusService;
    }

    @GetMapping("map")
    public String showMapView(Model model)
    {
        String geoJson = this.virusService.getAsGeoJson();
        model.addAttribute("geoJson", geoJson);
        System.out.println(geoJson);
        return "maps/map-home-view";
    }
}
