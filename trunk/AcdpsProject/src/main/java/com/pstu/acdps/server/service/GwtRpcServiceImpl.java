package com.pstu.acdps.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.pstu.acdps.client.GwtRpcService;
import com.pstu.acdps.server.dao.DepartmentDao;
import com.pstu.acdps.server.dao.EmployeeDao;
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
    public Long saveSection(SSPObjectDto dto) {
        // TODO Написать реаизацию
        return 0L;
    }

    @Override
    public void removeSection(Long id) {
        // TODO Написать реаизацию

    }

    @Override
    public List<SSPObjectDto> getSectionChilds(Long id, Date selectedDate) {
        // TODO Написать реаизацию
        ArrayList<SSPObjectDto> arrayList = new ArrayList<SSPObjectDto>();
        arrayList.add(new SSPObjectDto(1L, "Статья раз", null, new Date()));
        arrayList.add(new SSPObjectDto(2L, "Статья два", null, new Date()));
        arrayList.add(new SSPObjectDto(3L, "Статья три", null, new Date()));
        arrayList.add(new SSPObjectDto(4L, "Статья четыре", null, new Date()));

        return arrayList;
    }

}
