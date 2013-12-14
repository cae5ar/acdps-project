package com.pstu.acdps.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pstu.acdps.shared.dto.JobPosDto;
import com.pstu.acdps.shared.dto.SSPObjectDto;
import com.pstu.acdps.shared.dto.UserDto;
import com.pstu.acdps.shared.exception.AnyServiceException;

@RemoteServiceRelativePath("service.rpc")
public interface GwtRpcService extends RemoteService {
    UserDto getCurrentUser() throws AnyServiceException;

    Long saveEmployee(JobPosDto bean) throws AnyServiceException;

    List<SSPObjectDto> getDepartmentChilds(Long parentId, Date currdate) throws AnyServiceException;

    Long saveDepartment(SSPObjectDto item) throws AnyServiceException;

    void removeDepartment(Long id) throws AnyServiceException;

    long saveSection(SSPObjectDto dto);

    void removeSection(Long id);

    List<SSPObjectDto> getSectionChilds(Long id, Date selectedDate);

}
