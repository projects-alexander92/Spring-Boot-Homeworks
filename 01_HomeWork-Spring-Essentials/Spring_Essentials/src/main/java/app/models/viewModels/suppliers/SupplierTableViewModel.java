package app.models.viewModels.suppliers;

public class SupplierTableViewModel
{
    private long id;
    private String name;
    private int numberOfParts;

    public SupplierTableViewModel()
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

    public int getNumberOfParts()
    {
        return this.numberOfParts;
    }

    public void setNumberOfParts(int numberOfParts)
    {
        this.numberOfParts = numberOfParts;
    }
}
