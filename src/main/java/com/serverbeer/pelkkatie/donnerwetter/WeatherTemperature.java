package com.serverbeer.pelkkatie.donnerwetter;

public class WeatherTemperature {
  private long id;
  protected String value;
  protected String unit;
  protected String city;
  protected String country;
  protected long timefetched;

  public WeatherTemperature(){
    super();
  }

	/**
	* Returns value of id
	* @return
	*/
	public long getId() {
		return id;
	}

	/**
	* Sets new value of id
	* @param
	*/
	public void setId(long id) {
		this.id = id;
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
	public long getTimefetched() {
		return timefetched;
	}

	/**
	* Sets new value of timefetched
	* @param
	*/
	public void setTimefetched(long timefetched) {
		this.timefetched = timefetched;
	}
}
