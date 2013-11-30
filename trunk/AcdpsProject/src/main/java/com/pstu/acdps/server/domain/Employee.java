package com.pstu.acdps.server.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "SSP_EMPLOYEE")
public class Employee extends AbstractEntity {

	@Column(nullable = false, name = "first_name")
	private String firstName;
	
	@Column(nullable = false, name = "second_name")
	private String secondName;

	@Column(name = "middle_name")
	private String middleName;

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", nullable = false)
	private Date birthday;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
