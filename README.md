# cab-booking

**** IMPORTANT NOTE: Please ignore the "cab-client" project as it is not configured to run with Authentication & Authorization. It can be used in the MASTER branch, where the cab-service project is without any API security. **** 

-----------------------------------------------------------------------------------------------------------------------------------
An assignment having some functionalities of a cab booking application built using Spring Boot, Hibernate, MySQL, and some basic API security features implemented using Spring Security and JWT. 


Prerequisites: Maven, JDK 1.8, MySQL Database
Please Ensure that you have MySQL running locally on port 3306 and the database user exists with access rights to cab_service_db

These properties can be configured in application.properties of "cab-booking-service" project
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/cab_service_db
spring.datasource.username=aakash
spring.datasource.password=aakash

***There is no need to run the SQL scripts as I have added test-scripts within Flyway itself to intialize database with test data*** 

***Steps to run this application***

1. Clone/Download the repository
2. To run the service, Run "mvn spring-boot:run" command inside "cab-booking-service" directory using a command prompt.
   - The service will start on port 8080
3. Use the Postman Collection "Cab-Service-19-06.postman_collection.json" to test the REST APIs.
   - The APIs which are a part of the assignment are labled as "RQ-#", rest are supporting APIs. 



- The Postman Collection "GraphQL Trial.postman_collection.json" file has a basic create/fetch user functionality implemented using GraphQL approach. 

---------------------------------------------------------------------------------------------------------------------------

You can make use the following steps to test all the APIs, using ""Cab-Service-19-06.postman_collection.json" Postman collection

1. Create Cab Driver -> Create a Cab Driver, which returns a carId and a unique userId identifying this driver
2. Create Customer -> Create a Customer which returns a system generated unique userId identifying this customer
3. Driver Login -> Provide driver's mobile number and password to login to get a JWT token in the response header.
4. Customer Login -> Provide customer's mobile number and password to login to get a JWT token in the response header.
5. Get Nearby Cabs -> Using the JWT token as a header parameter, you can fetch the nearby cabs. 
   Please Note: Test data in the database is added within 5kms radius of point with latitude=19.2309696 and longitude=72.9824377
6. Fetch driver details -> Provide the system generated unique id to fetch the driver details, ex. "2NtTORIZe9"
7. Book a cab -> Provide the required details along with the customer's system generated userId (Example: "2NtTORIZe9") for whom you want to book a cab. This API returns a booking reference number.
8. Show all cab statuses -> This API shows which driver is assigned to which customer
9. Update booking status -> A supporting API to mark a booking as completed/cancelled, here you have to pass the system generated booking reference number. 
10. Fetch past bookings -> provide a customer's userId whose booking history you wish to fetch. 


*** Thank you! ***  

