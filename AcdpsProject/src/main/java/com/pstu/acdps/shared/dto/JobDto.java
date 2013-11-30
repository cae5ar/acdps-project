package com.pstu.acdps.shared.dto;



@SuppressWarnings("serial")
public class JobDto extends EntityDto {
    private String name;

    public JobDto() {
        super();
    }

    public JobDto(Long id, String name) {
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
