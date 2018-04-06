package app.entities.dto.viewModels;

import app.entities.orm.enums.Magnitude;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class VirusTableViewModel
{
    private long id;
    private String name;
    private Magnitude magnitude;
    private Date releasedOn;

    public VirusTableViewModel()
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

    public Magnitude getMagnitude()
    {
        return this.magnitude;
    }

    public void setMagnitude(Magnitude magnitude)
    {
        this.magnitude = magnitude;
    }

    public Date getReleasedOn()
    {
        return this.releasedOn;
    }

    public void setReleasedOn(Date releasedOn)
    {
        this.releasedOn = releasedOn;
    }
}
