## Installation

This app is built with "Spring Boot 3.0.6", the deployment steps below assume you have Docker installed on your local machine:

1. Clone this repo into your local machine
2. `cd` into the project folder and run
```
docker-compose up
```
to build and start the needed containers

3. To access the site, visit: http://localhost:8080/challenge/tower on your browser.
4. To access Swagger, visit: http://localhost:8080/swagger-ui/index.html

## Notes:
1. I relied on Spring boot to perform Dependency Injection.
2. There is case for example where if the `network operator` has `&` in it, since `&` considered as separator for parameters in the url, so it will require to convert `&` to its UTF-8 encoding.
3. The above point is only will happen on browser since the separation will not be clear, but in case we use Swagger or Postman to consume the API, this will not be a much of an issue.

## API Documentation
**Get the list of Towers**

```http://localhost:8080/challenge/tower```

**Get the list of Towers depending on filter parameters**

Here is the possible value of `param`: `networkoperator`, `technology`, `towerid`, `towertype`

```http://localhost:8080/challenge/tower?param1=value1&param2=value2```

E.g.:

**List of Towers where the network operator is verizon and technology of the towers is 2g**
```http://localhost:8080/challenge/tower?networkoperator=verizon&technology=2g```
