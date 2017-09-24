package com.serverbeer.pelkkatie.donnerwetter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;

public class DonnerwetterDataFetcher {
  //Key for OpenWeatherMap - 793f96e50cb1be83c7f250c13bfcadf5
  //E.g. URL for London in XML format - http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=793f96e50cb1be83c7f250c13bfcadf5&mode=xml
  //Sample for London UK http://samples.openweathermap.org/data/2.5/weather?q=London&mode=xml&appid=b1b15e88fa797225412429c1c50c122a1
  private String baseURLOpenWeatherData = "http://api.openweathermap.org/data/2.5/weather";
  private String aPIKeyForOpenWeatherMap = "793f96e50cb1be83c7f250c13bfcadf5";
  private String unitsOpenWeatherMap = "metric";
  private String dataFormatOpenWeatherMap = "xml";

  public DonnerwetterDataFetcher()  {
  }

public WeatherTemperature fetchWeatherDataForLocation(String city, String country){
  //Simple input value sanitation
  //OpenWeatherMap does location matching to the closest city if params provided
  WeatherTemperature rWT = null;
  if (city==null){
    city = "Oulu";
  }
  if (country==null){
    country = "FI";
  }

  try {
    StringBuilder sB = new StringBuilder(this.baseURLOpenWeatherData);
    sB.append("?q="+city+","+country);
    sB.append("&appid="+this.aPIKeyForOpenWeatherMap);
    sB.append("&mode="+this.dataFormatOpenWeatherMap);
    sB.append("&units="+this.unitsOpenWeatherMap);

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    //this parse seems to work with URLs too - wohoo
    Document document = builder.parse(sB.toString());
    document.getDocumentElement().normalize();

    XPath xPath = XPathFactory.newInstance().newXPath();
    String tempxPathExpression = "/current/temperature/@value";
    String cityxPathExpression = "/current/city/@name";
    String countryxPathExpression = "/current/city/country";
    String dateTimexPathExpression = "/current/lastupdate/@value";

    rWT = new WeatherTemperature();
    rWT.setValue(xPath.compile(tempxPathExpression).evaluate(document));
    rWT.setCity(xPath.compile(cityxPathExpression).evaluate(document));
    rWT.setCountry(xPath.compile(countryxPathExpression).evaluate(document));
    rWT.setDateTimeUpdated(xPath.compile(dateTimexPathExpression).evaluate(document));
    if(this.unitsOpenWeatherMap.equals("metric"))
    {
      rWT.setUnit("celcius");
    }

  } catch (Exception exp) {
    exp.printStackTrace();
  }
  return rWT;
}
}
