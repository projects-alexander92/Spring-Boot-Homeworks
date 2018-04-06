package app.models.bindingModels.customers;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CustomerEditBindingModel
{
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private boolean isYoungDriver;

    public boolean isYoungDriver()
    {
        return this.isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver)
    {
        isYoungDriver = youngDriver;
    }

    public CustomerEditBindingModel()
    {
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Date getBirthDate()
    {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }
}
