package com.pstu.acdps.server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.AcdpsException;
import com.pstu.acdps.server.domain.CFO;
import com.pstu.acdps.server.domain.Currency;
import com.pstu.acdps.server.domain.Employee;
import com.pstu.acdps.server.domain.Estimate;
import com.pstu.acdps.server.domain.EstimateSection;
import com.pstu.acdps.server.domain.Section;
import com.pstu.acdps.shared.dto.CfoDto;
import com.pstu.acdps.shared.dto.CurrencyDto;
import com.pstu.acdps.shared.dto.EstimateDto;
import com.pstu.acdps.shared.dto.EstimateSectionDto;
import com.pstu.acdps.shared.exception.AnyServiceException;
import com.pstu.acdps.shared.type.SystemConstants;

@Repository
public class EstimateDao extends JpaDao<Estimate> {

	@Autowired
	protected CfoDao cfoDao;

	@Autowired
	protected CurrencyDao currencyDao;

	@Autowired
	protected EmployeeDao employeeDao;

	@Autowired
	protected SectionDao sectionDao;

	@Override
	public Class<Estimate> getEntityClass() {
		return Estimate.class;
	}

	public Long save(EstimateDto bean) throws AnyServiceException {

		Estimate entity = null;

		Employee employee = null;
		CFO cfo = null;
		Currency currency = null;

		if (bean.getId() == null)
			entity = new Estimate();
		else
			entity = findById(bean.getId());

		if (entity.getIsAccepted())
			throw new AcdpsException("Нельзя изменять утвержденную смету!");

		employee = employeeDao.findById(bean.getAuthorId());

		if (bean.getCfo() != null)
			cfo = cfoDao.findById(bean.getCfo().getId());

		if (bean.getCurrency() != null)
			currency = currencyDao.findById(bean.getCurrency().getId());

		entity.setNum(bean.getNum());
		entity.setDocDate(bean.getCreatedDate());
		entity.setEmployee(employee);

		entity.setPlanYear(bean.getPlanYear());
		entity.setCfo(cfo);
		entity.setCurrency(currency);

		if (bean.getEstimateSection() != null) {
			for (EstimateSection line : entity.getEstimateSection()) {
				remove(line.getId());
			}

			List<EstimateSection> estimateSectionList = new ArrayList<EstimateSection>();

			for (EstimateSectionDto line : bean.getEstimateSection()) {
				EstimateSection estimateSection = new EstimateSection();
				estimateSection.setEstimate(entity);
				estimateSection.setMonth(line.getMonth());

				Section section = null;

				if (line.getSection() != null)
					section = sectionDao.findById(line.getSection().getId());

				estimateSection.setSection(section);
				estimateSection.setValue(line.getValue());

				estimateSectionList.add(estimateSection);
			}

			entity.setEstimateSection(estimateSectionList);
		}

		persist(entity);

		return entity.getId();
	}

	@SuppressWarnings("unchecked")
	public List<EstimateDto> getEstimateList(Date startDate, Date endDate) {

		List<EstimateDto> result = new ArrayList<EstimateDto>();

		Query estimateQuery = em
				.createQuery("select estimate "
						+ "from Estimate estimate "
						+ "where estimate.docDate >= :startDate and estimate.docDate <= :endDate "
						+ "order by estimate.docDate, estimate.num ");
		estimateQuery.setParameter("startDate", startDate);
		estimateQuery.setParameter("endDate", endDate);

		List<Estimate> resultList = estimateQuery.getResultList();

		for (Estimate estimate : resultList) {
			result.add(toDto(estimate, false));
		}

		return result;
	}
	
	public EstimateDto getById(Long estimateId) {
		
		EstimateDto result = null;
				
		Estimate entity = findById(estimateId);
		
		result = toDto(entity, true);
		
		return result;
	}

	public List<EstimateDto> getEstimateList() {

		return getEstimateList(SystemConstants.startDate,
				SystemConstants.endDate);
	}

	public EstimateDto toDto(Estimate estimate, boolean full) {

		if (estimate == null)
			return null;

		Long employeeId = null;
		if (estimate.getEmployee() != null)
			employeeId = estimate.getEmployee().getId();

		CfoDto cfoDto = CfoDao.toDto(estimate.getCfo(), false);
		CurrencyDto currencyDto = CurrencyDao.toDto(estimate.getCurrency());

		EstimateDto estimateDto = new EstimateDto(estimate.getId(),
				estimate.getNum(), employeeId, estimate.getDocDate());

		estimateDto.setPlanYear(estimate.getPlanYear());
		estimateDto.setCfo(cfoDto);
		estimateDto.setCurrency(currencyDto);

		if (full) {
			List<EstimateSectionDto> dtoList = new ArrayList<EstimateSectionDto>();

			for (EstimateSection child : estimate.getEstimateSection()) {
				EstimateSectionDto dto = new EstimateSectionDto(child.getId(),
						sectionDao.toDto(child.getSection()), child.getMonth(),
						child.getValue());

				dtoList.add(dto);
			}

			estimateDto.setEstimateSection(dtoList);
		}

		return estimateDto;
	}
}
