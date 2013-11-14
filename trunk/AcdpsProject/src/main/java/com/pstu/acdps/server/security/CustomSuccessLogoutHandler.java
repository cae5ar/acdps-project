package com.pstu.acdps.server.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.pstu.acdps.shared.type.ProtocolEvent;
import com.pstu.acdps.util.log.DatabaseLogger;
import com.pstu.acdps.util.log.Message;

public class CustomSuccessLogoutHandler extends SimpleUrlLogoutSuccessHandler {

    @Autowired
    DatabaseLogger databaseLogger;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        try {
            Message message = databaseLogger.createMessage();
            CustomUserDetails tmp = (CustomUserDetails) authentication.getPrincipal();
            message.setUserName(tmp.getUsername());
            message.setMethod(ProtocolEvent.LOGOUT);
            message.setStart(new Date());
            message.setEnd(new Date());
            databaseLogger.log(message);
            super.handle(request, response, authentication);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
