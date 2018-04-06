package app.repositories;

import app.entities.orm.Bike;
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
public class BikeRepositoryTest
{
    @Autowired
    private BikeRepository bikeRepository;
    private static final String BIKE_TEST_MODEL_NAME = "bike test model";

    @Before
    public void initObjects()
    {
        Bike bike = new Bike();
        bike.setGears(20);
        bike.setModel(BIKE_TEST_MODEL_NAME);
        Bike bike1 = new Bike();
        bike1.setGears(10);
        this.bikeRepository.save(bike);
        this.bikeRepository.save(bike1);
    }

    @Test
    public void testGetAllBikesByGearMoreThen10()
    {
        List<Bike> bikes = this.bikeRepository.getAllBikesByGearMoreThen10();

        Assert.assertTrue("bikes model is not the same", bikes.get(0).getModel().equals(BIKE_TEST_MODEL_NAME));
        Assert.assertTrue("wrong number of bikes " + bikes.size(), bikes.size() == 1);
    }

    @Test
    public void testIfAllBikesAreFetched()
    {
        List<Bike> bikes = this.bikeRepository.getAllBy();

        Assert.assertTrue("wrong number of bikes " + bikes.size(), bikes.size() == 2);
    }
}