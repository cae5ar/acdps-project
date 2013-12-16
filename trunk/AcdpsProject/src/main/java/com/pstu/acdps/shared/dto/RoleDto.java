package com.pstu.acdps.shared.dto;


@SuppressWarnings("serial")
public class RoleDto extends EntityDto {

	private String name;
	private String ident;
	
	public RoleDto() {
		super();
	}
	
	public RoleDto(Long id, String name, String ident) {
		super(id);
		this.name = name;
		this.ident = ident;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}
	
}
