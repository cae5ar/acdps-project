package com.pstu.acdps.server.domain;

import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.dao.JpaDao;
import com.pstu.acdps.shared.dto.JobPosDto;
import com.pstu.acdps.shared.exception.AnyServiceException;

@Repository
public class JobPosDao extends JpaDao<JobPos> {

    @Override
    public Class<JobPos> getEntityClass() {
        return JobPos.class;
    }

    public Long save(JobPosDto bean) throws AnyServiceException {
        JobPos entity = null;
        if (bean.getId() != null) {
            entity = findById(bean.getId());
        }
        else {
            entity = new JobPos();
        }
        Job job = find(Job.class, bean.getJobId());
        Department department = find(Department.class, bean.getDepartmentId());
        Employee employee = find(Employee.class, bean.getEmployeeDto().getId());
        entity.setDepartment(department);
        entity.setJob(job);
        entity.setEmployee(employee);
        entity.setStartDate(bean.getStartDate());
        entity.setEndDate(bean.getEndDate());
        persist(entity);
        return entity.getId();
    }
}
