package app.entities.orm;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity(name = "roles")
public class Role implements GrantedAuthority
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String authority;

    @Override
    public String getAuthority()
    {
        return this.authority;
    }

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setAuthority(String authority)
    {
        this.authority = authority;
    }
}
