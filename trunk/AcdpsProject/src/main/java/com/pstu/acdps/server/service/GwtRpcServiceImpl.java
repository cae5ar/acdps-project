package com.pstu.acdps.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.pstu.acdps.client.GwtRpcService;
import com.pstu.acdps.server.dao.CfoDao;
import com.pstu.acdps.server.dao.DepartmentDao;
import com.pstu.acdps.server.dao.EmployeeDao;
import com.pstu.acdps.server.dao.JobDao;
import com.pstu.acdps.server.dao.RoleDao;
import com.pstu.acdps.server.dao.SectionDao;
import com.pstu.acdps.server.dao.UserDao;
import com.pstu.acdps.server.domain.JobPosDao;
import com.pstu.acdps.shared.dto.CfoDto;
import com.pstu.acdps.shared.dto.EmployeeDto;
import com.pstu.acdps.shared.dto.JobPosDto;
import com.pstu.acdps.shared.dto.RoleDto;
import com.pstu.acdps.shared.dto.SSPObjectDto;
import com.pstu.acdps.shared.dto.SectionDto;
import com.pstu.acdps.shared.dto.UserDto;
import com.pstu.acdps.shared.exception.AnyServiceException;
import com.pstu.acdps.util.log.LoggableBusinessMethod;
import com.pstu.acdps.util.log.LoggableBusinessParameter;

@SuppressWarnings("serial")
@Transactional
@Service
public class GwtRpcServiceImpl extends RemoteServiceServlet implements GwtRpcService {

    @Autowired
    private SecurityDetailsService securityDetailsService;

    @Autowired
    private EmployeeDao jobPOsDao;

    @Autowired
    private JobPosDao jobPosDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private JobDao jobDao;

    @Autowired
    private CfoDao cfoDao;

    public UserDto getCurrentUser() throws AnyServiceException {
        return securityDetailsService.getCurrentUser();
    }

    @Override
    public Long saveJobPos(JobPosDto bean) throws AnyServiceException {
        bean.getEmployeeDto().setId(jobPOsDao.save(bean.getEmployeeDto()));
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
    @LoggableBusinessMethod(method = "Удаление подразделения")
    public void removeDepartment(@LoggableBusinessParameter(value = "id подразделения") Long id)
            throws AnyServiceException {
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
    public List<SSPObjectDto> getSectionChildsBySSPObjects(Long id, Date selectedDate) {
        return sectionDao.getChildsBySSPObjectDto(id, selectedDate);
    }

    @Override
    public List<JobPosDto> getAllJobPositions() {
        return jobPosDao.getAllJobPos();
    }

    @Override
    public Map<Long, String> getAllJob() {
        return jobDao.getAllJobMap();
    }

    @Override
    public void removeJobPos(Long id) throws AnyServiceException {
        jobPosDao.remove(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public Long saveUser(UserDto bean, String password) throws AnyServiceException {
        return userDao.save(bean, password);
    }

    @Override
    public void removeUser(Long id) throws AnyServiceException {
        userDao.remove(id);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return jobPOsDao.getAllEmployees();
    }

    @Override
    public List<RoleDto> getRoleList() throws AnyServiceException {
        return roleDao.getAllRoles();
    }

    @Override
    public List<CfoDto> getAllCfo() {
        List<CfoDto> list = new ArrayList<CfoDto>();
        list.add(new CfoDto(1L, "вася!!"));
        list.add(new CfoDto(2L, "маша!!"));
        list.add(new CfoDto(3L, "паша!!"));
        list.add(new CfoDto(4L, "гриша!!"));
        list.add(new CfoDto(5L, "петя!!"));
        return list;
    }

    @Override
    public void removeCfo(Long id) throws AnyServiceException {
        cfoDao.remove(id);
    }

    @Override
    public long saveCfo(CfoDto bean) throws AnyServiceException {
       return cfoDao.save(bean);
    }

    @Override
    public List<SectionDto> getSectionChilds(Long id, Date date) {
        return sectionDao.getChilds(id, date);
    }

}
