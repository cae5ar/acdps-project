package com.pstu.acdps.server.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
public class FileServlet {

    public ModelAndView setResponceMessage(HttpServletResponse res, int statusCode, String text) throws IOException {
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/HTML");
        res.setStatus(statusCode);
        res.getWriter().print(text);
        return null;
    }
}
