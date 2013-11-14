package com.pstu.acdps.server.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.pstu.acdps.shared.type.ProtocolEvent;
import com.pstu.acdps.util.log.DatabaseLogger;
import com.pstu.acdps.util.log.Message;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    DatabaseLogger databaseLogger;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        Message message = databaseLogger.createMessage();
        CustomUserDetails tmp = (CustomUserDetails) authentication.getPrincipal();
        message.setUserName(tmp.getUsername());
        message.setMethod(ProtocolEvent.LOGIN);
        message.setStart(new Date());
        message.setEnd(new Date());
        databaseLogger.log(message);
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
