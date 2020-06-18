
Create OR REPLACE VIEW customer_booking_history AS SELECT booking.reference_no, customer.mobile_number AS customer_number, booking.booking_time, booking.start_address, booking.destination_address, 
booking.number_of_passengers, car.car_name, car.car_number, driver.mobile_number AS driver_number, 
CONCAT(driver.first_name, ' ', driver.last_name) AS driver_name 
FROM booking 
INNER JOIN car on booking.car_id=car.id 
INNER JOIN user_account customer ON booking.customer_id=customer.id 
INNER JOIN user_account driver ON car.driven_by_account=driver.id
WHERE booking.state!='ACTIVE';