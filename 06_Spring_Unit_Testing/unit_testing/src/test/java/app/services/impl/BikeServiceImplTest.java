package app.services.impl;

import app.entities.orm.Bike;
import app.entities.viewModels.BikeViewModel;
import app.errors.BikeNotFoundException;
import app.repositories.BikeRepository;
import app.services.interfaces.BikeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BikeServiceImplTest
{
    private static final long VALID_ID = 1;
    private static final long INVALID_ID = -1212;
    private static final String MODEL = "BMX";
    private static final int EXPECTED_GEARS = 12;
    private Bike bike;
    @MockBean
    private BikeRepository bikeRepository;
    @Autowired
    private BikeService bikeService;

    @Before
    public void initObjects()
    {
        this.bike = new Bike();
        bike.setModel(MODEL);
        bike.setId(VALID_ID);
        bike.setGears(EXPECTED_GEARS);
        Mockito.when(this.bikeRepository.findOne(VALID_ID)).thenReturn(this.bike);
    }

    @Test
    public void testIfFindByIdMapsObjectsCorrectly()
    {
        BikeViewModel bikeViewModel = this.bikeService.findById(VALID_ID);
        Assert.assertTrue("Wrong model name", bikeViewModel.getModel().equals(MODEL));
        Assert.assertTrue("Wrong id", bikeViewModel.getId() == VALID_ID);
        Assert.assertTrue("Wrong gears count", bikeViewModel.getGears() == EXPECTED_GEARS);
    }

    @Test
    public void testIfFindByIdCallsRepositoryMethodFindOne()
    {
        this.bikeService.findById(VALID_ID);
        Mockito.verify(this.bikeRepository, Mockito.times(1)).findOne(VALID_ID);
    }

    @Test(expected = BikeNotFoundException.class)
    public void testFindByInvalidId()
    {
        this.bikeService.findById(INVALID_ID);
    }
}