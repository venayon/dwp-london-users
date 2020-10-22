# dwp-london-users
* Spring Boot Rest Web API to fetch all London users in or around 50 miles

# Solution
* I have developed solution using Spring Boot & Java 9
* service will expose one rest api to "fetch all London users in or around 50 miles" The endpoints return will a JSON list of the relevant users

### Building and Running

To build the app run below command from project base dir:
mvn spring-boot:run
or
mvn clean install
java -jar target/dwp-digital-tech-test-0.0.1-SNAPSHOT.jar

http://localhost:8081/api/users/within-fifty-miles-london

After starting the application -  application should start successfully on port 8081 
Through the HTTP GET Method - http://localhost:8081/api/users/within-fifty-miles-london
this service will return "all London users in or around 50 miles" users list

