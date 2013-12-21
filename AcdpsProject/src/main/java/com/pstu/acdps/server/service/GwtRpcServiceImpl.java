package com.pstu.acdps.server.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.pstu.acdps.client.GwtRpcService;
import com.pstu.acdps.server.dao.DepartmentDao;
import com.pstu.acdps.server.dao.EmployeeDao;
import com.pstu.acdps.server.dao.JobDao;
import com.pstu.acdps.server.dao.RoleDao;
import com.pstu.acdps.server.dao.SectionDao;
import com.pstu.acdps.server.dao.UserDao;
import com.pstu.acdps.server.domain.JobPosDao;
import com.pstu.acdps.shared.dto.EmployeeDto;
import com.pstu.acdps.shared.dto.JobPosDto;
import com.pstu.acdps.shared.dto.RoleDto;
import com.pstu.acdps.shared.dto.SSPObjectDto;
import com.pstu.acdps.shared.dto.UserDto;
import com.pstu.acdps.shared.exception.AnyServiceException;
import com.pstu.acdps.util.log.LoggableBusinessMethod;
import com.pstu.acdps.util.log.LoggableBusinessParameter;

@SuppressWarnings("serial")
@Transactional
@Service
public class GwtRpcServiceImpl extends RemoteServiceServlet implements
		GwtRpcService {

	@Autowired
	private SecurityDetailsService securityDetailsService;

	@Autowired
	private EmployeeDao employeeDao;

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

	public UserDto getCurrentUser() throws AnyServiceException {
		return securityDetailsService.getCurrentUser();
	}

	@Override
	public Long saveEmployee(JobPosDto bean) throws AnyServiceException {
		if (bean.getEmployeeDto().getId() == null) {
			bean.getEmployeeDto().setId(
					employeeDao.save(bean.getEmployeeDto()));
		}
		return jobPosDao.save(bean);
	}

	@Override
	public List<SSPObjectDto> getDepartmentChilds(Long parentId, Date currdate)
			throws AnyServiceException {
		return departmentDao.getChilds(parentId, currdate);
	}

	@Override
	public Long saveDepartment(SSPObjectDto item) throws AnyServiceException {
		return departmentDao.save(item);
	}

	@Override
	@LoggableBusinessMethod(method = "Удаление подразделения")
	public void removeDepartment(
			@LoggableBusinessParameter(value = "id подразделения") Long id)
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
	public List<SSPObjectDto> getSectionChilds(Long id, Date selectedDate) {
		return sectionDao.getChilds(id, selectedDate);
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
	public void removeEmployee(Long id) throws AnyServiceException {
		employeeDao.remove(id);
	}

	@Override
	public List<UserDto> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public Long saveUser(UserDto bean, String password)
			throws AnyServiceException {
		return userDao.save(bean, password);
	}

	@Override
	public void removeUser(Long id) throws AnyServiceException {
		userDao.remove(id);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}

	@Override
	public List<RoleDto> getRoleList() throws AnyServiceException {
		return roleDao.getAllRoles();
	}

}
