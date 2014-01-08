package com.pstu.acdps.shared.dto;

import java.util.Date;

@SuppressWarnings("serial")
public class DocumentDto extends EntityDto {

	protected String num;
	protected Long authorId;
	protected Date createdDate;

	public DocumentDto() {
		super();
	}

	public DocumentDto(Long id, String num, Long authorId, Date createdDate) {
		super(id);
		this.num = num;
		this.authorId = authorId;
		this.createdDate = createdDate;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
