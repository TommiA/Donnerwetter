package com.serverbeer.pelkkatie.donnerwetter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={DonnerwetterDataFetcher.class})
public class DonnerwetterDataFetcherTests {

	@Autowired
	private DonnerwetterDataFetcher dDF;

	@Test
	public void contextLoads() throws Exception {
		assertThat(dDF).isNotNull();
	}

	@Test
	public void testDataFetchingForRealCity() throws Exception {
		WeatherTemperature wT = dDF.fetchWeatherDataForLocation("Joroinen", "Finland");
		assertThat(wT).isNotNull();
		assertThat(wT.getValue()).isNotNull();
		assertThat(wT.getUnit()).isEqualTo("celcius");
		assertThat(wT.getCity()).isEqualTo("Joroinen");
		assertThat(wT.getCountry()).isEqualTo("FI");
	}

	@Test
	public void testDataFetchingForNonExistingCityFails() throws Exception {
		Throwable thrown = catchThrowable(() -> dDF.fetchWeatherDataForLocation("Somewhere", "Somewhere"));
		assertThat(thrown).isInstanceOf(java.io.FileNotFoundException.class);
	}
}
