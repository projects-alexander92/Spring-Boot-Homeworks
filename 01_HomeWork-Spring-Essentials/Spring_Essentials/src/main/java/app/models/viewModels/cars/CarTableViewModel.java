package app.models.viewModels.cars;

import java.math.BigInteger;

public class CarTableViewModel
{
    private Long id;
    private String make;
    private String model;
    private long travelledDistance;

    public CarTableViewModel()
    {
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public long getTravelledDistance()
    {
        return this.travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance)
    {
        this.travelledDistance = travelledDistance;
    }
}
