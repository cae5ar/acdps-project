package com.pstu.acdps.dao;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pstu.acdps.AbstractAuthenticatedTransactionalJUnit4SpringContextTests;
import com.pstu.acdps.server.dao.RoleDao;
import com.pstu.acdps.server.dao.RoleDto;
import com.pstu.acdps.shared.dto.SSPObjectDto;
import com.pstu.acdps.shared.exception.AnyServiceException;

public class FirstTest extends AbstractAuthenticatedTransactionalJUnit4SpringContextTests {

    @Before
    public void setUp() throws Exception {
        authenticateOperator();
    }

//    @Test
    public void testText() {
        try {
            List<SSPObjectDto> departmentChilds = gwtRpcService.getDepartmentChilds(null, new Date());
            for(SSPObjectDto d:departmentChilds){
                System.out.println(d.getName());
            }
        }
        catch (AnyServiceException e) {
            e.printStackTrace();
        }
    }
    
    @Autowired
    RoleDao roleDao = null;

    @Test
    public void userRoleTest() {
    	try {
    		RoleDto directoryRole = new RoleDto();
    		directoryRole.setName("Ответственный за ведение справочников");
    		directoryRole.setIdent("DIRECTORY_ROLE");
    		
    		roleDao.save(directoryRole);
    	}
    	catch (Throwable e) {
    		e.printStackTrace();
    	}
    }
}
