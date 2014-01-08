package com.pstu.acdps.server.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.dao.JpaDao;
import com.pstu.acdps.shared.dto.EmployeeDto;
import com.pstu.acdps.shared.dto.JobPosDto;
import com.pstu.acdps.shared.exception.AnyServiceException;
import com.pstu.acdps.shared.type.SystemConstants;

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
        Job job = find(Job.class, bean.getJob());
        Department department = find(Department.class, bean.getDepartmentId());
        Employee employee = find(Employee.class, bean.getEmployeeDto().getId());
        entity.setDepartment(department);
        entity.setJob(job);
        entity.setEmployee(employee);
        entity.setStartDate(SystemConstants.startDate);
        entity.setEndDate(SystemConstants.endDate);
        persist(entity);
        return entity.getId();
    }

    @SuppressWarnings("unchecked")
    public List<JobPosDto> getAllJobPos() {
        List<JobPosDto> result = new ArrayList<JobPosDto>();

        Query jobPosQuery = em.createQuery("select jobPos from JobPos jobPos");
        List<JobPos> resultList = jobPosQuery.getResultList();

        for (JobPos jobPos : resultList) {
            JobPosDto dto = new JobPosDto();

            dto.setId(jobPos.getId());
            dto.setStartDate(jobPos.getStartDate());
            dto.setEndDate(jobPos.getEndDate());

            Employee emp = jobPos.getEmployee();
            EmployeeDto employeeDto = new EmployeeDto(emp.getId(), emp.getFirstName(), emp.getMiddleName(), emp.getSecondName(), emp.getBirthday());

            dto.setEmployeeDto(employeeDto);
            dto.setDepartmentId(jobPos.getDepartment().getId());
            dto.setDepartmentName(jobPos.getDepartment().getName());
            dto.setJob(jobPos.getJob().getId());

            result.add(dto);
        }

        return result;
    }
}
