package com.pstu.acdps.server.dao;

import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.domain.Role;
import com.pstu.acdps.shared.exception.AnyServiceException;

@Repository
public class RoleDao extends JpaDao<Role> {

	@Override 
	public Class<Role> getEntityClass() {
		return Role.class;
	}
	
	public void save(RoleDto bean) throws AnyServiceException {
		Role entity = null;
		if (bean.getId() == null) {
			entity = new Role();
		} else {
			entity = findById(bean.getId());
		}
		
		entity.setIdent(bean.getIdent());
		entity.setName(bean.getName());
		persist(entity);
		entity.getId();
	}
}
