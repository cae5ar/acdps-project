package com.pstu.acdps.server.dao;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.domain.Employee;
import com.pstu.acdps.server.domain.Role;
import com.pstu.acdps.server.domain.User;
import com.pstu.acdps.server.domain.UserCredentials;
import com.pstu.acdps.server.domain.UserRole;
import com.pstu.acdps.shared.dto.RoleDto;
import com.pstu.acdps.shared.dto.UserDto;
import com.pstu.acdps.shared.exception.AnyServiceException;

@Repository
public class UserDao extends JpaDao<User> {

	@Autowired
	EmployeeDao employeeDao = null;

	@Autowired
	RoleDao roleDao = null;

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	public User findByLogin(String login) {
		if (login == null)
			return null;
		try {
			login = login.trim().toLowerCase();
			Query q = em.createQuery("SELECT e FROM "
					+ getEntityClass().getName()
					+ " e JOIN e.credentials c WHERE c.login = :login");
			q.setParameter("login", login);
			return (User) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public void save(UserDto userDto, String password)
			throws AnyServiceException {

		User user = null;

		if (userDto.getId() == null) {
			user = new User();
			user.setCredentials(new UserCredentials());
		} else {
			user = findById(userDto.getId());
		}

		Employee employee = null;

		if (userDto.getEmployee() != null) {
			employee = employeeDao.findById(userDto.getEmployee().getId());
		}

		user.setAdmin(userDto.getAdmin());
		user.setName(userDto.getName());
		user.setEmployee(employee);
		user.getCredentials().setLogin(userDto.getLogin());
		user.getCredentials().setPassword(password);

		if (userDto.getRoles() != null) {
			user.getUserRoles().clear();
			for (RoleDto dto : userDto.getRoles()) {
				Role role = roleDao.findById(dto.getId());
				if (role != null) {
					UserRole userRole = new UserRole();
					userRole.setRole(role);
					userRole.setUser(user);
					user.getUserRoles().add(userRole);
				}
			}
		}

		persist(user);
	}
}
