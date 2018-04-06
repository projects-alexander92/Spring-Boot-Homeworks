package app.models.orm;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity(name = "cars")
public class Car
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private Long travelledDistance;
    @ManyToMany(mappedBy = "cars", fetch = FetchType.LAZY)
    private List<Part> parts;
    public Car()
    {
    }

    public Long getId()
    {
        return id;
    }

    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public Long getTravelledDistance()
    {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance)
    {
        this.travelledDistance = travelledDistance;
    }

    public List<Part> getParts()
    {
        return parts;
    }
}
