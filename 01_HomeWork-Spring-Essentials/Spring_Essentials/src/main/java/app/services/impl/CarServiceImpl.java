package app.services.impl;

import app.models.bindingModels.cars.CarBindingModel;
import app.models.orm.Car;
import app.models.viewModels.cars.CarDetailsViewModel;
import app.models.viewModels.cars.CarTableViewModel;
import app.parser.ModelParser;
import app.repositories.CarRepository;
import app.services.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService
{
    private final CarRepository carRepository;
    private final ModelParser modelParser;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelParser modelParser)
    {
        this.carRepository = carRepository;
        this.modelParser = modelParser;
    }

    @Override
    public List<CarTableViewModel> getAllCars()
    {
        List<CarTableViewModel> cars = this.carRepository.getCarsSorted().stream().map(e -> this.modelParser.parse(e, CarTableViewModel.class)).collect(Collectors.toList());
        return cars;
    }

    @Override
    public List<CarTableViewModel> getAllCarsByMake(String make)
    {
        List<CarTableViewModel> cars = this.carRepository.getCarsSortedByMake(make).stream().map(e -> this.modelParser.parse(e, CarTableViewModel.class)).collect(Collectors.toList());
        return cars;
    }

    @Override
    public CarDetailsViewModel getById(long id)
    {
        Car one = this.carRepository.findOne(id);
        CarDetailsViewModel parse = this.modelParser.parse(one, CarDetailsViewModel.class);
        return parse;
    }

    @Override
    @Transactional
    public void addCar(CarBindingModel carBindingModel)
    {
        Car parse = this.modelParser.parse(carBindingModel, Car.class);
        this.carRepository.save(parse);
    }
}
