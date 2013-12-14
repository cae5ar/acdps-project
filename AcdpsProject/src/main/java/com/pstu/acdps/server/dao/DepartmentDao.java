package com.pstu.acdps.server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
        List<SSPObjectHierachy> hierrachies = entity.getHierrachies();
        if (isNew) {
            sspObjectHierachyDao.save(entity, bean.getParentId(), bean.getStartDate());
        }
        return entity.getId();
    }

    @SuppressWarnings("unchecked")
    public List<SSPObjectDto> getChilds(Long parentId, Date date) {
        List<SSPObjectDto> list = new ArrayList<SSPObjectDto>();
        Criteria c = getHibernateSession().createCriteria(SSPObjectHierachy.class, "h");
        c.createAlias("h.sspObject", "sspObj");
        if (parentId == null)
            c.add(Restrictions.isNull("parent"));
        else
            c.add(Restrictions.eq("parent.id", parentId));
//        @formatter:off
        c.add(Restrictions.ge("h.endDate", date));
        c.add(Restrictions.le("h.startDate", date));
        
        c.setProjection(Projections.projectionList()
        .add(Projections.property("sspObj.id"))
        .add(Projections.property("h.startDate"))
        .add(Projections.property("h.endDate")));

        List<Object[]> resultList = c.list();
        for (Object[] o : resultList) {
            Query q = em.createQuery("SELECT COUNT(*) FROM " + SSPObjectHierachy.class.getName() + " h WHERE :currdate >= h.startDate and :currdate < h.endDate and h.parent.id = :parentId");
//        @formatter:on
            Long sspObjectId = (Long) o[0];
            q.setParameter("currdate", date);
            q.setParameter("parentId", sspObjectId);
            Department d = findById(sspObjectId);
            boolean singleResult = ((Long) q.getSingleResult() > 0 ? true : false);
            list.add(new SSPObjectDto(sspObjectId, d.getName(), parentId, (Date) o[1], (Date) o[2], singleResult));
        }
        return list;
    }
}
