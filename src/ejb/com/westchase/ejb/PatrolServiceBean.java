package com.westchase.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.criterion.Order;
import org.jboss.ejb3.annotation.SecurityDomain;

import com.westchase.persistence.criteria.CitizenSearchCriteria;
import com.westchase.persistence.criteria.OfficerSearchCriteria;
import com.westchase.persistence.criteria.PatrolActivitySearchCriteria;
import com.westchase.persistence.dao.CitizenDAO;
import com.westchase.persistence.dao.OfficerDAO;
import com.westchase.persistence.dao.PatrolActivityDAO;
import com.westchase.persistence.dao.PatrolActivityDetailCitizenDAO;
import com.westchase.persistence.dao.PatrolActivityDetailDAO;
import com.westchase.persistence.dao.PatrolDetailCategoryDAO;
import com.westchase.persistence.dao.PatrolDetailTypeDAO;
import com.westchase.persistence.dao.PatrolPhoneDAO;
import com.westchase.persistence.dao.PatrolShopDAO;
import com.westchase.persistence.dao.PatrolTypeDAO;
import com.westchase.persistence.dao.PropertyDAO;
import com.westchase.persistence.dto.cms.PropertyDTO;
import com.westchase.persistence.dto.patrol.OfficerCountDTO;
import com.westchase.persistence.dto.patrol.OfficerListCountListDTO;
import com.westchase.persistence.dto.patrol.PatrolActivityReportDTO;
import com.westchase.persistence.model.Citizen;
import com.westchase.persistence.model.Officer;
import com.westchase.persistence.model.PatrolActivity;
import com.westchase.persistence.model.PatrolActivityDetail;
import com.westchase.persistence.model.PatrolActivityDetailCitizen;
import com.westchase.persistence.model.PatrolDetailCategory;
import com.westchase.persistence.model.PatrolDetailType;
import com.westchase.persistence.model.PatrolPhone;
import com.westchase.persistence.model.PatrolShop;
import com.westchase.persistence.model.PatrolType;
import com.westchase.utils.FormatUtils;

@Stateless
@SecurityDomain("WestchaseRealm")
@Local(PatrolService.class)
public class PatrolServiceBean implements PatrolService {

	public PatrolServiceBean() {
		super();
	}

	@Override
	public List<Officer> listOfficers() {
		final OfficerDAO dao = new OfficerDAO();
		return dao.findAllOrdered();
	}

	@Override
	public List<PatrolType> listPatrolTypes() {
		final PatrolTypeDAO dao = new PatrolTypeDAO();
		return dao.findAll(Order.asc("orderVal"));
	}

	@Override
	public List<PatrolShop> listPatrolShops() {
		final PatrolShopDAO dao = new PatrolShopDAO();
		return dao.findAll(Order.asc("name"));
	}

	@Override
	public List<PatrolPhone> listPatrolPhones() {
		final PatrolPhoneDAO dao = new PatrolPhoneDAO();
		return dao.findAll(Order.asc("name"));
	}

	@Override
	public List<PatrolDetailCategory> listDetailCategories() {
		final PatrolDetailCategoryDAO dao = new PatrolDetailCategoryDAO();
		return dao.findAll(Order.asc("id"));
	}

	@Override
	public List<PatrolDetailType> listDetailTypes() {
		final PatrolDetailTypeDAO dao = new PatrolDetailTypeDAO();
		return dao.findAll(Order.asc("name"));
	}

	@Override
	public List<PropertyDTO> listProperties() {
		final PropertyDAO dao = new PropertyDAO();
		return dao.listAllForPatrolDetail();
	}

	@Override
	public List<Citizen> listCitizens() {
		final CitizenDAO dao = new CitizenDAO();
		return dao.findAll(Order.asc("firstName"));
	}

	// -- PatrolActivity -- //
	@Override
	public PatrolActivity getActivity(Long patrolActivityId) throws Exception {
		final PatrolActivityDAO dao = new PatrolActivityDAO();
		return dao.findById(patrolActivityId);
	}
	
	@Override
	public List<PatrolActivity> findAll(PatrolActivitySearchCriteria criteria) {
		final PatrolActivityDAO dao = new PatrolActivityDAO();
		return dao.findAll(criteria);
	}
	
	@Override
	public long findAllCount(PatrolActivitySearchCriteria criteria) {
		final PatrolActivityDAO dao = new PatrolActivityDAO();
		return dao.findAllCount(criteria);
	}

	@Override
	public List<PatrolActivity> listOtherByOfficerAndDay(PatrolActivity patrolActivity) {
		if (patrolActivity == null || patrolActivity.getOfficer() == null || patrolActivity.getOfficer().getId() == null || patrolActivity.getStartDateTime() == null) {
			return null;
		}
		
		// truncate time from the date
		Date activityDate = DateUtils.truncate(patrolActivity.getStartDateTime(), Calendar.DATE);
		final PatrolActivityDAO dao = new PatrolActivityDAO();
		
		return dao.listOtherByOfficerAndDay(patrolActivity.getId(), patrolActivity.getOfficer().getId(), activityDate);
	}

	@Override
	public Long saveOrUpdateActivity(PatrolActivity patrolActivity) throws Exception {
		if (patrolActivity != null && patrolActivity.getOfficer() != null) {
			
			// check consistent times
			//List<PatrolActivity> sameOfficerSameDayList = listOtherByOfficerAndDay(patrolActivity.getId(), patrolActivity.getOfficer().getId(), patrolActivity.getStartDateTime());
			
			if (patrolActivity.getPatrolPhone() != null && patrolActivity.getPatrolPhone().getId() != null && patrolActivity.getPatrolPhone().getId().intValue() < 0) {
				patrolActivity.setPatrolPhone(null);
			}
			final PatrolActivityDAO dao = new PatrolActivityDAO();
			dao.saveOrUpdate(patrolActivity);
			if (patrolActivity.getId() != null) {
				return patrolActivity.getId();
			}
		}
		return null;
	}

	@Override
	public void deleteActivity(Long patrolActivityId) {
		final PatrolActivityDAO dao = new PatrolActivityDAO();
		dao.delete(patrolActivityId);
	}

	// -- PatrolActivityDetail -- //
	@Override
	public PatrolActivityDetail getActivityDetail(Long patrolActivityDetailId) throws Exception {
		final PatrolActivityDetailDAO dao = new PatrolActivityDetailDAO();
		return dao.findById(patrolActivityDetailId);
	}

	@Override
	public List<PatrolActivityDetail> findActivityDetail(Long patrolActivityId) {
		PatrolActivityDetailDAO dao = new PatrolActivityDetailDAO();
		return dao.findByActivity(patrolActivityId);
	}

	@Override
	public Long saveOrUpdateActivityDetail(PatrolActivityDetail patrolActivityDetail, List<Long> selectedCitizens) throws Exception {
		if (patrolActivityDetail != null) {
			if (StringUtils.isBlank(patrolActivityDetail.getOfficerRole())) {
				patrolActivityDetail.setOfficerRole(null);
			}
			
			final PatrolActivityDetailDAO dao = new PatrolActivityDetailDAO();
			dao.saveOrUpdate(patrolActivityDetail);
			
			PatrolActivityDetailCitizenDAO padcDao = new PatrolActivityDetailCitizenDAO();
			padcDao.deleteAllForDetail(patrolActivityDetail.getId());
			
	        if (selectedCitizens != null && selectedCitizens.size() > 0) {
	        	for (Long citizenId : selectedCitizens) {
	        		PatrolActivityDetailCitizen p = new PatrolActivityDetailCitizen();
	        		p.setCitizen(new Citizen(citizenId));
	        		p.setPatrolActivityDetail(patrolActivityDetail);
	        		padcDao.save(p);
	        	}
	        }
			
			
			if (patrolActivityDetail.getId() != null) {
				return patrolActivityDetail.getId();
			}
		}
		return null;
	}

	@Override
	public List<Citizen> listActivityDetailCitizens(Long patrolActivityDetailId) {
		final CitizenDAO dao = new CitizenDAO();
		return dao.findByActivityDetail(patrolActivityDetailId);
	}

	@Override
	public List<Citizen> listNonSelectedCitizens(Long patrolActivityDetailId) {
		final CitizenDAO dao = new CitizenDAO();
		return dao.listNonSelected(patrolActivityDetailId);
	}

	@Override
	public void deleteActivityDetail(Long patrolActivityDetailId) {
		final PatrolActivityDetailDAO dao = new PatrolActivityDetailDAO();
		dao.delete(patrolActivityDetailId);
	}

	// -- Officer -- //
	@Override
	public Officer getOfficer(Integer officerId) {
		final OfficerDAO dao = new OfficerDAO();
		final Officer officer = dao.findById(officerId);
		if (officer != null) {
			officer.setCellPhone(FormatUtils.formatPhoneNumber(officer.getCellPhone()));
		}
		return officer;
	}

	@Override
	public List<Officer> findAll(OfficerSearchCriteria criteria) {
		final OfficerDAO dao = new OfficerDAO();
		return dao.findAll(criteria);
	}

	@Override
	public long findAllCount(OfficerSearchCriteria criteria) {
		final OfficerDAO dao = new OfficerDAO();
		return dao.findAllCount(criteria);
	}

	@Override
	public Integer saveOrUpdateOfficer(Officer officer) throws Exception {
		if (officer != null) {
			officer.setCellPhone(FormatUtils.formatPhoneNumber(officer.getCellPhone()));
			final OfficerDAO dao = new OfficerDAO();
			dao.saveOrUpdate(officer);
			if (officer.getId() != null) {
				return officer.getId();
			}
		}
		return null;
	}

	// -- Citizen -- //
	@Override
	public Citizen getCitizen(Long citizenId) {
		final CitizenDAO dao = new CitizenDAO();
		return dao.findById(citizenId);
	}

	@Override
	public List<Citizen> findAll(CitizenSearchCriteria criteria) {
		final CitizenDAO dao = new CitizenDAO();
		return dao.findAll(criteria);
	}

	@Override
	public long findAllCount(CitizenSearchCriteria criteria) {
		final CitizenDAO dao = new CitizenDAO();
		return dao.findAllCount(criteria);
	}

	@Override
	public Long saveOrUpdateCitizen(Citizen citizen) throws Exception {
		if (citizen != null) {
			final CitizenDAO dao = new CitizenDAO();
			dao.saveOrUpdate(citizen);
			if (citizen.getId() != null) {
				return citizen.getId();
			}
		}
		return null;
	}

	// -- Reports -- //
	@Override
	public List<PatrolActivityReportDTO> runReport(List<Integer> officerIdList, Date startDate, Date endDate, List<Integer> patrolTypeIdList) {
		final PatrolActivityDAO dao = new PatrolActivityDAO();
		return dao.runReport(officerIdList, startDate, endDate, patrolTypeIdList);
	}

	@Override
	public OfficerListCountListDTO<PatrolDetailType, OfficerCountDTO<PatrolDetailType>> runOfficerDetailTypeReport(List<Integer> officerIdList, Date startDate, Date endDate) {
		OfficerListCountListDTO<PatrolDetailType, OfficerCountDTO<PatrolDetailType>> dto = 
				new OfficerListCountListDTO<PatrolDetailType, OfficerCountDTO<PatrolDetailType>>();
		final PatrolActivityDAO dao = new PatrolActivityDAO();
		
		dto.setOfficerList(listOfficers());
		dto.setItemList(listDetailTypes());
		
		dto.setOfficerCounts(dao.countDetailTypeByOfficer(officerIdList, startDate, endDate));
		
		return dto;
	}

	@Override
	public OfficerListCountListDTO<PatrolDetailCategory, OfficerCountDTO<PatrolDetailCategory>> runOfficerDetailCategoryReport(List<Integer> officerIdList, Date startDate, Date endDate) {
		OfficerListCountListDTO<PatrolDetailCategory, OfficerCountDTO<PatrolDetailCategory>> dto = 
				new OfficerListCountListDTO<PatrolDetailCategory, OfficerCountDTO<PatrolDetailCategory>>();
		final PatrolActivityDAO dao = new PatrolActivityDAO();
		
		dto.setOfficerList(listOfficers());
		dto.setItemList(listDetailCategories());
		
		dto.setOfficerCounts(dao.countDetailCategoryByOfficer(officerIdList, startDate, endDate));
		
		return dto;
	}

}
