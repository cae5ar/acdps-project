package com.pstu.acdps.shared.dto;

import java.util.Date;

@SuppressWarnings("serial")
public class EstimateSectionDto extends EntityDto {

	protected SectionDto section = null;
	protected Date month = null;
	protected Double value = null;
	
	public EstimateSectionDto() {
		super();
	}

	public EstimateSectionDto(Long id, SectionDto section, Date month, Double value) {
		super(id);
		
		this.section = section;
		this.month = month;
		this.value = value;
	}

	public SectionDto getSection() {
		return section;
	}

	public void setSection(SectionDto section) {
		this.section = section;
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
