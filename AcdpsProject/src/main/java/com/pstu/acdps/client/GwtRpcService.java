package com.pstu.acdps.client;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pstu.acdps.shared.dto.EmployeeDto;
import com.pstu.acdps.shared.dto.JobPosDto;
import com.pstu.acdps.shared.dto.RoleDto;
import com.pstu.acdps.shared.dto.SSPObjectDto;
import com.pstu.acdps.shared.dto.UserDto;
import com.pstu.acdps.shared.exception.AnyServiceException;

@RemoteServiceRelativePath("service.rpc")
public interface GwtRpcService extends RemoteService {
	UserDto getCurrentUser() throws AnyServiceException;

	Long saveEmployee(JobPosDto bean) throws AnyServiceException;

	List<SSPObjectDto> getDepartmentChilds(Long parentId, Date currdate)
			throws AnyServiceException;

	Long saveDepartment(SSPObjectDto item) throws AnyServiceException;

	void removeDepartment(Long id) throws AnyServiceException;

	Long saveSection(SSPObjectDto dto) throws AnyServiceException;

	void removeSection(Long id) throws AnyServiceException;

	List<SSPObjectDto> getSectionChilds(Long id, Date selectedDate);

	List<EmployeeDto> getAllEmployees();

	Map<Long, String> getAllJob();

	List<JobPosDto> getAllJobPositions();

	void removeEmployee(Long id) throws AnyServiceException;

	List<UserDto> getAllUsers();

	Long saveUser(UserDto bean, String password) throws AnyServiceException;

	void removeUser(Long id) throws AnyServiceException;

	List<RoleDto> getRoleList() throws AnyServiceException;

}
