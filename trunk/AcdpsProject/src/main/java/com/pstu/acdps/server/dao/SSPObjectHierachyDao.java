package com.pstu.acdps.server.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.domain.SSPObject;
import com.pstu.acdps.server.domain.SSPObjectHierachy;
import com.pstu.acdps.shared.exception.AnyServiceException;
import com.pstu.acdps.shared.type.SystemConstants;

@Repository
public class SSPObjectHierachyDao extends JpaDao<SSPObjectHierachy> {

    @Override
    public Class<SSPObjectHierachy> getEntityClass() {
        return SSPObjectHierachy.class;
    }

    public void save(SSPObject entity, Long parentId, Date startDate) throws AnyServiceException {
        SSPObjectHierachy hier = new SSPObjectHierachy();
        hier.setSspObject(entity);
        hier.setEndDate(SystemConstants.endDate);
        hier.setStartDate(startDate);
        hier.setParent(find(SSPObject.class, parentId));
        persist(hier);
    }
}
