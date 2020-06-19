package com.assignment.cab_booking.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.assignment.cab_booking.constants.EntityConstants;
import com.assignment.cab_booking.constants.ValidationConstants;
import com.assignment.cab_booking.model.AccountType;

@Entity
@Table(name = EntityConstants.USER_ACCOUNT)
public class UserAccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = EntityConstants.ID)
	private Long Id;

	@NotEmpty(message = ValidationConstants.USER_ID_INVALID_MESSAGE)
	@Column(name = EntityConstants.USER_ID, unique = true)
	private String userId;

	@NotEmpty(message = ValidationConstants.FIRST_NAME_INVALID_MESSAGE)
	@Column(name = EntityConstants.FIRST_NAME)
	private String firstName;

	@NotEmpty(message = ValidationConstants.LAST_NAME_INVALID_MESSAGE)
	@Column(name = EntityConstants.LAST_NAME)
	private String lastName;

	@NotEmpty(message = ValidationConstants.PASSWORD_INVALID_MESSAGE)
	@Column(name = EntityConstants.ENCRYPTED_PASSWORD)
	private String encryptedPassword;

	@Pattern(regexp = "[\\d]{10}", message = ValidationConstants.MOBILE_NUMBER_INVALID_MESSAGE)
	@Column(name = EntityConstants.MOBILE_NUMBER, unique = true)
	private String mobileNumber;

	@Enumerated(EnumType.STRING)
	@NotNull(message = ValidationConstants.ACCOUNT_TYPE_INVALID_MESSAGE)
	@Column(name = EntityConstants.ACCOUNT_TYPE)
	private AccountType accountType;

	@Temporal(TemporalType.DATE)
	@Column(name = EntityConstants.CREATED_ON)
	private Date createdOn;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
