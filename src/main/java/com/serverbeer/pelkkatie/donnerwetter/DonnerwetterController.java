package com.serverbeer.pelkkatie.donnerwetter;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class DonnerwetterController {

    private DonnerwetterDataFetcher ddf;

    @CrossOrigin
    @RequestMapping(method=RequestMethod.GET, produces = "application/json")
    WeatherTemperature getWeatherTemperature(@RequestParam(value="city", required=false) String city,
                            @RequestParam(value="country", required=false) String country) throws Exception {
        ddf = new DonnerwetterDataFetcher();
        return ddf.fetchWeatherDataForLocation(city, country);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DonnerwetterController.class, args);
  }

}
