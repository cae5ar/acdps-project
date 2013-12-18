package com.pstu.acdps.server.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "SSP_ESTIMATE")
public class Estimate extends Document {

	@Temporal(TemporalType.DATE)
	@Column(name = "plan_year", nullable = false)
	private Date planYear;
	
	@Column(name = "is_accepted", nullable = false)
	private Boolean isAccepted;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cfo_id", nullable = false)
	private CFO cfo = null;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "currency_id", nullable = true)
	private Currency currency = null;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estimate", cascade = CascadeType.ALL)
	private List<EstimateSection> estimateSection = new ArrayList<EstimateSection>();

	public List<EstimateSection> getEstimateSection() {
		return estimateSection;
	}

	public void setEstimateSection(List<EstimateSection> estimateSection) {
		this.estimateSection = estimateSection;
	}

	public Date getPlanYear() {
		return planYear;
	}

	public void setPlanYear(Date planYear) {
		this.planYear = planYear;
	}

	public Boolean getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public CFO getCfo() {
		return cfo;
	}

	public void setCfo(CFO cfo) {
		this.cfo = cfo;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}
