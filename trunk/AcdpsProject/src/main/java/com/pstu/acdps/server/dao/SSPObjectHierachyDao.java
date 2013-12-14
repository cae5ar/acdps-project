package com.pstu.acdps.server.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.AcdpsException;
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

    public void save(SSPObject entity, Long parentId, Date startDate, boolean isNew) throws AnyServiceException {
    	if (!isNew) {
    		SSPObjectHierachy actualHier = null;
    		for (SSPObjectHierachy hier : entity.getHierrachies()) {
    			if (startDate.getTime() >= hier.getStartDate().getTime() && startDate.getTime() <= hier.getEndDate().getTime()) {
    				actualHier = hier;
    			}
    		}
    		if (actualHier != null) {
    			if (actualHier.getParent() == null && parentId == 0) {
    				//попытка создать новую версию иерархии с тем же самым парентом, то есть по сути ничего не изменяя
    				throw new AcdpsException("Попытка создать новую версию иерархии не изменяя родителя");
    			} else if (actualHier.getParent().getId() == parentId) {
    				//попытка создать новую версию иерархии с тем же самым парентом, то есть по сути ничего не изменяя
    				throw new AcdpsException("Попытка создать новую версию иерархии не изменяя родителя");
    			} else {
    				actualHier.setEndDate(new Date(startDate.getTime() - 1000));
    				persist(actualHier);
    			}
    		}
    	}
    	
        SSPObjectHierachy hier = new SSPObjectHierachy();
        hier.setSspObject(entity);
        hier.setEndDate(SystemConstants.endDate);
        hier.setStartDate(startDate);
        hier.setParent(find(SSPObject.class, parentId));
        persist(hier);
    }
}
