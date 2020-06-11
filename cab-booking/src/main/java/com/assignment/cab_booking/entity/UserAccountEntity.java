package com.assignment.cab_booking.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.assignment.cab_booking.constants.EntityColumnConstants;
import com.assignment.cab_booking.constants.ValidationConstants;

@Entity
public class UserAccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = ValidationConstants.FIRST_NAME_INVALID_MESSAGE)
	@Column(name = EntityColumnConstants.FIRST_NAME)
	private String firstName;

	@NotEmpty(message = ValidationConstants.LAST_NAME_INVALID_MESSAGE)
	@Column(name = EntityColumnConstants.LAST_NAME)
	private String lastName;

	@NotEmpty(message = ValidationConstants.PASSWORD_INVALID_MESSAGE)
	@Column(name = EntityColumnConstants.ENCRYPTED_PASSWORD)
	private String encryptedPassword;

	@Pattern(regexp = "[\\d]{10}", message = ValidationConstants.MOBILE_NUMBER_INVALID_MESSAGE)
	@Column(name = EntityColumnConstants.MOBILE_NUMBER, unique = true)
	private String mobileNumber;

	@NotNull(message = ValidationConstants.ACCOUNT_TYPE_INVALID_MESSAGE)
	@Column(name = EntityColumnConstants.ACCOUNT_TYPE)
	private AccountType accountType;

	@Temporal(TemporalType.DATE)
	@Column(name = EntityColumnConstants.CREATION_DATE)
	private Date creationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
