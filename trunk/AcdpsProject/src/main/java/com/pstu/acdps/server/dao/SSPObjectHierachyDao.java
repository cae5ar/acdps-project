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
        SSPObjectHierachy newHier = null; //new SSPObjectHierachy();
        
    	//этим методом нельзя изменить startDate и endDate
    	if (!isNew) {
    		SSPObjectHierachy actualHier = null;
    		for (SSPObjectHierachy hier : entity.getHierrachies()) {
    			if (startDate.getTime() >= hier.getStartDate().getTime() && startDate.getTime() <= hier.getEndDate().getTime()) {
    				actualHier = hier;
    			}
    		}
    		if (actualHier != null) {
    			Long actualParentId = actualHier.getParent() == null ? null : actualHier.getParent().getId();
    			
    			if (actualParentId == parentId) {
    				//попытка создать новую версию иерархии с тем же самым парентом, то есть по сути ничего не изменяя
    				//throw new AcdpsException("Попытка создать новую версию иерархии не изменяя родителя");
    				//в общем то ничего не меняется - будем игнорировать
    				return;
    			} else if (actualHier.getStartDate().getTime() == startDate.getTime()){
    				newHier = actualHier;
    			} else {
    				actualHier.setEndDate(new Date(startDate.getTime() - 1000));
    				persist(actualHier);
    				newHier = new SSPObjectHierachy();
    			}
    		}
    	} else {
    		newHier = new SSPObjectHierachy();
    	}
    	
        newHier.setSspObject(entity);
        newHier.setEndDate(SystemConstants.endDate);
        newHier.setStartDate(startDate);
        newHier.setParent(find(SSPObject.class, parentId));
        persist(newHier);
    }
}
