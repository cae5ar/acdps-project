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
@Table(name = "SSP_ESTIMATE_SECTION")
public class EstimateSection extends AbstractEntity {

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "section_id", nullable = false)
	private Section section = null;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "estimate_id", nullable = false)
	private Estimate estimate = null;

	@Temporal(TemporalType.DATE)
	@Column(name = "plan_month", nullable = false)
	private Date month;

	@Column(name = "value")
	private Double value;

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Estimate getEstimate() {
		return estimate;
	}

	public void setEstimate(Estimate estimate) {
		this.estimate = estimate;
	}

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
