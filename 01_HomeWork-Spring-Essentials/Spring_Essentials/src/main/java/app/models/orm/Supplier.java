package app.models.orm;

import javax.persistence.*;
import java.util.List;

@Entity(name = "suppliers")
public class Supplier
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name ="isImporter")
    private Boolean isImporter;
    @OneToMany(mappedBy = "supplier")
    private List<Part> parts;
    public Supplier()
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

    public Boolean getIsImporter()
    {
        return isImporter;
    }

    public void setIsImporter(Boolean importer)
    {
        isImporter = importer;
    }

    public List<Part> getParts()
    {
        return parts;
    }

    public void setParts(List<Part> parts)
    {
        this.parts = parts;
    }
}
