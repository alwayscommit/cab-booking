package com.assignment.cab_booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.cab_booking.entity.UserAccountEntity;
import com.assignment.cab_booking.model.AccountType;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccountEntity, Long> {

	//Login purpose
	UserAccountEntity findByMobileNumber(String mobileNumber);

	UserAccountEntity findByUserIdAndAccountType(String userId, AccountType driver);
	
}
