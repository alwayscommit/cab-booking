package com.assignment.cab_booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.cab_booking.entity.UserAccountEntity;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccountEntity, Long> {

	UserAccountEntity findByMobileNumberAndAccountType(String mobileNumber, String accountType);
	
}
