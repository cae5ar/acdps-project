package com.pstu.acdps.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.AcdpsException;
import com.pstu.acdps.server.domain.CFO;
import com.pstu.acdps.server.domain.Currency;
import com.pstu.acdps.server.domain.Employee;
import com.pstu.acdps.server.domain.Payment;
import com.pstu.acdps.server.domain.Section;
import com.pstu.acdps.shared.dto.PaymentDto;
import com.pstu.acdps.shared.exception.AnyServiceException;

@Repository
public class PaymentDao extends JpaDao<Payment> {

	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	SectionDao sectionDao;

	@Autowired
	CfoDao cfoDao;

	@Autowired
	CurrencyDao currencyDao;
	
    @Override
    public Class<Payment> getEntityClass() {
        return Payment.class;
    }
    
    public Long save(PaymentDto bean) throws AnyServiceException{
    	Payment entity = null;

    	if (bean.getId() == null) {
    		entity = new Payment();
    	} else {
    		entity = findById(bean.getId());
    		if (entity == null)
    			throw new AcdpsException("Платежа с таки Id не существует!");
    		else if (entity.getIsAccepted()) 
    			throw new AcdpsException("Платеж уже утвержден, сохранение невозможно!");
    	}
    	
    	if (bean.getNum() == null || bean.getNum().equals(""))
    		throw new AcdpsException("Номер документа не указан!");
    	
    	if (bean.getAuthorId() == null)
    		throw new AcdpsException("Автор документа не указан!");
    	
    	if (bean.getCreatedDate() == null)
    		throw new AcdpsException("Дата документа не указана!");
    	
    	if (bean.getCfoId() == null)
    		throw new AcdpsException("ЦФО платежа не указано!");
    	
    	if (bean.getSectionId() == null)
    		throw new AcdpsException("Статья платежа не указана!");
    	
    	if (bean.getCurrencyId() == null)
    		throw new AcdpsException("Валюта платежа не указана!");
    	
    	if (bean.getPayDate() == null)
    		throw new AcdpsException("Дата платежа не указана!");
    	
    	if (bean.getAmount() == null)
    		throw new AcdpsException("Сумма платежа не указана");
    	
    	Employee employee = employeeDao.findById(bean.getAuthorId());
    	if (employee == null)
    		throw new AcdpsException("Сотрудника с таким Id не существует!");
    	
    	Section section = sectionDao.findById(bean.getSectionId());
    	if (section == null)
    		throw new AcdpsException("Статьи с таким Id не существует!");
    	
    	CFO cfo = cfoDao.findById(bean.getCfoId());
    	if (cfo == null)
    		throw new AcdpsException("ЦФО с таки Id не существует!");
    	
    	Currency currency = currencyDao.findById(bean.getCurrencyId());
    	if (currency == null)
    		throw new AcdpsException("Валюты с таким Id не существует!");
    	
    	entity.setNum(bean.getNum());
    	entity.setDocDate(bean.getCreatedDate());
    	entity.setEmployee(employee);
    	
    	entity.setCfo(cfo);
    	entity.setSection(section);
    	entity.setCurrency(currency);
    	entity.setAmount(bean.getAmount());
    	entity.setPayDate(bean.getPayDate());
    	
    	persist(entity);
    	
    	return entity.getId();
    }

}
