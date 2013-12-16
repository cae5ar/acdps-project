package com.pstu.acdps.server.dao;

import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.domain.CFO;
import com.pstu.acdps.shared.dto.CfoDto;
import com.pstu.acdps.shared.exception.AnyServiceException;
@Repository
public class CfoDao extends JpaDao<CFO>{

    @Override
    public Class<CFO> getEntityClass() {
        return CFO.class;
    }

    public Long save(CfoDto bean) throws AnyServiceException{
        CFO entity = null;
        if(bean.getId() != null){
            entity = findById(bean.getId());
        }else{
            entity = new CFO();
        }
        entity.setName(bean.getName());
        persist(entity);
        return entity.getId();
    }
    
}

