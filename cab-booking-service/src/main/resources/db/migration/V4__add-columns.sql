ALTER TABLE booking ADD COLUMN start_address VARCHAR(255) AFTER booking_time;
ALTER TABLE booking ADD COLUMN destination_address VARCHAR(255) AFTER start_address;