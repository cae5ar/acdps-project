package com.pstu.acdps.shared.dto;


@SuppressWarnings("serial")
public class DepartmentDto extends EntityDto {

    private String name;

    public DepartmentDto() {
        super();
    }

    public DepartmentDto(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
