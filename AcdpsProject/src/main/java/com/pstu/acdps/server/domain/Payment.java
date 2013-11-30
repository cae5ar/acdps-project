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
@Table(name = "SSP_PAYMENT")
public class Payment extends Document {

	@Temporal(TemporalType.DATE)
	@Column(name = "pay_date", nullable = false)
	private Date payDate;
	
	@Column(name = "is_accepted", nullable = false)
	private Boolean isAccepted;
	
	@Column(name = "amount", nullable = false)
	private Double amount;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "section_id", nullable = false)
	private Section section = null;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cfo_id", nullable = false)
	private CFO cfo = null;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "currency_id", nullable = true)
	private Currency currency = null;

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Boolean getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
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
