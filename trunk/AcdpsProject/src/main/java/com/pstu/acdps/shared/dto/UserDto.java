package com.pstu.acdps.shared.dto;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class UserDto extends EntityDto {

	private String login = null;
	private Boolean admin = false;
	// перечень ролей пользователя
	private List<RoleDto> roles = new ArrayList<RoleDto>();
	// связанный сотрудник
	private EmployeeDto employee = null;

	public UserDto() {
		super();
		this.roles = new ArrayList<RoleDto>();
	}

	public UserDto(Long id) {
		super(id);

		this.roles = new ArrayList<RoleDto>();
	}

	public UserDto(Long id, String login) {
		super(id);
		this.login = login;

		this.roles = new ArrayList<RoleDto>();
	}

	public UserDto(Long id, String login, Boolean admin) {
		super(id);
		this.login = login;
		this.admin = admin;

		this.roles = new ArrayList<RoleDto>();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

	public EmployeeDto getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDto employee) {
		this.employee = employee;
	}

}
