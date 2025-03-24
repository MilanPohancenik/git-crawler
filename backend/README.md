# Read Me First


# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.4/gradle-plugin)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.4/reference/web/servlet.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

## Building and running the project
To be able to build and run this project, you only need a JDK 21+ installed. The rest is done via the gradle
wrapper, which downloads gradle and builds the project.

### Commands
To build the project, just run
`gradle clean build`

To start the project, you don't need any docker images etc. Just run `gradle bootRun -Dspring.profiles.active=local` or even with building it before like
`gradle clean build bootRun -Dspring.profiles.active=local`. The profile currently doesn't do anything, but it's there to illustrate the possibility :)

### Using personal github token
You can  pass in your github personal user token via env variable `export GIT_HUB_AUTHORIZATION=your-personal-token` before running the app. This will automatically
activate the authorization an run with higher rate limiters. Please note, that this app uses the search API, which may use different rate limiting
than the rest and therefore can run into 500 error codes on the backend. If you see any logs regarding the rate limiting, 
then lower the amount of fetched records in the requests.

### Application url
The application starts by default on [this url](http://localhost:8080/git-crawler)

## APIs and Swagger UI
The applicaiton This application provides a [Swagger UI](http://localhost:8080/git-crawler/swagger-ui.html), which si divided into groups "actuator" and "github search". 