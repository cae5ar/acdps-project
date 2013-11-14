package com.pstu.acdps.server.security;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.pstu.acdps.server.dao.UserDao;
import com.pstu.acdps.server.domain.User;

/**
 * A custom service for retrieving users from a custom datasource, such as a
 * database.
 * <p>
 * This custom service must implement Spring's {@link UserDetailsService}
 */
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    protected static Logger logger = Logger.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserDao userDao;

    // @Autowired
    // private AdminProperties adminProperties;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        UserDetails user = null;
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new GrantedAuthorityImpl(CurrentUser.ROLE_USER));

        // if (adminProperties.getLogin().equalsIgnoreCase(username)) {
        User dbUser = userDao.findByLogin(username);
        if ((dbUser != null) && dbUser.isAdmin()) {
            // пользователь из админов
            authorities.add(new GrantedAuthorityImpl(CurrentUser.ROLE_ADMIN));
            user = new CustomUserDetails(dbUser.getId(), dbUser.getCredentials().getLogin(), dbUser.getCredentials().getPassword(), true, true, true, true, authorities);
        }
        else {
            if (dbUser == null) throw new UsernameNotFoundException("Username Not Found");
            user = new CustomUserDetails(dbUser.getId(), dbUser.getCredentials().getLogin(), dbUser.getCredentials().getPassword(), true, true, true, true, authorities);
        }

        return user;
    }

}