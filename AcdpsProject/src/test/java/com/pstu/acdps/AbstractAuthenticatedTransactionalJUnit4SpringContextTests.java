/**
 * 
 */
package com.pstu.acdps;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.pstu.acdps.client.GwtRpcService;

public abstract class AbstractAuthenticatedTransactionalJUnit4SpringContextTests extends AbstractTransactionalSpringTest {

    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Autowired
    protected GwtRpcService gwtRpcService;

    @PersistenceContext
    protected EntityManager em;


    protected void authenticateOperator() {
        authenticate("operator", "123qwe");
    }

    protected void authenticate(String userName, String password) {
        UserDetails customUserDetails = customUserDetailsService.loadUserByUsername(userName);
        AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customUserDetails, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        authentication.setAuthenticated(false);
        daoAuthenticationProvider.authenticate(authentication);
    }

}
