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
    String home(@RequestParam(value="loc", required=false) String loc) {
        //return "Hello World! from "+loc+ " - Donner";
        return "Here's some info "+fetchWeatherDataForLocation("Oulu", "Finland");
    }

    private String fetchWeatherDataForLocation(String city, String country){
      //Key for OpenWeatherMap - 793f96e50cb1be83c7f250c13bfcadf5
      //E.g. URL for London in XML format - http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=793f96e50cb1be83c7f250c13bfcadf5&mode=xml
      //Sample for London UK http://samples.openweathermap.org/data/2.5/weather?q=London&mode=xml&appid=b1b15e88fa797225412429c1c50c122a1
      Map<String, String> locCityCountry = new HashMap<String, String>();
      //Primitive input sanitation
      if (city==null){
        locCityCountry.put("city", "Oulu");
      }
      else {
        locCityCountry.put("city", city);
      }
      if (country==null){
        locCityCountry.put("country", "FI");
      }
      else {
        locCityCountry.put("country", country);
      }
      RestTemplate rTempl = new RestTemplate();
      //ResponseEntity<String> result = rTempl.getForEntity("http://api.openweathermap.org/data/2.5/weather?q={city},{country}&appid=793f96e50cb1be83c7f250c13bfcadf5&mode=xml", String.class, locCityCountry);
      WeatherTemperature result = rTempl.getForObject("http://api.openweathermap.org/data/2.5/weather?q={city},{country}&appid=793f96e50cb1be83c7f250c13bfcadf5&mode=xml", WeatherTemperature.class, locCityCountry);
      //return result.getBody();
      System.out.print(result.getTemperature());
      return result.getTemperature();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DonnerwetterController.class, args);
  }

}
