package app.models.viewModels.sales;

public class SaleTableViewModel
{
    private long id;
    private Double discount;
    private String carMake;
    private String customerName;

    public SaleTableViewModel()
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

    public Double getDiscount()
    {
        return this.discount;
    }

    public void setDiscount(Double discount)
    {
        this.discount = discount;
    }

    public String getCarMake()
    {
        return this.carMake;
    }

    public void setCarMake(String carMake)
    {
        this.carMake = carMake;
    }

    public String getCustomerName()
    {
        return this.customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }
}
