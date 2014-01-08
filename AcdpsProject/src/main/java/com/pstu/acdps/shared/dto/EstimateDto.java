package com.pstu.acdps.shared.dto;

import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class EstimateDto extends DocumentDto {
    
	protected Date planYear = null;
	protected Boolean isAccepted = null;
	protected CfoDto cfo = null;
	protected CurrencyDto currency = null;
	protected List<EstimateSectionDto> estimateSection = null;
    
    public EstimateDto() {
        super();
    }

    public EstimateDto(Long id, String num, Long authorId, Date createdDate) {
        super(id, num, authorId, createdDate);
    }

	public Date getPlanYear() {
		return planYear;
	}

	public void setPlanYear(Date planYear) {
		this.planYear = planYear;
	}

	public Boolean isAccepted() {
		return isAccepted;
	}

	public CfoDto getCfo() {
		return cfo;
	}

	public void setCfo(CfoDto cfo) {
		this.cfo = cfo;
	}

	public CurrencyDto getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyDto currency) {
		this.currency = currency;
	}

	public List<EstimateSectionDto> getEstimateSection() {
		return estimateSection;
	}

	public void setEstimateSection(List<EstimateSectionDto> estimateSection) {
		this.estimateSection = estimateSection;
	}
    
}
