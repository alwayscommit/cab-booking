CREATE TABLE IF NOT EXISTS booking (
	id BIGINT NOT NULL AUTO_INCREMENT, 
	booking_time DATETIME NOT NULL, 
	end_latitude DOUBLE PRECISION NOT NULL, 
	end_longitude DOUBLE PRECISION NOT NULL, 
	number_of_passengers VARCHAR(1) NOT NULL, 
	reference_no VARCHAR(10) NOT NULL, 
	start_latitude DOUBLE PRECISION NOT NULL, 
	start_longitude DOUBLE PRECISION NOT NULL, 
	state VARCHAR(50) NOT NULL, 
	car_id BIGINT NOT NULL, 
	customer_id BIGINT NOT NULL, PRIMARY KEY (id)
) engine=InnoDB; 


CREATE TABLE IF NOT EXISTS car (
	id BIGINT NOT NULL AUTO_INCREMENT, 
	car_id VARCHAR(10) NOT NULL, 
	car_name VARCHAR(50), 
	car_number VARCHAR(10), 
	car_status VARCHAR(20) NOT NULL, 
	latitude DOUBLE PRECISION, 
	longitude DOUBLE PRECISION, 
	driven_by_account BIGINT, 
	PRIMARY KEY (id)
) engine=InnoDB;


CREATE TABLE IF NOT EXISTS user_account (
	id BIGINT NOT NULL AUTO_INCREMENT, 
	user_id VARCHAR(10) NOT NULL, 
	account_type VARCHAR(20) NOT NULL, 
	created_on DATE, 
	encrypted_password VARCHAR(255), 
	first_name VARCHAR(50), 
	last_name VARCHAR(50), 
	mobile_number VARCHAR(10), 
	PRIMARY KEY (id)
) engine=InnoDB;