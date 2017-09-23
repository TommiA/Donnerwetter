package com.serverbeer.pelkkatie.donnerwetter;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;

public class DonnerwetterDataFetcher {

  private String aPIKeyForOpenWeatherMap = "793f96e50cb1be83c7f250c13bfcadf5";

  public DonnerwetterDataFetcher()  {
  }

private WeatherTemperature readXml(String xMLWeatherData) {
  //I hate XML :D
  WeatherTemperature wt = null;
  wt = new WeatherTemperature();
  String[] parts = xMLWeatherData.split("<temperature\\svalue=\"");
  if(parts.length==2){
      String[] tempparts = parts[1].split("\"");
      wt.setValue(tempparts[0]);
  }
  return wt;
}

public WeatherTemperature fetchWeatherDataForLocation(String city, String country){
  //Key for OpenWeatherMap - 793f96e50cb1be83c7f250c13bfcadf5
  //E.g. URL for London in XML format - http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=793f96e50cb1be83c7f250c13bfcadf5&mode=xml
  //Sample for London UK http://samples.openweathermap.org/data/2.5/weather?q=London&mode=xml&appid=b1b15e88fa797225412429c1c50c122a1
  Map<String, String> urlParams = new HashMap<String, String>();
  //Primitive input sanitation
  urlParams.put("apikey", aPIKeyForOpenWeatherMap);
  if (city==null){
    urlParams.put("city", "Oulu");
  }
  else {
    urlParams.put("city", city);
  }
  if (country==null){
    urlParams.put("country", "FI");
  }
  else {
    urlParams.put("country", country);
  }
  RestTemplate rTempl = new RestTemplate();
  ResponseEntity<String> result = rTempl.getForEntity("http://api.openweathermap.org/data/2.5/weather?q={city},{country}&appid={apikey}&mode=xml&units=metric", String.class, urlParams);
  return this.readXml(result.getBody());
}
}
