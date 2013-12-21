package com.pstu.acdps.server.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.domain.Job;
import com.pstu.acdps.shared.dto.JobDto;
import com.pstu.acdps.shared.exception.AnyServiceException;

@Repository
public class JobDao extends JpaDao<Job> {

	@Override
	public Class<Job> getEntityClass() {
		return Job.class;
	}

	public Long save(JobDto bean) throws AnyServiceException {
		Job entity = null;
		if (bean.getId() != null) {
			entity = findById(bean.getId());
		} else {
			entity = new Job();
		}
		entity.setName(bean.getName());
		persist(entity);
		return entity.getId();
	}

	@SuppressWarnings("unchecked")
	public List<JobDto> getAllJob() {
		List<JobDto> result = new ArrayList<JobDto>();

		Query jobQuery = em.createQuery("select job from Job job");
		List<Job> resultList = jobQuery.getResultList();

		for (Job job : resultList) {
			JobDto dto = new JobDto(job.getId(), job.getName());
			result.add(dto);
		}

		return result;
	}

	public Map<Long, String> getAllJobMap() {
		Map<Long, String> result = new HashMap<Long, String>();

		List<JobDto> allJob = getAllJob();

		for (JobDto job : allJob) {
			result.put(job.getId(), job.getName());
		}

		return result;
	}
}
