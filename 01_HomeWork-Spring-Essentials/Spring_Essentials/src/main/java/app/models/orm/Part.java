package app.models.orm;

import javax.persistence.*;
import java.util.List;

@Entity(name = "parts")
public class Part
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "parts_cars",
            joinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"))
    private List<Car> cars;

    public Part()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Supplier getSupplier()
    {
        return supplier;
    }

    public void setSupplier(Supplier supplier)
    {
        this.supplier = supplier;
    }

    public List<Car> getCars()
    {
        return cars;
    }

    public void addCar(Car car)
    {
        this.cars.add(car);
    }

    @Override
    public String toString()
    {
        return "Part{" +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", supplier=" + supplier +
                ", cars=" + cars +
                '}';
    }
}
