package com.pstu.acdps.server.security;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * A custom service for retrieving users from a custom datasource, such as a
 * database.
 * <p>
 * This custom service must implement Spring's {@link UserDetailsService}
 */
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        // TODO Auto-generated method stub
        return null;
    }
//
//    protected static Logger logger = Logger.getLogger(CustomUserDetailsService.class);
//
//    @Autowired
//    private UserDao userDao;
//
////    @Autowired
////    private AdminProperties adminProperties;
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
//        // logger.debug("loadUserByUsername username=" + username);
//        UserDetails user = null;
//        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        authorities.add(new GrantedAuthorityImpl(CurrentUser.ROLE_USER));
//
////            if (adminProperties.getLogin().equalsIgnoreCase(username)) {
//        com.prognoz.meeting.server.domain.User dbUser = userDao.findByLogin(username);
//        if ((dbUser != null) && dbUser.isAdmin()) {
//            // пользователь из админов
//            authorities.add(new GrantedAuthorityImpl(CurrentUser.ROLE_ADMIN));
//            user = new CustomUserDetails(dbUser.getId(), dbUser.getCredentials().getLogin(), dbUser.getCredentials().getPassword(), true, true, true, true, authorities);
//        }
//        else {
//            if (dbUser == null) throw new UsernameNotFoundException("Username Not Found");
//            user = new CustomUserDetails(dbUser.getId(), dbUser.getCredentials().getLogin(), dbUser.getCredentials().getPassword(), true, true, true, true, authorities);
//        }
//
//        return user;
//    }

}