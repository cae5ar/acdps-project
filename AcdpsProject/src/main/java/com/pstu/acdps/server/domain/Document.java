package com.pstu.acdps.server.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "SSP_DOCUMENT")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Document extends AbstractEntity {

	@Column(nullable = false, name = "num")
	private String num;

	@Temporal(TemporalType.DATE)
	@Column(name = "doc_date", nullable = false)
	private Date docDate;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "author_id", nullable = false)
	private Employee employee = null;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
