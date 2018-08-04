package com.westchase.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.criterion.Order;
import org.jboss.ejb3.annotation.SecurityDomain;

import com.westchase.persistence.criteria.CitizenSearchCriteria;
import com.westchase.persistence.criteria.OfficerSearchCriteria;
import com.westchase.persistence.criteria.PatrolActivitySearchCriteria;
import com.westchase.persistence.dao.BaseDAO;
import com.westchase.persistence.dao.CitizenDAO;
import com.westchase.persistence.dao.OfficerDAO;
import com.westchase.persistence.dao.PatrolActivityDAO;
import com.westchase.persistence.dao.PatrolActivityDetailCitizenDAO;
import com.westchase.persistence.dao.PatrolActivityDetailDAO;
import com.westchase.persistence.dao.PatrolActivityHotspotDAO;
import com.westchase.persistence.dao.PatrolActivityOfficerDAO;
import com.westchase.persistence.dao.PatrolDetailCategoryDAO;
import com.westchase.persistence.dao.PatrolDetailTypeDAO;
import com.westchase.persistence.dao.PatrolHotspotDAO;
import com.westchase.persistence.dao.PatrolPhoneDAO;
import com.westchase.persistence.dao.PatrolShopDAO;
import com.westchase.persistence.dao.PatrolTypeDAO;
import com.westchase.persistence.dao.PropertyDAO;
import com.westchase.persistence.dto.cms.PropertyDTO;
import com.westchase.persistence.dto.patrol.OfficerListCountListDTO;
import com.westchase.persistence.dto.patrol.PatrolActivityReportDTO;
import com.westchase.persistence.dto.patrol.PatrolDetailTypeDayTimeCountDTO;
import com.westchase.persistence.model.Citizen;
import com.westchase.persistence.model.Officer;
import com.westchase.persistence.model.PatrolActivity;
import com.westchase.persistence.model.PatrolActivityDetail;
import com.westchase.persistence.model.PatrolActivityDetailCitizen;
import com.westchase.persistence.model.PatrolActivityHotspot;
import com.westchase.persistence.model.PatrolActivityOfficer;
import com.westchase.persistence.model.PatrolDetailCategory;
import com.westchase.persistence.model.PatrolDetailType;
import com.westchase.persistence.model.PatrolHotspot;
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
	public List<Officer> listActiveOfficers() {
		final OfficerDAO dao = new OfficerDAO();
		return dao.findAllActiveOrdered();
	}

	private List<Officer> listOfficers(List<Integer> officerIdList) {
		final OfficerDAO dao = new OfficerDAO();
		return dao.findOrdered(officerIdList);
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
	public List<PatrolDetailCategory> listDetailCategories(List<Integer> itemIdList) {
		if (!BaseDAO.hasListValues(itemIdList)) {
			return listDetailCategories(itemIdList);
		}
		final PatrolDetailCategoryDAO dao = new PatrolDetailCategoryDAO();
		return dao.findAll(itemIdList);
	}

	@Override
	public List<PatrolDetailType> listDetailTypes() {
		final PatrolDetailTypeDAO dao = new PatrolDetailTypeDAO();
		return dao.findAll(Order.asc("name"));
	}
	@Override
	public List<PatrolDetailType> listDetailTypes(List<Integer> itemIdList) {
		if (itemIdList != null && !BaseDAO.hasListValues(itemIdList)) {
			return listDetailTypes(itemIdList);
		}
		final PatrolDetailTypeDAO dao = new PatrolDetailTypeDAO();
		return dao.findAll(itemIdList);
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

	@Override
	public List<PatrolHotspot> listPatrolHotspots(boolean eastWest) {
		final PatrolHotspotDAO dao = new PatrolHotspotDAO();
		return dao.listHotspots(eastWest);
	}

	// -- PatrolActivity -- //
	@Override
	public PatrolActivity getActivity(Long patrolActivityId) throws Exception {
		final PatrolActivityDAO dao = new PatrolActivityDAO();
		return dao.findById(patrolActivityId);
	}
	
	@Override
	@PermitAll
	public List<Officer> listActivityOfficers(Long patrolActivityId) {
		final PatrolActivityOfficerDAO dao = new PatrolActivityOfficerDAO();
		return dao.listOfficers(patrolActivityId);
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
	public Long saveOrUpdateActivity(List<Integer> officerIdList, PatrolActivity patrolActivity, List<Integer> hotspotIdListEast, List<Integer> hotspotIdListWest) throws Exception {
		if (patrolActivity != null && CollectionUtils.isNotEmpty(officerIdList)) {
			
			// check consistent times
			//List<PatrolActivity> sameOfficerSameDayList = listOtherByOfficerAndDay(patrolActivity.getId(), patrolActivity.getOfficer().getId(), patrolActivity.getStartDateTime());
			
			patrolActivity.setPatrolOfficerCount(officerIdList.size());
			
			if (patrolActivity.getPatrolPhone() != null && patrolActivity.getPatrolPhone().getId() != null && patrolActivity.getPatrolPhone().getId().intValue() < 0) {
				patrolActivity.setPatrolPhone(null);
			}
			final PatrolActivityDAO dao = new PatrolActivityDAO();
			dao.saveOrUpdate(patrolActivity);
			if (patrolActivity.getId() != null) {
				
				savePatrolActivityOfficerList(patrolActivity.getId(), officerIdList);
				
				saveHotspotIdList(patrolActivity.getId(), false, hotspotIdListEast);
				saveHotspotIdList(patrolActivity.getId(), true, hotspotIdListWest);
				
				return patrolActivity.getId();
			}
		}
		return null;
	}

	private void savePatrolActivityOfficerList(Long patrolActivityId, List<Integer> officerIdList) {
		if (patrolActivityId != null && patrolActivityId.longValue() > 0 && CollectionUtils.isNotEmpty(officerIdList)) {
			PatrolActivityOfficerDAO dao = new PatrolActivityOfficerDAO();
			dao.removeAllForPatrolActivity(patrolActivityId);
		
			for (Integer officerId : officerIdList) {
				PatrolActivityOfficer patrolActivityOfficer = new PatrolActivityOfficer();
				patrolActivityOfficer.setOfficer(new Officer(officerId));
				patrolActivityOfficer.setPatrolActivity(new PatrolActivity(patrolActivityId));
				dao.save(patrolActivityOfficer);
			}
		}
	}

	private boolean saveHotspotIdList(Long patrolActivityId, boolean eastWest, List<Integer> hotspotIdList) {
		PatrolActivityHotspotDAO dao = new PatrolActivityHotspotDAO();
		if (dao.deleteAllForPatrolActivity(patrolActivityId, eastWest)) {
			if (hotspotIdList != null && !hotspotIdList.isEmpty()) {
				for (Integer hotspotId : hotspotIdList) {
					PatrolActivityHotspot pah = new PatrolActivityHotspot();
					pah.setPatrolActivity(new PatrolActivity(patrolActivityId));
					pah.setPatrolHotspot(new PatrolHotspot(hotspotId));
					dao.save(pah);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public void deleteActivity(Long patrolActivityId) {
		final PatrolActivityDAO dao = new PatrolActivityDAO();
		dao.delete(patrolActivityId);
	}
	
	@Override
	public List<Integer> getHotspotIdList(Long patrolActivityId, boolean eastWest) {
		PatrolActivityHotspotDAO dao = new PatrolActivityHotspotDAO();
		return dao.listIdForActivity(patrolActivityId, eastWest);
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

	@Override
	public List<Date> listAvailableDates(Long patrolActivityId) {
		final PatrolActivityDAO dao = new PatrolActivityDAO();
		return dao.listAvailableDatesForDetail(patrolActivityId);
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
	public List<PatrolActivityReportDTO> runScoreReport(List<Integer> officerIdList, Date startDate, Date endDate, List<Integer> patrolTypeIdList) {
		final PatrolActivityDAO dao = new PatrolActivityDAO();
		return dao.runScoreReport(officerIdList, startDate, endDate, patrolTypeIdList);
	}

	@Override
	public OfficerListCountListDTO<PatrolDetailType> runOfficerDetailTypeReport(List<Integer> officerIdList, Date startDate, Date endDate, List<Integer> patrolDetailTypeIdList) {
		OfficerListCountListDTO<PatrolDetailType> dto = 
				new OfficerListCountListDTO<PatrolDetailType>();
		final PatrolActivityDAO dao = new PatrolActivityDAO();
		
		dto.setOfficerList(listOfficers(officerIdList));
		dto.setItemList(listDetailTypes(patrolDetailTypeIdList));
		
		dto.setOfficerCounts(dao.countDetailTypeByOfficer(officerIdList, startDate, endDate, patrolDetailTypeIdList));
		
		return dto;
	}

	@Override
	public OfficerListCountListDTO<PatrolDetailCategory> runOfficerDetailCategoryReport(List<Integer> officerIdList, Date startDate, Date endDate, List<Integer> patrolDetailCategoryIdList) {
		OfficerListCountListDTO<PatrolDetailCategory> dto = 
				new OfficerListCountListDTO<PatrolDetailCategory>();
		final PatrolActivityDAO dao = new PatrolActivityDAO();
		
		dto.setOfficerList(listOfficers(officerIdList));
		dto.setItemList(listDetailCategories(patrolDetailCategoryIdList));
		
		dto.setOfficerCounts(dao.countDetailCategoryByOfficer(officerIdList, startDate, endDate, patrolDetailCategoryIdList));
		
		return dto;
	}

	@Override
	public List<PatrolDetailTypeDayTimeCountDTO> runDetailByDayTimeReport(Date startDate, Date endDate,
			boolean includeProperty, boolean includeDay, boolean includeTime, 
			List<Integer> patrolDetailTypeIdList, List<Integer> propertyIdList, List<Integer> dayIdList) {
		final PatrolActivityDAO dao = new PatrolActivityDAO();
		return dao.countDetailTypeByDayTime(startDate, endDate, includeProperty, includeDay, includeTime, patrolDetailTypeIdList, propertyIdList, dayIdList);
	}

}
