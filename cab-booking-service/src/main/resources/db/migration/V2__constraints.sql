ALTER TABLE booking ADD CONSTRAINT uk_reference_no UNIQUE (reference_no);
ALTER TABLE car ADD CONSTRAINT uk_car_number unique (car_number);
ALTER TABLE user_account ADD CONSTRAINT uk_mobile_number unique (mobile_number);
ALTER TABLE booking ADD CONSTRAINT fk_car_id FOREIGN KEY (car_id) REFERENCES car (id);
ALTER TABLE booking ADD CONSTRAINT fk_user_account FOREIGN KEY (customer_id) REFERENCES user_account (id);
ALTER TABLE car ADD CONSTRAINT fk_driven_by FOREIGN KEY (driven_by_account) REFERENCES user_account (id);