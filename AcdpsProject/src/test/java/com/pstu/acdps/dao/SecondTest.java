package com.pstu.acdps.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

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
import com.pstu.acdps.server.dao.UserDao;
import com.pstu.acdps.server.domain.SSPObject;
import com.pstu.acdps.server.domain.Section;
import com.pstu.acdps.shared.dto.CfoDto;
import com.pstu.acdps.shared.dto.CurrencyDto;
import com.pstu.acdps.shared.dto.EmployeeDto;
import com.pstu.acdps.shared.dto.RoleDto;
import com.pstu.acdps.shared.dto.SSPObjectDto;
import com.pstu.acdps.shared.dto.SectionCFODto;
import com.pstu.acdps.shared.dto.SectionDto;
import com.pstu.acdps.shared.dto.UserDto;
import com.pstu.acdps.shared.exception.AnyServiceException;
import com.pstu.acdps.shared.type.SystemConstants;

public class SecondTest extends AbstractAuthenticatedTransactionalJUnit4SpringContextTests {
    
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
    
    @Autowired
    UserDao userDao = null;

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
    
    @SuppressWarnings("unchecked")
    @Test
    public void createSection() throws AnyServiceException {
    	SectionDto section = new SectionDto();
    	
    	section.setName("Статья 144");
    	
    	Long id = sectionDao.save(section);

        Query sectionQuery = em.createQuery("select section from Section section join section.hierrachies hier where hier.parent is null and hier.startDate <= :currdate and hier.endDate > :currdate");
        sectionQuery.setParameter("currdate", new Date());
        //sectionQuery.setParameter("parentId", null);
        List<Section> resultList = sectionQuery.getResultList();
        
        for (Section s : resultList) {
        	
        	System.out.println(s.getName());
        }
        
        List<SSPObject> resultObjectList = sectionQuery.getResultList();
        
        for (SSPObject o : resultObjectList) {
        	System.out.println(o.getId());
        }
    	
    	sectionDao.remove(id);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void selectSection() {
    	
    	Query sectionQuery = em.createQuery(
        		"select section, hier,  " +
        		"	(select count(child) from section.children child where child.startDate <= :currdate and child.endDate > :currdate) " +
        		"from Section section join section.hierrachies hier " +
        		"where hier.parent is null and " +
        		"hier.startDate <= :currdate and " +
        		"hier.endDate > :currdate "
    			);
    	
    	sectionQuery.setParameter("currdate", new Date());
    	
    	List<Object[]> resultList = sectionQuery.getResultList();
    	
    	for (Object[] fields : resultList) {
    		for (Object o : fields) {
    			System.out.println(o);
    		}
    	}
    	
    	Query sectionQuery2 = em.createQuery(
            		"select section, hier,  " +
            		"	(select count(child) from section.children child where child.startDate <= :currdate and child.endDate > :currdate) " +
            		"from Section section join section.hierrachies hier " +
            		"where hier.parent.id = :parentId and " +
            		"hier.startDate <= :currdate and " +
            		"hier.endDate > :currdate "
            		);
    	sectionQuery2.setParameter("currdate", new Date());
    	sectionQuery2.setParameter("parentId", 0L);
    	
    	List<Object[]> resultList2 = sectionQuery2.getResultList();
    	
    	for (Object[] fields : resultList2) {
    		for (Object o : fields) {
    			System.out.println(o);
    		}
    	}
    }
    
    Long empPupkinId = null;
    
    @Test
    public void createEmployee() throws AnyServiceException {
    	EmployeeDto employee = new EmployeeDto();
    	
    	employee.setFirstName("Абдурахман");
    	employee.setSecondName("Пупкин");
    	employee.setMiddleName("Чапаев");
    	employee.setBirthday(new Date());
    	
    	Long id = employeeDao.save(employee);
    	
    	employeeDao.remove(id);
    	
    	empPupkinId = employeeDao.save(employee);
    }
    
    @Test
    public void createUser() throws AnyServiceException {
    	
    	UserDto dto = new UserDto();
    	
    	dto.setAdmin(false);
    	dto.setLogin("notAdmin");
    	
    	RoleDto roleDto = roleDao.getRoleByIdent("ROLE_DIRECTORY");
    	
    	dto.getRoles().add(roleDto);
    	
    	RoleDto missingRole = roleDao.getRoleByIdent("ROLE_MISSING");
    	
    	if (missingRole != null)
    		dto.getRoles().add(missingRole);
    	
    	createEmployee();
    	
    	if (empPupkinId != null) {
    		EmployeeDto employee = employeeDao.getEmployeeById(empPupkinId);
    		dto.setEmployee(employee);
    	}
    	
    	userDao.save(dto, "password");
    }
    
    @Test
    public void testAdmin() {
    	UserDto user = userDao.findByLogin("operator").toDto();
    	if (user != null) {
    		System.out.println(user.getLogin() + user.getAdmin().toString());
    	}
    		
    }
    
    @Test
    public void createCfoWithSections() throws AnyServiceException {
    	
    	CfoDto cfo = new CfoDto();
    	cfo.setName("ЦФО 1");
    	
    	List<SSPObjectDto> sections = null; //sectionDao.getChilds(null, new Date());
    	
    	SSPObjectDto sec1 = sections.get(0);
    	
    	List<SectionCFODto> sectionCfo = new ArrayList<SectionCFODto>(); 
    	
    	for (SSPObjectDto section : sections) {
    		System.out.println(section.getName());
    		
    		SectionCFODto dto = new SectionCFODto();
    		
    		dto.setSection(section);
    		dto.setCfo(cfo);
    		dto.setStartDate(new Date());
    		dto.setEndDate(SystemConstants.endDate);
    		
    		sectionCfo.add(dto);
    	}
    	
    	cfo.setSectionCfo(sectionCfo);
    	
    	Long id = cfoDao.save(cfo);
    	
    	cfo = cfoDao.getById(id);
    	
    	for (SectionCFODto section : cfo.getSectionCfo()) {
    		System.out.println(section.getSection().getName());
    	}
    	
    	cfo.getSectionCfo().clear();
    	
    	SectionCFODto secCfo = new SectionCFODto();
    	secCfo.setSection(sec1);
    	secCfo.setCfo(cfo);
    	secCfo.setStartDate(SystemConstants.startDate);
    	secCfo.setEndDate(SystemConstants.endDate);
    	
    	cfo.getSectionCfo().add(secCfo);
    	
    	id = cfoDao.save(cfo);
    	
    	cfo = cfoDao.getById(id);

    	for (SectionCFODto section : cfo.getSectionCfo()) {
    		System.out.println(section.getSection().getName());
    	}
    }
    
    @Test
    public void addingRoles() throws AnyServiceException {
    	UserDto user = userDao.findByLogin("operator").toDto();
    	
    	for (RoleDto role : user.getRoles()) {
    		System.out.println(role.getName() + " " + role.getIdent());
    	}
    	
    	RoleDto newRole = roleDao.getRoleByIdent("ROLE_DIRECTORY");
    	
    	user.getRoles().add(newRole);
    	
    	userDao.save(user, "123456789");
    	
    	user = userDao.findByLogin("operator").toDto();
    	
    	for (RoleDto role : user.getRoles()) {
    		System.out.println(role.getName() + " " + role.getIdent());
    	}
    }
}
