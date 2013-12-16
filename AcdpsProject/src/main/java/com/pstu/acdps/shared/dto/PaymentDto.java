package com.pstu.acdps.shared.dto;

import java.util.Date;

@SuppressWarnings("serial")
public class PaymentDto extends DocumentDto {
    
	private Date payDate;
	private Boolean isAccepted;
	private Double amount;
	
	private Long cfoId;
	private Long sectionId;
	private Long currencyId;
	
    public PaymentDto() {
        super();
    }
    
    public PaymentDto(Long id, String num, Long authorId, Date createdDate) {
        super(id, num, authorId, createdDate);
    }

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

	public Long getCfoId() {
		return cfoId;
	}

	public void setCfoId(Long cfoId) {
		this.cfoId = cfoId;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}
    
}
