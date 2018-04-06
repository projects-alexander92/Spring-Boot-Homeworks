package app.controllers;

import app.entities.viewModels.BikeViewModel;
import app.services.interfaces.BikeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(BikeController.class)
@ActiveProfiles("test")
public class BikeControllerTest
{
    private static final long VALID_BIKE_ID = 1;
    private static final String BIKE_MODEL_NAME = "BMX";
    private static final String VALID_BIKE_DETAILS_GET_URL = "/bikes/show/1";
    private static final String BIKE_DETAILS_VIEW = "bikes/bike-show";
    private static final int BIKE_GEARS = 0;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BikeService bikeService;

    @Before
    public void initObjects()
    {
        BikeViewModel bikeViewModel = new BikeViewModel();
        bikeViewModel.setModel(BIKE_MODEL_NAME);
        bikeViewModel.setId(VALID_BIKE_ID);
        bikeViewModel.setGears(BIKE_GEARS);
        Mockito.when(this.bikeService.findById(VALID_BIKE_ID)).thenReturn(bikeViewModel);
    }

    @Test
    public void validBikeIdShouldReturnValidBikePage() throws Exception
    {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get(VALID_BIKE_DETAILS_GET_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(BIKE_DETAILS_VIEW))
                .andExpect(MockMvcResultMatchers.model().attribute("bike", hasProperty("id", is(VALID_BIKE_ID))))
                .andExpect(MockMvcResultMatchers.model().attribute("bike", hasProperty("model", is(BIKE_MODEL_NAME))))
                .andExpect(MockMvcResultMatchers.model().attribute("bike", hasProperty("gears", is(BIKE_GEARS))));

        Mockito.verify(this.bikeService, Mockito.times(1)).findById(VALID_BIKE_ID);
    }
}