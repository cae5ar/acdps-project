package com.pstu.acdps.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pstu.acdps.shared.dto.JobPosDto;
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
}
