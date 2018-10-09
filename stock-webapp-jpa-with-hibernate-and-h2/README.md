# Simple Stock Application

This application provides the means of buying and selling stocks within the underlying data source
Initially some sample stocks and users are loaded via a sql script under resources

## Requirements

- Java 8
- Maven 3

### Design

The application is structured in layers, namely:
- *controller* : responsible for handling the requests and communicating with the layer service for providing a response
- *service* : responsible for executing the business logic
- *model* : the domain objects
- *repository* : abstracts the underlying datastore



### Build

    project$ mvn clean install

### Running

	project$ cd stock-webapp-jpa-with-hibernate-and-h2
    project$ mvn spring-boot:run

	The following CURL examples might come in handy while validating the application
	
	To buy a stock:
    curl -H "Content-Type: application/json" -X POST -d '{"userName":"User1","symbol":"GOOGLE","quanity":"10"}' http://localhost:8080/stocks/buy
	
	To sell a stock:
    curl -H "Content-Type: application/json" -X POST -d '{"userName":"User1","symbol":"GOOGLE","quanity":"10"}' http://localhost:8080/stocks/sell
	
	To view the stocks inventory:
    curl -H "Content-Type: application/json" http://localhost:8080/stocks
	
	To view the user portfolio:
    curl -H "Content-Type: application/json" http://localhost:8080/users