package com.serverbeer.pelkkatie.donnerwetter;

public class WeatherTemperature {
  protected String value;
  protected String unit;
  protected String city;
  protected String country;
  protected String dateTimeUpdated;

  public WeatherTemperature(){
    super();
  }

	/**
	* Returns value of value
	* @return
	*/
	public String getValue() {
		return value;
	}

	/**
	* Sets new value of value
	* @param
	*/
	public void setValue(String value) {
		this.value = value;
	}

	/**
	* Returns value of unit
	* @return
	*/
	public String getUnit() {
		return unit;
	}

	/**
	* Sets new value of unit
	* @param
	*/
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	* Returns value of city
	* @return
	*/
	public String getCity() {
		return city;
	}

	/**
	* Sets new value of city
	* @param
	*/
	public void setCity(String city) {
		this.city = city;
	}

	/**
	* Returns value of country
	* @return
	*/
	public String getCountry() {
		return country;
	}

	/**
	* Sets new value of country
	* @param
	*/
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	* Returns value of timefetched
	* @return
	*/
	public String getDateTimeUpdated() {
		return dateTimeUpdated;
	}

	/**
	* Sets new value of timefetched
	* @param
	*/
	public void setDateTimeUpdated(String dateTimeUpdated) {
		this.dateTimeUpdated = dateTimeUpdated;
	}
}
