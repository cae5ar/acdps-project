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
import com.pstu.acdps.shared.exception.AnyServiceException;
@Repository
public class CfoDao extends JpaDao<CFO>{

	@Autowired
	SectionCfoDao sectionCfoDao = null;
	
	@Autowired
	SectionDao sectionDao = null;
	
    @Override
    public Class<CFO> getEntityClass() {
        return CFO.class;
    }

    //при сохранении цфо учитываются связанные статьи, есть коллекция статей != null!
    public Long save(CfoDto bean) throws AnyServiceException{
        CFO entity = null;
        if(bean.getId() != null){
            entity = findById(bean.getId());
        }else{
            entity = new CFO();
        }
        
        entity.setName(bean.getName());
        
        //persist(entity);
        
        if (bean.getSectionCfo() != null) {
        	entity.getSectionCfo().clear();
        	for (SectionCFODto dto : bean.getSectionCfo()) {
        		SectionCFO sectionCfo = null;
        		if (dto.getId() == null)
        			sectionCfo = new SectionCFO();
        		else
        			sectionCfo = sectionCfoDao.findById(dto.getId());
        		
        		Long sectionId = dto.getSection() == null ? null : dto.getSection().getId();
        		Section section = sectionDao.findById(sectionId);
        		if (section == null) 
        			throw new AcdpsException("Статья не найдена!");
        		
        		if (dto.getStartDate() == null || dto.getEndDate() == null)
        			throw new AcdpsException("Одна из дат периода не указана!");
        		
        		sectionCfo.setCfo(entity);
        		sectionCfo.setSection(section);
        		sectionCfo.setStartDate(dto.getStartDate());
        		sectionCfo.setEndDate(dto.getEndDate());
        		
        		entity.getSectionCfo().add(sectionCfo);
        	}
        }
        
        persist(entity);
        
        return entity.getId();
    }
    
    @SuppressWarnings("unchecked")
    public List<CfoDto> getAllCfo() {
    	
    	List<CfoDto> result = new ArrayList<CfoDto>();
    	
    	Query cfoQuery = em.createQuery("select cfo from CFO cfo");
    	
    	List<CFO> resultList = cfoQuery.getResultList();
    	
    	for (CFO cfo : resultList) {
    		 CfoDto dto = new CfoDto(cfo.getId(), cfo.getName());
    		 //список все цфо достаем просто, без связанных статей
    		 //dto.setSections(sectionCfoDao.getByCfoId(cfo.getId()));
    		 
    		 result.add(dto);
    	}
    	
    	return result;
    }
    
    public CfoDto getById(Long id) {
    	
    	CfoDto result = null;
    	CFO cfo = null;
    	
    	if (id != null) {
    		cfo = findById(id);
    	}
    	
    	if (cfo != null) {
    		result = new CfoDto(cfo.getId(), cfo.getName());
    		result.setSectionCfo(sectionCfoDao.getByCfoId(id));
    	}
    	
    	return result;
    }
}

