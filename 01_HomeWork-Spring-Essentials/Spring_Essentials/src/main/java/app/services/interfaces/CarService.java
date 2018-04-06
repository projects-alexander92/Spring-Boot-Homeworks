package app.services.interfaces;

import app.models.bindingModels.cars.CarBindingModel;
import app.models.viewModels.cars.CarDetailsViewModel;
import app.models.viewModels.cars.CarTableViewModel;

import javax.transaction.Transactional;
import java.util.List;

public interface CarService
{
    List<CarTableViewModel> getAllCars();

    List<CarTableViewModel> getAllCarsByMake(String make);

    CarDetailsViewModel getById(long id);

    @Transactional
    void addCar(CarBindingModel carBindingModel);
}
