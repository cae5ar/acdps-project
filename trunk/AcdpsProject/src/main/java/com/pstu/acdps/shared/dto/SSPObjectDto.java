package com.pstu.acdps.shared.dto;

import java.util.Date;

@SuppressWarnings("serial")
public class SSPObjectDto extends EntityDto {

    private String name;
    private Long parentId;
    private Date startDate;

    public SSPObjectDto() {
        super();
    }

    public Date getStartDate() {
        return startDate;
    }

    public SSPObjectDto(Long id, String name, Long parentId, Date startDate) {
        super(id);
        this.name = name;
        this.parentId = parentId;
        this.startDate = startDate;
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

}
