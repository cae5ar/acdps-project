package com.pstu.acdps.server.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "SSP_CFO_DEPARTMENT")
public class CFODepartment extends AbstractEntity {

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "department_id", nullable = false)
	private Department department = null;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cfo_id", nullable = false)
	private CFO cfo = null;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", nullable = false)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", nullable = false)
	private Date endDate;

	public CFO getCfo() {
		return cfo;
	}

	public void setCfo(CFO cfo) {
		this.cfo = cfo;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
