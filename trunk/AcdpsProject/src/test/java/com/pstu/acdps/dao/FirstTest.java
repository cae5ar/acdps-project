package com.pstu.acdps.dao;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pstu.acdps.AbstractAuthenticatedTransactionalJUnit4SpringContextTests;
import com.pstu.acdps.server.dao.CfoDao;
import com.pstu.acdps.server.dao.CurrencyDao;
import com.pstu.acdps.server.dao.DepartmentDao;
import com.pstu.acdps.server.dao.EmployeeDao;
import com.pstu.acdps.server.dao.PaymentDao;
import com.pstu.acdps.server.dao.RoleDao;
import com.pstu.acdps.server.dao.SectionDao;
import com.pstu.acdps.shared.dto.CfoDto;
import com.pstu.acdps.shared.dto.CurrencyDto;
import com.pstu.acdps.shared.dto.EmployeeDto;
import com.pstu.acdps.shared.dto.RoleDto;
import com.pstu.acdps.shared.dto.SSPObjectDto;
import com.pstu.acdps.shared.dto.SectionDto;
import com.pstu.acdps.shared.exception.AnyServiceException;

public class FirstTest extends AbstractAuthenticatedTransactionalJUnit4SpringContextTests {
    
    @Autowired
    RoleDao roleDao = null;
    
    @Autowired 
    CurrencyDao currencyDao = null;
    
    @Autowired 
    CfoDao cfoDao = null;
    
    @Autowired 
    SectionDao sectionDao = null;
    
    @Autowired 
    DepartmentDao departmentDao = null;
    
    @Autowired 
    EmployeeDao employeeDao = null;
    
    @Autowired
    PaymentDao paymentDao = null;

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
    
    @Test 
    public void createCurrency() throws AnyServiceException {
    	CurrencyDto currency = new CurrencyDto();
    	
    	currency.setName("Рубль");
    	currency.setCode("RUR");
    	
    	Long id = currencyDao.save(currency);
    	
    	currencyDao.remove(id);
    }
    
    @Test 
    public void createCfo() throws AnyServiceException {
    	CfoDto cfo = new CfoDto();
    	
    	cfo.setName("Это Цфо");
    	
    	Long id = cfoDao.save(cfo);
    	
    	cfoDao.remove(id);
    }
    
    @Test
    public void createSection() throws AnyServiceException {
    	SectionDto section = new SectionDto();
    	
    	section.setName("Статья 144");
    	
    	Long id = sectionDao.save(section);
    	
    	sectionDao.remove(id);
    }
    
    @Test
    public void createEmployee() throws AnyServiceException {
    	EmployeeDto employee = new EmployeeDto();
    	
    	employee.setFirstName("Абдурахман");
    	employee.setSecondName("Пупкин");
    	employee.setMiddleName("Чапаев");
    	employee.setBirthday(new Date());
    	
    	Long id = employeeDao.save(employee);
    	
    	employeeDao.remove(id);
    }
}
