package app.controllers;

import app.entities.viewModels.BikeViewModel;
import app.errors.PageNotFoundException;
import app.services.interfaces.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BikeController
{


    private final BikeService bikeService;

    @Autowired
    public BikeController(BikeService bikeService)
    {
        this.bikeService = bikeService;
    }

    @GetMapping("/bikes/all")
    public String showBikeCardsView(Model model,@PageableDefault(size = 10) Pageable pageable)
    {
        Page<BikeViewModel> bikes = this.bikeService.findAll(pageable);
        model.addAttribute("bikes", bikes);
        return "bikes/all-bikes";
    }

    @GetMapping("/bikes/show/{id}")
    public String showBikeDetailsView(Model model, @PathVariable long id)
    {
        BikeViewModel bikeViewModel = this.bikeService.findById(id);
        model.addAttribute("bike", bikeViewModel);
        return "bikes/bike-show";
    }

    @ExceptionHandler(PageNotFoundException.class)
    public String showBikeNotFoundExceptionView()
    {
        return "errors/404";
    }
}
