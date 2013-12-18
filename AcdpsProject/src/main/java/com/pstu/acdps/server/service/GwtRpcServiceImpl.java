package com.pstu.acdps.server.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.pstu.acdps.client.GwtRpcService;
import com.pstu.acdps.server.dao.DepartmentDao;
import com.pstu.acdps.server.dao.EmployeeDao;
import com.pstu.acdps.server.dao.SectionDao;
import com.pstu.acdps.server.domain.JobPosDao;
import com.pstu.acdps.shared.dto.JobPosDto;
import com.pstu.acdps.shared.dto.SSPObjectDto;
import com.pstu.acdps.shared.dto.UserDto;
import com.pstu.acdps.shared.exception.AnyServiceException;

@SuppressWarnings("serial")
@Transactional
@Service
public class GwtRpcServiceImpl extends RemoteServiceServlet implements GwtRpcService {

    @Autowired
    SecurityDetailsService securityDetailsService;

    @Autowired
    EmployeeDao epmployeeDao;

    @Autowired
    JobPosDao jobPosDao;

    @Autowired
    DepartmentDao departmentDao;
    
    @Autowired
    SectionDao sectionDao;

    public UserDto getCurrentUser() throws AnyServiceException {
        return securityDetailsService.getCurrentUser();
    }

    @Override
    public Long saveEmployee(JobPosDto bean) throws AnyServiceException {
        if (bean.getEmployeeDto().getId() == null) {
            bean.getEmployeeDto().setId(epmployeeDao.save(bean.getEmployeeDto()));
        }
        return jobPosDao.save(bean);
    }

    @Override
    public List<SSPObjectDto> getDepartmentChilds(Long parentId, Date currdate) throws AnyServiceException {
        return departmentDao.getChilds(parentId, currdate);
    }

    @Override
    public Long saveDepartment(SSPObjectDto item) throws AnyServiceException {
        return departmentDao.save(item);
    }

    @Override
    public void removeDepartment(Long id) throws AnyServiceException {
        departmentDao.remove(id);
    }

    @Override
    public Long saveSection(SSPObjectDto dto) throws AnyServiceException {
        return sectionDao.save(dto);
    }

    @Override
    public void removeSection(Long id) throws AnyServiceException {
    	sectionDao.remove(id);
    }

    @Override
    public List<SSPObjectDto> getSectionChilds(Long id, Date selectedDate) {
        return sectionDao.getChilds(id, selectedDate);
    }

    @Override
    public List<JobPosDto> getAllEmployees() {
        //TODO:дописать; чтоб все поля были заполнены
        return null;
    }

    @Override
    public Map<Long, String> getAllJob() {
        //TODO: надо пригнать все имеющиеся должности <идДолжности, Название>
        HashMap<Long, String> hashMap = new HashMap<Long, String>();
        hashMap.put(1L, "Задрот 2ой категории");
        hashMap.put(2L, "Задрот 1ой категории");
        hashMap.put(3L, "Главный задрот 1ой категории");
        hashMap.put(4L, "Ведущий задрот 1ой категории");
        hashMap.put(5L, "Pr0");
        return hashMap;
    }

}
