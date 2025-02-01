# Beer Rating

## Description
Application is used  to rate beers.

### Features
* Gets list of beers and saved ratings.
* Creates rating for selected beer.
* Updates already created ratings.

### Technologies used
* Java
* Spring Boot
* MongoDB
* Open Api
* Hibernate validation
* Lombok

## Installation

### Prerequisites
* Java 21
* Gradle
* MongoDB

### Setup
### Linux users
Clone source code from `GitHub` repository.
```
git clone git@github.com:TomcaK/beer_rating.git
```
Configure application with `application.properties` file.
```properties
beer-rating.mongo-db.db-connection-string=mongodb://localhost:27017/beer_rating
beer-rating.mongo-db.db-name=beer_rating
beer-rating.data-source.url=https://random-data-api.com/api/v2/beers
beer-rating.data-source.data-size=100
```
Build application with `Gradle`.
```
./gradlew build
```

Make sure that your MongoDB Database server is running. Check with prompt.
```
sudo systemctl status mongod
```
If the server is not running, start it.
```
sudo systemctl start mongod
```
Run the application.
```
java -jar build/libs/beer_rating-1.0.0.jar
```

## Open API Documentation
Documentation of all endpoints is accessible while the application is running on link below. 
* [REST API Documentation](http://localhost:8080/api/swagger-ui)
