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

import com.pstu.acdps.server.domain.SSPObjectHierachy;
import com.pstu.acdps.server.domain.Section;
import com.pstu.acdps.shared.dto.SSPObjectDto;
import com.pstu.acdps.shared.exception.AnyServiceException;

@Repository
public class SectionDao extends JpaDao<Section> {
    @Autowired
    SSPObjectHierachyDao sspObjectHierachyDao;

    @Override
    public Class<Section> getEntityClass() {
        return Section.class;
    }

    public Long save(SSPObjectDto bean) throws AnyServiceException {
        Section entity = null;
        boolean isNew = true;
        if (bean.getId() != null) {
            entity = findById(bean.getId());
            isNew = false;
        }
        else {
            entity = new Section();
        }
        entity.setName(bean.getName());
        //entity.setCode(bean.getCode());
        persist(entity);
        /*
         * 
        if (isNew) {
            sspObjectHierachyDao.save(entity, bean.getParentId(), bean.getStartDate());
        }
        */
        sspObjectHierachyDao.save(entity, bean.getParentId(), bean.getStartDate(), isNew);
        return entity.getId();
    }

    @SuppressWarnings("unchecked")
    public List<SSPObjectDto> getChilds(Long parentId, Date date) {
    	
        List<SSPObjectDto> list = new ArrayList<SSPObjectDto>();
        
        /*
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
            Section s = findById(sspObjectId);
            boolean singleResult = ((Long) q.getSingleResult() > 0 ? true : false);
            list.add(new SSPObjectDto(sspObjectId, s.getName(), parentId, (Date) o[1], (Date) o[2], singleResult));
        }
        */
        
        Query sectionQuery = null;
        
        if (parentId == null) {
        	sectionQuery = em.createQuery(
            		"select section, hier,  " +
            		"	(select count(child) from section.children child where child.startDate <= :currdate and child.endDate > :currdate) " +
            		"from Section section join section.hierrachies hier " +
            		"where hier.parent is null and " +
            		"hier.startDate <= :currdate and " +
            		"hier.endDate > :currdate "
        		);
        	sectionQuery.setParameter("currdate", date);
        } else {
        	sectionQuery = em.createQuery(
            		"select section, hier,  " +
            		"	(select count(child) from section.children child where child.startDate <= :currdate and child.endDate > :currdate) " +
            		"from Section section join section.hierrachies hier " +
            		"where hier.parent.id = :parentId and " +
            		"hier.startDate <= :currdate and " +
            		"hier.endDate > :currdate "
    			);
        	sectionQuery.setParameter("currdate", date);
        	sectionQuery.setParameter("parentId", parentId);
        }
        
        List<Object[]> resultList = sectionQuery.getResultList();
        
        for (Object[] fields : resultList) {
        	Section section = (Section) fields[0];
        	SSPObjectHierachy hier = (SSPObjectHierachy) fields[1];
        	Long count = (Long) fields[2];
        	Long sectionParentId = hier.getParent() == null ? null : hier.getParent().getId();
        	
        	SSPObjectDto objectDto = new SSPObjectDto(section.getId(), section.getName(), sectionParentId, hier.getStartDate(), hier.getEndDate(), count != 0);
        	list.add(objectDto);
        }
        
        return list;
    }
}
