package com.pstu.acdps.server.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.pstu.acdps.shared.dto.EmployeeDto;
import com.pstu.acdps.shared.dto.RoleDto;
import com.pstu.acdps.shared.dto.UserDto;

@Audited
@Entity
@Table(name = "T_USER")
public class User extends AbstractEntity {

    @Column(nullable = false, length = 4096)
    private String name;

    /** логин пароль */
    @JoinColumn(nullable = true)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserCredentials credentials = null;

    /** Флажок о том что пользователь админ */
    @Column(name = "c_admin", nullable = false)
    private Boolean admin = false;
    
    //роли пользователя
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserRole> userRoles = new ArrayList<UserRole>();
    
    @JoinColumn(name = "employee_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Employee employee = null;

    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
		this.name = name;
    }

    public UserCredentials getCredentials() {
		return credentials;
	}

    public void setCredentials(UserCredentials credentials) {
        this.credentials = credentials;
    }

    public UserDto toDto() {
        UserDto dto = new UserDto(id, name, credentials.getLogin(), admin);
        
        List<RoleDto> roles = new ArrayList<RoleDto>();
        for (UserRole ur : userRoles) {
        	Role role = ur.getRole();
        	RoleDto roleDto = new RoleDto(role.getId(), role.getName(), role.getIdent());
        	roles.add(roleDto);
        }
        
        dto.setRoles(roles);
        
        if (employee != null) {
        	EmployeeDto employeeDto = new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getSecondName(), employee.getMiddleName(), employee.getBirthday());
        	dto.setEmployee(employeeDto);
        }
        
        return dto;
    }

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
