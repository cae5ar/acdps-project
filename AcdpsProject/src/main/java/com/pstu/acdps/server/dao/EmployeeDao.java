package com.pstu.acdps.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.domain.Employee;
import com.pstu.acdps.shared.dto.EmployeeDto;
import com.pstu.acdps.shared.exception.AnyServiceException;

@Repository
public class EmployeeDao extends JpaDao<Employee> {

	@Override
	public Class<Employee> getEntityClass() {
		return Employee.class;
	}

	public Long save(EmployeeDto bean) throws AnyServiceException {
		Employee entity = null;
		if (bean.getId() != null) {
			entity = findById(bean.getId());
		} else {
			entity = new Employee();
		}
		entity.setFirstName(bean.getFirstName());
		entity.setSecondName(bean.getSecondName());
		entity.setMiddleName(bean.getMiddleName());
		entity.setBirthday(bean.getBirthday());
		persist(entity);
		return entity.getId();
	}

	public EmployeeDto getEmployeeById(Long id) {
		EmployeeDto result = null;

		Employee employee = findById(id);

		if (employee != null) {
			result = new EmployeeDto(employee.getId(), employee.getFirstName(),
					employee.getSecondName(), employee.getMiddleName(),
					employee.getBirthday());
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<EmployeeDto> getAllEmployees() {
		List<EmployeeDto> list = new ArrayList<EmployeeDto>();
		Query q = em.createQuery("SELECT u FROM " + getEntityClass().getName()
				+ " u");
		List<Employee> resultList = q.getResultList();
		for (Employee e : resultList) {
			EmployeeDto dto = new EmployeeDto(e.getId(), e.getFirstName(),
					e.getSecondName(), e.getMiddleName(), e.getBirthday());
			list.add(dto);
		}
		return list;
	}
}
