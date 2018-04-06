package app.repositories;

import app.entities.orm.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest
{
    @Autowired
    private UserRepository userRepository;
    private static final String TEST_USERNAME = "pesho";
    private static final int EXPECTED_LIST_SIZE = 2;
    private User pesho;
    private User gosho;

    @Before
    public void initObjects()
    {
        this.pesho = new User();
        this.pesho.setUsername(TEST_USERNAME);
        this.gosho = new User();
        this.userRepository.save(pesho);
        this.userRepository.save(gosho);
    }

    @Test
    public void testFindByUsername()
    {
        User pesho = this.userRepository.findByUsername(TEST_USERNAME);
        Assert.assertTrue("names Mismatch", pesho.getUsername().equals(TEST_USERNAME));
    }

    @Test
    public void testGetAllBySize()
    {
        List<User> allBy = this.userRepository.getAllBy();
        Assert.assertTrue("list length mismatch", allBy.size() == EXPECTED_LIST_SIZE);
    }
}