package app.models.viewModels.cars;

import app.models.viewModels.parts.PartDetailsForCar;

import java.util.List;

public class CarDetailsViewModel
{
    private String make;
    private String model;
    private Long travelledDistance;
    private List<PartDetailsForCar> parts;

    public CarDetailsViewModel()
    {
    }

    public String getMake()
    {
        return this.make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public String getModel()
    {
        return this.model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public Long getTravelledDistance()
    {
        return this.travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance)
    {
        this.travelledDistance = travelledDistance;
    }

    public List<PartDetailsForCar> getParts()
    {
        return this.parts;
    }

    public void setParts(List<PartDetailsForCar> parts)
    {
        this.parts = parts;
    }
}
