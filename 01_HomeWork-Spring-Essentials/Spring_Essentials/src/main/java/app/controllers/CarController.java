package app.controllers;

import app.models.bindingModels.cars.CarBindingModel;
import app.models.viewModels.cars.CarDetailsViewModel;
import app.models.viewModels.cars.CarTableViewModel;
import app.services.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController
{

    private final CarService carService;

    @Autowired
    public CarController(CarService carService)
    {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String menageCarTableView(Model model, @RequestParam(name = "make", required = false) String make)
    {
        List<CarTableViewModel> cars;
        System.out.println(make);
        if (make == null || make.equals(""))
        {
            cars = this.carService.getAllCars();
        } else
        {
            cars = this.carService.getAllCarsByMake(make);
        }
        model.addAttribute("cars", cars);
        return "cars/car-home-view";
    }

    @GetMapping("/cars/details/{id}")
    public String showCarDetailsView(@PathVariable long id, Model model)
    {
        CarDetailsViewModel car = this.carService.getById(id);
        model.addAttribute("car", car);
        model.addAttribute("parts", car.getParts());
        return "cars/car-detailed-view";
    }

    @GetMapping("cars/add")
    public String showCarAddView(Model model)
    {
        model.addAttribute("car", new CarBindingModel());
        return "cars/car-add-view";
    }

    @PostMapping("cars/add")
    public String submitCarForm(@ModelAttribute CarBindingModel carBindingModel)
    {
        System.out.println(carBindingModel);
        this.carService.addCar(carBindingModel);
        return "redirect:/cars";
    }
}
