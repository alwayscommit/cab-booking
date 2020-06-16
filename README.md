# cab-booking
An assignment to have the basic functionalities of a cab booking application using Spring, Hibernate, and a bit of frontend...  
------------------------------------------------------------------------------------------------------------------------------

Prerequisites: Maven, JDK 1.8, MySQL Database
Ensure that you have MySQL running locally on port 3306 and the datbase user exists with access rights to cab_service_db

These properties can be configured in application.properties of "cab-booking-service" project
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/cab_service_db
spring.datasource.username=aakash
spring.datasource.password=aakash

Steps to run this application

1. Clone the repository
2. To run the service, Run "mvn spring-boot:run" command inside "cab-booking-service" directory using a command prompt.
   - The service will start on port 8080
3. To run the client, Open another console and run the same command "mvn spring-boot:run" inside "cab-booking-client" directory.
   - The client will start on port 8081
4. Run the SQL commands in "cab-booking-service/sql commands.txt" to insert customers, drivers and cars.
5. Open web browser and access http://localhost:8081/booking 
6. You can use the "cab-booking-service/test-data.txt" file to test the application

--- Points to be noted. ---
1. The user's default location has been made to point at 19.2309696,72.9824377 (Everest World, Thane, Maharashtra) coordinates since the test data has coordinates around this point.
2. The coordinates of the cab drivers are inserted in the SQL script itself in point number 4, there is no mechanism yet to capture live coordinates or to simulate coordinates changing in the backend every few seconds.
3. I have used OpenLayers to have the map visualization
4. LocationIQ's free service has been used on the frontend to have Geocoding enabled in the application. 

The cab-booking-service has APIs for the following functionalities:

1. Create Cab Driver
2. Create Customer
3. Book a cab
4. Get available cab drivers near a location
5. Show all cab booking statuses
6. Update Driver Location
7. Get particular driver's details

"Cab-Service.postman_collection.json" postman collection has all these requests.

