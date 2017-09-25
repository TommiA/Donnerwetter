# Donnerwetter
This is a simple weather application server backend. Technologies used include Spring Boot and OpenWeatherMap as the actual data provider.

## Backend
The backend consists of the REST API provider and the weather data fetcher. All
the weather data is provided by the OpenWeatherMap API (see https://openweathermap.org/)
in the XML format. The backend employes the Spring Boot framework and while running it is located at http://localhost:8080 by default. If you have
domain name and other fancy networking stuff, you will be able to figure out how to change the URL and the port to your liking.

The backend supports only HTTP GET method and provides the temperature information as JSON string. The JSON string contains following parameters
- value - the actual temperature value
- unit - unit of the provided value. This backend only supports metric celcius at the moment
- city - the location of the temperature as understood by the OpenWeatherMap
- country - the country of the location as understood by the OpenWeatherMap
- dateTimeUpdated - the datetime string of the value updated at the OpenWeatherMap. The time is the local time in London UK

### To build
Just to build runnable java jar use  

`$ mvn package`  

to produce jar into the target folder. The appropriate server infrastructure is included.
### To run
To run see execute  

`$ jar tvf target/[filename].jar`  

To both build and run use  

`$ mvn spring-boot:run`
### To use
#### URL pattern supported
Provided that the backend starts as supposed and it is available at [http://localhost:8080] the following URL pattern is supported  
`http://localhost:8080?city={city name}&country={country name}`  

In where `{city name}` and `{country name}` are the city and country for the location for the temperature query.  

E.g.  
`http://localhost:8080?city=Oulu&country=FI`  

The backend defaults to the nearest city understood by the OpenWeatherMap data provider or to Oulu Finland if left empty. The U.S. states are recognized by their
abbreviation or full name, e.g.

E.g.  
`http://localhost:8080?city=Nashua&country=NH`  
#### Testing with curl or browser
Provided that the backend starts as supposed and it is available at [http://localhost:8080] direct your browser to the URL or
use curl. E.g.  
```
$curl -i localhost:8080

HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 24 Sep 2017 08:43:22 GMT

{"value":"11","unit":"celcius","city":"Oulu","country":"FI","dateTimeUpdated":"2017-09-24T08:20:00"}
```  
## Frontend
TODO
