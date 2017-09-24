package com.serverbeer.pelkkatie.donnerwetter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={DonnerwetterController.class})
public class DonnerWetterControllerTests {

	@Autowired
	private DonnerwetterController dCO;

	@Test
	public void contextLoads() throws Exception {
		assertThat(dCO).isNotNull();
	}

  @Test
  public void testGetWeatherTemperatureForRealCity() throws Exception {
    WeatherTemperature wT = dCO.getWeatherTemperature("Joroinen", "FI");
    assertThat(wT).isNotNull();
    assertThat(wT.getValue()).isNotNull();
    assertThat(wT.getUnit()).isEqualTo("celcius");
    assertThat(wT.getCity()).isEqualTo("Joroinen");
    assertThat(wT.getCountry()).isEqualTo("FI");
  }

	@Test
	public void testGetWeatherTemperatureForNonExistingCityFails() throws Exception {
		Throwable thrown = catchThrowable(() -> dCO.getWeatherTemperature("Somewhere", "Somewhere"));
		assertThat(thrown).isInstanceOf(java.io.FileNotFoundException.class);
	}

}
