package com.pstu.acdps.server.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pstu.acdps.server.dao.UserDao;
import com.pstu.acdps.server.domain.User;
import com.pstu.acdps.server.security.CurrentUser;
import com.pstu.acdps.shared.dto.UserDto;
import com.pstu.acdps.shared.exception.AnyServiceException;

@Component
public class SecurityDetailsService {

    @PersistenceContext
    protected EntityManager em;

    @Autowired
    private UserDao userDao;

    public UserDto getCurrentUser() throws AnyServiceException {
        if (CurrentUser.hasRole(CurrentUser.ROLE_ADMIN))
            return new UserDto(null, "Администратор", CurrentUser.getLogin());
        User user = userDao.findById(CurrentUser.getId());
        return user == null ? null : user.toDto();
    }
}
