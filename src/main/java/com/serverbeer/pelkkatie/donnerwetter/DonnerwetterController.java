package com.serverbeer.pelkkatie.donnerwetter;

import java.util.HashMap;
import java.util.Map;
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

    @RequestMapping(method=RequestMethod.GET, produces = "application/json")
    WeatherTemperature home(@RequestParam(value="city", required=false) String city,
                            @RequestParam(value="country", required=false) String country) {
        DonnerwetterDataFetcher ddf = new DonnerwetterDataFetcher();
        return ddf.fetchWeatherDataForLocation(city, "Finland");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DonnerwetterController.class, args);
  }

}
