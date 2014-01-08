package com.pstu.acdps.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.AcdpsException;
import com.pstu.acdps.server.domain.CFO;
import com.pstu.acdps.server.domain.Section;
import com.pstu.acdps.server.domain.SectionCFO;
import com.pstu.acdps.shared.dto.CfoDto;
import com.pstu.acdps.shared.dto.SectionCFODto;
import com.pstu.acdps.shared.dto.SectionDto;
import com.pstu.acdps.shared.exception.AnyServiceException;
import com.pstu.acdps.shared.type.SystemConstants;
@Repository
public class SectionCfoDao extends JpaDao<SectionCFO>{

	/*
	@Autowired
	private CfoDao cfoDao = null;
	*/
	
	@Autowired
	private SectionDao sectionDao = null;
	
    @Override
    public Class<SectionCFO> getEntityClass() {
        return SectionCFO.class;
    }

    public Long save(SectionCFODto bean, Long cfoId) throws AnyServiceException{
        SectionCFO entity = null;
        if(bean.getId() != null){
            entity = findById(bean.getId());
        }else{
            entity = new SectionCFO();
        }

        /*
        CFO cfo = null;
        if (bean.getCfo() == null)
        	throw new AcdpsException("ЦФО не указан!");
        cfo = cfoDao.findById(bean.getCfo().getId());
        if (cfo == null)
        	throw new AcdpsException("ЦФО не найден!");
        */
        
        Section section = null;
        if (bean.getSection() == null)
        	throw new AcdpsException("Статья не указана!");
        section = sectionDao.findById(bean.getSection().getId());
        if (section == null)
        	throw new AcdpsException("Статья не найдена!");
        
        //entity.setCfo(cfo);
        entity.setSection(section);
        entity.setStartDate(bean.getStartDate() != null ? bean.getStartDate() : SystemConstants.startDate);
        entity.setEndDate(bean.getEndDate() != null ? bean.getEndDate() : SystemConstants.endDate);
        
        persist(entity);
        return entity.getId();
    }
    
    @SuppressWarnings("unused")
    public List<SectionCFODto> getByCfoId(Long cfoId) {
    	
    	List<SectionCFODto> result = new ArrayList<SectionCFODto>();
    	
    	Query sectionCfoQuery = em.createQuery("select sectionCfo from SectionCFO sectionCfo where sectionCfo.cfo.id = :cfoId");
    	sectionCfoQuery.setParameter("cfoId", cfoId);
    	
    	List<SectionCFO> resultList = sectionCfoQuery.getResultList();
    	
    	for (SectionCFO sectionCfo : resultList) {
    		
    		CFO cfo = sectionCfo.getCfo();
    		Section section = sectionCfo.getSection();
    		
    		CfoDto cfoDto = new CfoDto(sectionCfo.getCfo().getId(), sectionCfo.getCfo().getName());
    		SectionDto sectionDto = new SectionDto(section.getId(), section.getName(), section.getCode());
    		
    		SectionCFODto dto = new SectionCFODto(cfoDto, sectionDto, sectionCfo.getStartDate(), sectionCfo.getEndDate());
    		result.add(dto);
    	}
    	
    	return result;
    }
}

