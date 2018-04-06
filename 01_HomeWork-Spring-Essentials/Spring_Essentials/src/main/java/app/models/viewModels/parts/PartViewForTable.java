package app.models.viewModels.parts;

public class PartViewForTable
{
    private long id;
    private String name;
    private Double price;
    private int quantity;

    public PartViewForTable()
    {
    }

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Double getPrice()
    {
        return this.price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public int getQuantity()
    {
        return this.quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
