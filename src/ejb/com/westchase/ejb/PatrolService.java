package com.westchase.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.westchase.persistence.criteria.CitizenSearchCriteria;
import com.westchase.persistence.criteria.OfficerSearchCriteria;
import com.westchase.persistence.criteria.PatrolActivitySearchCriteria;
import com.westchase.persistence.dto.cms.PropertyDTO;
import com.westchase.persistence.dto.patrol.OfficerListCountListDTO;
import com.westchase.persistence.dto.patrol.PatrolActivityReportDTO;
import com.westchase.persistence.dto.patrol.PatrolDetailTypeDayTimeCountDTO;
import com.westchase.persistence.model.Citizen;
import com.westchase.persistence.model.Officer;
import com.westchase.persistence.model.PatrolActivity;
import com.westchase.persistence.model.PatrolActivityDetail;
import com.westchase.persistence.model.PatrolDetailCategory;
import com.westchase.persistence.model.PatrolDetailType;
import com.westchase.persistence.model.PatrolHotspot;
import com.westchase.persistence.model.PatrolPhone;
import com.westchase.persistence.model.PatrolShop;
import com.westchase.persistence.model.PatrolType;

@Local
public interface PatrolService {


	List<Officer> listOfficers();
	List<Officer> listActiveOfficers();
	
	List<PatrolType> listPatrolTypes();

	List<PatrolShop> listPatrolShops();

	List<PatrolPhone> listPatrolPhones();

	List<PatrolDetailCategory> listDetailCategories();
	List<PatrolDetailCategory> listDetailCategories(List<Integer> itemIdList);

	List<PatrolDetailType> listDetailTypes();
	List<PatrolDetailType> listDetailTypes(List<Integer> itemIdList);
	
	List<PropertyDTO> listProperties();

	List<Citizen> listCitizens();
	
	List<PatrolHotspot> listPatrolHotspots(boolean eastWest);
	
	// -- PatrolActivity -- //
	PatrolActivity getActivity(Long patrolActivityId) throws Exception;
	
	List<Officer> listActivityOfficers(Long patrolActivityId);
	
	List<PatrolActivity> findAll(PatrolActivitySearchCriteria criteria);
	
	long findAllCount(PatrolActivitySearchCriteria criteria);
	
	List<PatrolActivity> listOtherByOfficerAndDay(PatrolActivity patrolActivity);
	
	Long saveOrUpdateActivity(List<Integer> officerIdList, PatrolActivity patrolActivity, List<Integer> hotspotIdListEast, List<Integer> hotspotIdListWest) throws Exception;

	void deleteActivity(Long patrolActivityId);
	
	List<Integer> getHotspotIdList(Long patrolActivityId, boolean eastWest);
	
	// -- PatrolActivityDetail -- //
	PatrolActivityDetail getActivityDetail(Long patrolActivityDetailId) throws Exception;
	
	List<PatrolActivityDetail> findActivityDetail(Long patrolActivityId);
	
	Long saveOrUpdateActivityDetail(PatrolActivityDetail patrolActivityDetail, List<Long> selectedCitizens) throws Exception;

	List<Citizen> listActivityDetailCitizens(Long patrolActivityDetailId);

	List<Citizen> listNonSelectedCitizens(Long patrolActivityDetailId);

	void deleteActivityDetail(Long patrolActivityDetailId);
	
	List<Date> listAvailableDates(Long patrolActivityId);

	// -- Officer -- //
	Officer getOfficer(Integer officerId);
	
	List<Officer> findAll(OfficerSearchCriteria criteria);
	
	long findAllCount(OfficerSearchCriteria criteria);
	
	Integer saveOrUpdateOfficer(Officer officer) throws Exception;

	// -- Citizen -- //
	Citizen getCitizen(Long citizenId);
	
	List<Citizen> findAll(CitizenSearchCriteria criteria);
	
	long findAllCount(CitizenSearchCriteria criteria);
	
	Long saveOrUpdateCitizen(Citizen citizen) throws Exception;

	// -- Reports -- //
	List<PatrolActivityReportDTO> runReport(List<Integer> officerIdList, Date startDate, Date endDate, List<Integer> patrolTypeIdList);
	
	List<PatrolActivityReportDTO> runScoreReport(List<Integer> officerIdList, Date startDate, Date endDate, List<Integer> patrolTypeIdList);
	
	OfficerListCountListDTO<PatrolDetailType> runOfficerDetailTypeReport(List<Integer> officerIdList, Date startDate, Date endDate, List<Integer> patrolDetailTypeIdList);

	OfficerListCountListDTO<PatrolDetailCategory> runOfficerDetailCategoryReport(List<Integer> officerIdList, Date startDate, Date endDate, List<Integer> patrolDetailCategoryIdList);

	List<PatrolDetailTypeDayTimeCountDTO> runDetailByDayTimeReport(Date startDate, Date endDate,
			boolean includeProperty, boolean includeDay, boolean includeTime, List<Integer> patrolDetailTypeIdList, List<Integer> propertyIdList, List<Integer> dayIdList);

	
}
