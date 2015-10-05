package com.bradbury.dana;

import java.util.Date;

import org.springframework.data.annotation.Id;


public class Customer {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String registrationId;
    private Date registrationDate;
    
    public Customer() {}

    public Customer(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(final String firstName, final String lastName, final String registrationId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationId = registrationId;
    }
    
    public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(final String registrationId) {
		this.registrationId = registrationId;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(final Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s', registrationId='%s']",
                id, firstName, lastName, registrationId);
    }

}

