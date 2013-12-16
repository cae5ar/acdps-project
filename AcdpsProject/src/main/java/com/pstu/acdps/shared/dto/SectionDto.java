package com.pstu.acdps.shared.dto;

import java.util.Date;


@SuppressWarnings("serial")
public class SectionDto extends SSPObjectDto {
    
    private String code;

    public SectionDto() {
        super();
    }

    public SectionDto(Long id, String name, String code, Long parentId, Date startDate) {
        super(id, name, parentId, startDate);
        this.code = code;
    }

    public SectionDto(Long id, String name, String code, Long parentId, Date startDate, Date endDate) {
        super(id, name, parentId, startDate, endDate);
        this.code = code;
    }

    public SectionDto(Long id, String name, String code, Long parentId, Date startDate, Date endDate, boolean hasChild) {
        super(id, name, parentId, startDate, endDate, hasChild);
        this.code = code;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
