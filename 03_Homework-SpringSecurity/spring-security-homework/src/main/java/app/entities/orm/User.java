package app.entities.orm;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Set<Role> authorities;

    public User()
    {
        this.authorities = new HashSet<>();
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public boolean isAccountNonExpired()
    {
        return this.isAccountNonExpired;
    }

    public boolean isAccountNonLocked()
    {
        return this.isAccountNonLocked;
    }

    public boolean isCredentialsNonExpired()
    {
        return this.isCredentialsNonExpired;
    }

    public boolean isEnabled()
    {
        return this.isEnabled;
    }

    public Set<Role> getAuthorities()
    {
        return this.authorities;
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired)
    {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked)
    {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired)
    {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled)
    {
        isEnabled = enabled;
    }

    public void setAuthorities(Set<Role> authorities)
    {
        this.authorities = authorities;
    }
}
