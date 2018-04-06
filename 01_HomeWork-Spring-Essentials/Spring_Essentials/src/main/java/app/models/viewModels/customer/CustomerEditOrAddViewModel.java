package app.models.viewModels.customer;

import java.util.Date;

public class CustomerEditOrAddViewModel
{
    private String name;
    private Date birthDate;

    public CustomerEditOrAddViewModel()
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
