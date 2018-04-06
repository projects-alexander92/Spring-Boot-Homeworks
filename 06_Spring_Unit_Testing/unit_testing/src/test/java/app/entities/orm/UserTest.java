package app.entities.orm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest
{

    private User user;
    private static final String DEFAULT_USERNAME = "pesho";

    @Before
    public void initObjects()
    {
        this.user = new User();
        user.setUsername(DEFAULT_USERNAME);
    }

    @Test
    public void testUsernameGetter()
    {
        Assert.assertTrue("Getter not working correctly", this.user.getUsername().equals(DEFAULT_USERNAME));
    }
}