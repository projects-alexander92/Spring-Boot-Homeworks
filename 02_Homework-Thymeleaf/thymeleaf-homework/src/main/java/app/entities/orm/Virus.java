package app.entities.orm;

import app.entities.orm.enums.Magnitude;
import app.entities.orm.enums.Mutation;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "viruses")
public class Virus
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(columnDefinition = "VARCHAR(100)")
    private String description;
    private String creator;
    @Size(max = 50)
    private String sideEffect;
    @Column
    private Boolean isDeadly;
    @Column
    private Boolean isCurable;
    @Column
    @Enumerated(EnumType.STRING)
    private Mutation mutation;
    @Column
    private Integer turnOverRate;
    private Integer hoursUntilTurn;
    @Column
    private Magnitude magnitude;
    @Column
    private Date releasedOn;
    @ManyToMany
    @JoinTable(name = "vairuses_capitals",
            joinColumns = @JoinColumn(name = "virus_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "capital_id", referencedColumnName = "id"))
    private List<Capital> capitals;

    public Virus()
    {
        this.capitals = new ArrayList<>();
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

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getSideEffect()
    {
        return this.sideEffect;
    }

    public void setSideEffect(String sideEffects)
    {
        this.sideEffect = sideEffects;
    }

    public String getCreator()
    {
        return this.creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public Boolean getIsDeadly()
    {
        return this.isDeadly;
    }

    public void setIsDeadly(Boolean deadly)
    {
        isDeadly = deadly;
    }

    public Boolean getIsCurable()
    {
        return this.isCurable;
    }

    public void setIsCurable(Boolean curable)
    {
        isCurable = curable;
    }

    public Mutation getMutation()
    {
        return this.mutation;
    }

    public void setMutation(Mutation mutation)
    {
        this.mutation = mutation;
    }

    public Integer getTurnOverRate()
    {
        return this.turnOverRate;
    }

    public void setTurnOverRate(Integer turnOverRate)
    {
        this.turnOverRate = turnOverRate;
    }

    public Integer getHoursUntilTurn()
    {
        return this.hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn)
    {
        this.hoursUntilTurn = hoursUntilTurn;
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

    public List<Capital> getCapitals()
    {
        return this.capitals;
    }

    public void setCapitals(List<Capital> capitals)
    {
        this.capitals = capitals;
    }
}
