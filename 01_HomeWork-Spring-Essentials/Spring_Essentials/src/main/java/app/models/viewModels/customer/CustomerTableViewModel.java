package app.models.viewModels.customer;

import java.util.Date;

public class CustomerTableViewModel
{
    private Long id;
    private String name;
    private Date birthDate;
    private Boolean isYoungDriver;

    public CustomerTableViewModel()
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

    public Boolean getIsYoungDriver()
    {
        return this.isYoungDriver;
    }

    public void setIsYoungDriver(Boolean youngDriver)
    {
        isYoungDriver = youngDriver;
    }
}
