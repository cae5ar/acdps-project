package com.pstu.acdps.server.dao;

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
        }
        else {
            entity = new Employee();
        }
        entity.setFirstName(bean.getFirstName());
        entity.setSecondName(bean.getSecondName());
        entity.setMiddleName(bean.getMiddleName());
        entity.setBirthday(bean.getBirthday());
        persist(entity);
        return entity.getId();
    }
}
