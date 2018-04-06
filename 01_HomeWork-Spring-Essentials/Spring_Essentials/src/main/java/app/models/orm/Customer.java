package app.models.orm;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "customers")
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Date birthDate;
    @Column
    private Boolean isYoungDriver;

    public Customer()
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

    public Date getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    public Boolean getIsYoungDriver()
    {
        return this.isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver)
    {
        isYoungDriver = youngDriver;
    }
}
