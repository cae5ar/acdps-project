package com.pstu.acdps.server.dao;

import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.domain.Job;
import com.pstu.acdps.shared.dto.JobDto;
import com.pstu.acdps.shared.exception.AnyServiceException;
@Repository
public class JobDao extends JpaDao<Job>{

    @Override
    public Class<Job> getEntityClass() {
        return Job.class;
    }

    public Long save(JobDto bean) throws AnyServiceException{
        Job entity = null;
        if(bean.getId() != null){
            entity = findById(bean.getId());
        }else{
            entity = new Job();
        }
        entity.setName(bean.getName());
        persist(entity);
        return entity.getId();
    }
    
}

