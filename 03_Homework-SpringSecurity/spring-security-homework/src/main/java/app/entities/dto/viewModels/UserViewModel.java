package app.entities.dto.viewModels;

public class UserViewModel
{
    private long id;
    private String username;
    private boolean isEnabled;

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public boolean getIsEnabled()
    {
        return this.isEnabled;
    }

    public void setIsEnabled(boolean enabled)
    {
        isEnabled = enabled;
    }
}
