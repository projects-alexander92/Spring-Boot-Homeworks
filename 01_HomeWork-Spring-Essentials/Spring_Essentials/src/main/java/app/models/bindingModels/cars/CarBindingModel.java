package app.models.bindingModels.cars;

public class CarBindingModel
{
    private String make;
    private String model;
    private Long travelledDistance;

    public CarBindingModel()
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
}
