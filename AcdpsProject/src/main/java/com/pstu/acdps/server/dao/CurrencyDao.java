package com.pstu.acdps.server.dao;

import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.AcdpsException;
import com.pstu.acdps.server.domain.Currency;
import com.pstu.acdps.server.domain.Role;
import com.pstu.acdps.shared.dto.CurrencyDto;
import com.pstu.acdps.shared.dto.RoleDto;
import com.pstu.acdps.shared.exception.AnyServiceException;

@Repository
public class CurrencyDao extends JpaDao<Currency> {

	@Override 
	public Class<Currency> getEntityClass() {
		return Currency.class;
	}
	
	public Long save(CurrencyDto bean) throws AnyServiceException {
		
		Currency entity = null;
		if (bean.getId() == null) {
			entity = new Currency();
		} else {
			entity = findById(bean.getId());
			if (entity == null)
				throw new AcdpsException("Валюты с таки Id не существует!");
		}
		
		entity.setName(bean.getName());
		entity.setCode(bean.getCode());
		
		persist(entity);
		
		return entity.getId();
	}
}
