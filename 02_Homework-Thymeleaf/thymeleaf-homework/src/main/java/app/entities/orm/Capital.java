package app.entities.orm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "capitals")
public class Capital
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    @ManyToMany(mappedBy = "capitals")
    private List<Virus> viruses;

    public Capital()
    {
        this.viruses = new ArrayList<>();
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

    public Double getLatitude()
    {
        return this.latitude;
    }

    public void setLatitude(Double latitude)
    {
        this.latitude = latitude;
    }

    public Double getLongitude()
    {
        return this.longitude;
    }

    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
    }

    public List<Virus> getViruses()
    {
        return this.viruses;
    }

    public void setViruses(List<Virus> viruses)
    {
        this.viruses = viruses;
    }
}
