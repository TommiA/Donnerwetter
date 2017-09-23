package com.serverbeer.pelkkatie.donnerwetter;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//OpenWeatherMap root element
@XmlRootElement(name="current")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeatherTemperature implements Serializable {
  private long id;
  @XmlElement
  private String temperature;

  public WeatherTemperature(){
    super();
  }

  public WeatherTemperature(String temperature) {
    super();
    this.temperature = temperature;
  }


  public void setTemperature(String temperature){
    System.out.print("Temperature being set as "+temperature);
      this.temperature = temperature;
  }

  public String getTemperature(){
    return this.temperature;
  }
}
