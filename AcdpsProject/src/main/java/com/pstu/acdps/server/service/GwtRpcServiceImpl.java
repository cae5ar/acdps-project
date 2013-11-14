package com.pstu.acdps.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.pstu.acdps.client.GwtRpcService;
import com.pstu.acdps.shared.dto.UserDto;
import com.pstu.acdps.shared.exception.AnyServiceException;

@SuppressWarnings("serial")
@Transactional
@Service
public class GwtRpcServiceImpl extends RemoteServiceServlet implements GwtRpcService {

    @Autowired
    SecurityDetailsService securityDetailsService;

    public UserDto getCurrentUser() throws AnyServiceException {
        return securityDetailsService.getCurrentUser();
    }

}
