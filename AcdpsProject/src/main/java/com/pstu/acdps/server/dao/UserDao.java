package com.pstu.acdps.server.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.domain.User;

@Repository
public class UserDao extends JpaDao<User> {

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    public User findByLogin(String login) {
        if (login == null) return null;
        try {
            login = login.trim().toLowerCase();
            Query q = em.createQuery("SELECT e FROM " + getEntityClass().getName()
                    + " e JOIN e.credentials c WHERE c.login = :login");
            q.setParameter("login", login);
            return (User) q.getSingleResult();
        }
        catch (Exception e) {
            return null;
        }
    }

}
