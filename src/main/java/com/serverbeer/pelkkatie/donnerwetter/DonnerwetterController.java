package com.serverbeer.pelkkatie.donnerwetter;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class DonnerwetterController {

    //---documentation of the method below
    @RequestMapping(method=RequestMethod.GET, produces = "application/json")
    WeatherTemperature home(@RequestParam(value="loc", required=false) String loc) {
        //return "Hello World! from "+loc+ " - Donner";
        DonnerwetterDataFetcher ddf = new DonnerwetterDataFetcher();
        return ddf.fetchWeatherDataForLocation("Oulu", "Finland");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DonnerwetterController.class, args);
  }

}
