package com.pstu.acdps.shared.dto;

import java.util.Date;

import com.pstu.acdps.shared.type.SystemConstants;

@SuppressWarnings("serial")
public class SSPObjectDto extends EntityDto implements HasChild {

	private String name;
	private Long parentId;
	private Date startDate;
	private Date endDate;
	private Boolean hasChild = false;

	public SSPObjectDto() {
		super();
		startDate = new Date();
		endDate = SystemConstants.endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public SSPObjectDto(Long id, String name) {
		super(id);
		this.name = name;
	}

	public SSPObjectDto(Long id, String name, Long parentId, Date startDate) {
		super(id);
		this.name = name;
		this.parentId = parentId;
		this.startDate = startDate;
	}

	public SSPObjectDto(Long id, String name, Long parentId, Date startDate,
			Date endDate) {
		super(id);
		this.name = name;
		this.parentId = parentId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public SSPObjectDto(Long id, String name, Long parentId, Date startDate,
			Date endDate, boolean hasChild) {
		super(id);
		this.name = name;
		this.parentId = parentId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hasChild = hasChild;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean hasChild() {
		return hasChild;
	}

	public String getNodeName() {
		return this.name;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		if (endDate == null) {
			this.endDate = SystemConstants.endDate;
		}
		this.endDate = endDate;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(Boolean hasChild) {
		this.hasChild = hasChild;
	}

}
