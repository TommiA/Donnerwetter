package com.serverbeer.pelkkatie.donnerwetter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.assertj.core.api.Assertions.*;

import org.springframework.boot.test.autoconfigure.json.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.json.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={DonnerwetterController.class}, webEnvironment=WebEnvironment.RANDOM_PORT)
//@SpringBootTest(classes={DonnerwetterController.class})
public class DonnerWetterControllerTests {

	@Autowired
	private DonnerwetterController dCO;

	@Autowired
	private TestRestTemplate restTemplate;

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

	@Test
	public void testGetWeatherTemperatureForDefaultCityREST() throws Exception {
		WeatherTemperature body = this.restTemplate.getForObject("/", WeatherTemperature.class);
		assertThat(body.getValue()).isNotNull();
		assertThat(body.getUnit()).isEqualTo("celcius");
		assertThat(body.getCity()).isEqualTo("Oulu");
		assertThat(body.getCountry()).isEqualTo("FI");
	}

	@Test
	public void testGetWeatherTemperatureForNHCityREST() throws Exception {
		WeatherTemperature body = this.restTemplate.getForObject("/?city=nashua&country=nh", WeatherTemperature.class);
		assertThat(body.getValue()).isNotNull();
		assertThat(body.getUnit()).isEqualTo("celcius");
		assertThat(body.getCity()).isEqualTo("Nashua");
		assertThat(body.getCountry()).isEqualTo("US");
	}

	@Test
	public void testGetWeatherTemperatureForNonExistingCityRESTFails() throws Exception {
		String body = this.restTemplate.getForObject("/?city=somewhere&country=somewhere", String.class);
		assertThat(body).contains("status\":500,\"error");
	}

}
