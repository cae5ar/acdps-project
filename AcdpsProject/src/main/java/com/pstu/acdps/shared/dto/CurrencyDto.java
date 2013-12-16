package com.pstu.acdps.shared.dto;

@SuppressWarnings("serial")
public class CurrencyDto extends EntityDto {

    private String name;
    private String code;
    
    public CurrencyDto() {
        super();
    }
    
    public CurrencyDto(Long id, String name, String code) {
        super(id);
        this.name = name;
        this.code = code;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
