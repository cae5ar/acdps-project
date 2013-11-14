package com.pstu.acdps.server.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.pstu.acdps.shared.exception.SessionExpireSecurityException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    protected static Logger logger = Logger.getLogger(CustomAuthenticationEntryPoint.class);

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        if (request.getMethod().equalsIgnoreCase("POST") && request.getRequestURI().contains(".rpc")) {
            try {
                String s = RPC.encodeResponseForFailure(null, new SessionExpireSecurityException("Сессия закончилась"));
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(s);
            }
            catch (SerializationException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Сессия закончилась");
            }
        }
        else {
            response.setStatus(HttpServletResponse.SC_OK);
            request.getRequestDispatcher("/WEB-INF/jsp/loginpage.jsp").forward(request, response);
        }
    }
}
