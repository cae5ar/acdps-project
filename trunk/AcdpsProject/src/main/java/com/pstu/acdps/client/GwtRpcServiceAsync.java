package com.pstu.acdps.client;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pstu.acdps.shared.dto.EmployeeDto;
import com.pstu.acdps.shared.dto.JobPosDto;
import com.pstu.acdps.shared.dto.RoleDto;
import com.pstu.acdps.shared.dto.SSPObjectDto;
import com.pstu.acdps.shared.dto.UserDto;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GwtRpcServiceAsync {
    void getCurrentUser(AsyncCallback<UserDto> callback);

    void saveEmployee(JobPosDto bean, AsyncCallback<Long> callback);

    void getDepartmentChilds(Long parentId, Date currdate, AsyncCallback<List<SSPObjectDto>> callback);

    void saveDepartment(SSPObjectDto item, AsyncCallback<Long> simpleAsyncCallback);

    void removeDepartment(Long id, AsyncCallback<Void> simpleAsyncCallback);

    void saveSection(SSPObjectDto dto, AsyncCallback<Long> simpleAsyncCallback);

    void removeSection(Long id, AsyncCallback<Void> simpleAsyncCallback);

    void getSectionChilds(Long id, Date selectedDate, AsyncCallback<List<SSPObjectDto>> simpleAsyncCallback);

    void getAllJobPositions(AsyncCallback<List<JobPosDto>> asyncCallback);

    void getAllJob(AsyncCallback<Map<Long, String>> asyncCallback);

    void getAllUsers(AsyncCallback<List<UserDto>> asyncCallback);

    void removeEmployee(Long id, AsyncCallback<Void> asyncCallback);

    void saveUser(UserDto bean, String password, AsyncCallback<Long> callback);

    void removeUser(Long id, AsyncCallback<Void> callback);

    void getAllEmployees(AsyncCallback<List<EmployeeDto>> simpleAsyncCallback);

    void getRoleList(AsyncCallback<List<RoleDto>> callback);
}
