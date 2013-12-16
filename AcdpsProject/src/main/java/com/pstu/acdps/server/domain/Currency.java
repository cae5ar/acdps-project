package com.pstu.acdps.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "SSP_CURRENCY")
public class Currency extends AbstractEntity {

	@Column(nullable = false, name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
