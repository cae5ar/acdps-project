package com.pstu.acdps.shared.dto;

import java.util.Date;

import com.pstu.acdps.shared.type.SystemConstants;

@SuppressWarnings("serial")
public class SectionCFODto extends EntityDto {

	//private CfoDto cfo;
	//private SectionDto section;
	private SSPObjectDto section;
	private Date startDate;
	private Date endDate;
	
	public SectionCFODto() {
		super();
		this.startDate = SystemConstants.startDate;
		this.endDate = SystemConstants.endDate;
	}

	//public SectionCFODto(CfoDto cfo, SectionDto section, Date startDate, Date endDate) {
	@Deprecated
	public SectionCFODto(CfoDto cfo, SSPObjectDto section, Date startDate, Date endDate) {
		super();
		//this.cfo = cfo;
		this.section = section;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public SectionCFODto(Long id, SSPObjectDto section, Date startDate, Date endDate) {
		super(id);
		
		this.section = section;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	//public SectionDto getSection() {
	public SSPObjectDto getSection() {
		return section;
	}

	//public void setSection(SectionDto section) {
	public void setSection(SSPObjectDto section) {
		this.section = section;
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

	public CfoDto getCfo() {
		return null; //cfo;
	}

	public void setCfo(CfoDto cfo) {
		//this.cfo = cfo;
	}
	
}
