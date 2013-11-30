package com.pstu.acdps.server.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.domain.Department;
import com.pstu.acdps.server.domain.SSPObjectHierachy;
import com.pstu.acdps.shared.dto.SSPObjectDto;
import com.pstu.acdps.shared.exception.AnyServiceException;

@Repository
public class DepartmentDao extends JpaDao<Department> {
    @Autowired
    SSPObjectHierachyDao sspObjectHierachyDao;
    
    @Override
    public Class<Department> getEntityClass() {
        return Department.class;
    }

    public Long save(SSPObjectDto bean) throws AnyServiceException {
        Department entity = null;
        boolean isNew = true;
        if (bean.getId() != null) {
            entity = findById(bean.getId());
            isNew = false;
        }
        else {
            entity = new Department();
        }
        entity.setName(bean.getName());
        persist(entity);
        if (isNew) {
            sspObjectHierachyDao.save(entity, bean.getParentId(), bean.getStartDate());
        }
        return entity.getId();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<SSPObjectDto> getChilds(Long parentId, Date date) {
        // "select d.* from deparment d, hier h where currdate between h.start_date and h.end_date and d.id=h.obj_id"
        Query q = em.createQuery("SELECT op FROM "
                + SSPObjectHierachy.class
                + " h JOIN h.sspObject op where :currdate between h.startDate and h.endDate and h.parent.id = :parentId");
        q.setParameter("currdate", date);
        q.setParameter("parentId", parentId);
        List resultList = q.getResultList();
        return resultList;
    }

}
