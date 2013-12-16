package com.pstu.acdps.shared.dto;

@SuppressWarnings("serial")
public class CfoDto extends EntityDto {
    
	private String name;

    public CfoDto() {
        super();
    }

    public CfoDto(Long id, String name) {
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
