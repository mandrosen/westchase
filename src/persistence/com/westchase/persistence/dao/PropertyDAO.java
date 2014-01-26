package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.westchase.persistence.Constants;
import com.westchase.persistence.criteria.PropertySearchCriteria;
import com.westchase.persistence.dto.cms.CompanyPropertyDTO;
import com.westchase.persistence.dto.cms.PropertyDTO;
import com.westchase.persistence.dto.report.ContactDTO;
import com.westchase.persistence.dto.report.PhoneBookPropertyDTO;
import com.westchase.persistence.model.PhoneBook;
import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
public class PropertyDAO extends BaseDAO<Property> {

	private Query getQuery(PropertySearchCriteria criteria, String select, String alias) {
		StringBuffer query = new StringBuffer(select);
		Property p = criteria.getSearchObject();
		if (p != null) {
			if (p.getId() != null) {
				query.append(" and " + alias + ".id = :id ");
			}
			if (p.getInputDate() != null) {
				query.append(" and date_format(" + alias + ".inputDate, '%Y-%m-%d') = :inputdt ");
			}
			if (p.getLastUpdate() != null) {
				query.append(" and date_format(" + alias + ".lastUpdate, '%Y-%m-%d') = :lastupdt ");
			}
			if (p.getTrspassDate() != null) {
				query.append(" and date_format(" + alias + ".trspassDate, '%Y-%m-%d') = :trspssdt ");
			}
			if (StringUtils.isNotBlank(p.getBuildingName())) {
				query.append(" and " + alias + ".buildingName like concat('%','").append(p.getBuildingName()).append("','%')");				
			}
			if (StringUtils.isNotBlank(p.getGeoNumber())) {
				query.append(" and " + alias + ".geoNumber like concat('%','").append(p.getGeoNumber()).append("','%')");				
			}
			if (StringUtils.isNotBlank(p.getGeoAddress())) {
				query.append(" and " + alias + ".geoAddress like concat('%','").append(p.getGeoAddress()).append("','%')");				
			}
			if (StringUtils.isNotBlank(p.getGeoCity())) {
				query.append(" and " + alias + ".geoCity like concat('%','").append(p.getGeoCity()).append("','%')");				
			}
			if (StringUtils.isNotBlank(p.getGeoState())) {
				query.append(" and " + alias + ".geoState like concat('%','").append(p.getGeoState()).append("','%')");				
			}
			if (StringUtils.isNotBlank(p.getGeoZipCode())) {
				query.append(" and " + alias + ".geoZipCode like concat('%','").append(p.getGeoZipCode()).append("','%')");				
			}
			if (StringUtils.isNotBlank(p.getBusinessType())) {
				query.append(" and " + alias + ".businessType like concat('%','").append(p.getBusinessType()).append("','%')");	
			}
			if (p.getPropertyType() != null && p.getPropertyType().getId() != null) {
				query.append(" and " + alias + ".propertyType.id = ").append(p.getPropertyType().getId().intValue());
			}
			if (StringUtils.isNotBlank(p.getLatitude())) {
				query.append(" and " + alias + ".latitude like concat('%','").append(p.getLatitude()).append("','%')");	
			}
			if (StringUtils.isNotBlank(p.getLongitude())) {
				query.append(" and " + alias + ".longitude like concat('%','").append(p.getLongitude()).append("','%')");	
			}
			if (p.getBuildingSize() != null) {
				query.append(" and " + alias + ".buildingSize = :bsize ");
			}
			if (p.getAvailableForLease() != null) {
				query.append(" and " + alias + ".availableForLease = :avlease ");
			}
			if (p.getOccupiedSqFt() != null) {
				query.append(" and " + alias + ".occupiedSqFt = :occsqft ");
			}
			if (p.getOccupancyRate() != null) {
				query.append(" and " + alias + ".occupancyRate = :occrate ");
			}
			if (p.getLargestVacancy() != null) {
				query.append(" and " + alias + ".largestVacancy = :largvac ");
			}
			if (p.getNoUnits() != null) {
				query.append(" and " + alias + ".noUnits = :nounits ");
			}
			if (p.getVacantUnits() != null) {
				query.append(" and " + alias + ".vacantUnits = :vacunits ");
			}
			if (p.getCommercialSpcFore() != null) {
				query.append(" and " + alias + ".commercialSpcFore = :commspcfor ");
			}
			if (StringUtils.isNotBlank(p.getCommSpcFore1yr())) {
				query.append(" and " + alias + ".commSpcFore1yr like concat('%','").append(p.getCommSpcFore1yr()).append("','%')");	
			}
			if (p.getAcreage() != null) {
				query.append(" and " + alias + ".acreage = :acreage ");
			}
			
			if (StringUtils.isNotBlank(p.getHcad())) {
				query.append(" and ph.hcad = :hcad ");	
			}
			if (StringUtils.isNotBlank(p.getOwner())) {
				query.append(" and " + alias + ".owner like concat('%','").append(p.getOwner()).append("','%')");	
			}
			if (StringUtils.isNotBlank(p.getMetaOwner())) {
				query.append(" and " + alias + ".metaOwner like concat('%','").append(p.getMetaOwner()).append("','%')");	
			}
			if (StringUtils.isNotBlank(p.getCenter())) {
				query.append(" and " + alias + ".center like concat('%','").append(p.getCenter()).append("','%')");	
			}
			if (StringUtils.isNotBlank(p.getYearBuilt())) {
				query.append(" and " + alias + ".yearBuilt like concat('%','").append(p.getYearBuilt()).append("','%')");	
			}
			if (StringUtils.isNotBlank(p.getFacet())) {
				query.append(" and " + alias + ".facet like concat('%','").append(p.getFacet()).append("','%')");	
			}
			if (StringUtils.isNotBlank(p.getFrontage())) {
				query.append(" and " + alias + ".frontage like concat('%','").append(p.getFrontage()).append("','%')");	
			}
			if (StringUtils.isNotBlank(p.getPriceSqFt())) {
				query.append(" and " + alias + ".priceSqFt like concat('%','").append(p.getPriceSqFt()).append("','%')");	
			}
			if (StringUtils.isNotBlank(p.getRestrictions())) {
				query.append(" and " + alias + ".restrictions like concat('%','").append(p.getRestrictions()).append("','%')");	
			}
			if (StringUtils.isNotBlank(p.getNotes())) {
				query.append(" and " + alias + ".notes like concat('%','").append(p.getNotes()).append("','%')");	
			}
			

			if (criteria.getForSale() != null) {
				query.append(" and " + alias + ".forSale = :frsale ");
			}
			if (criteria.getSingleTenant() != null) {
				query.append(" and " + alias + ".singleTenant = :singten ");
			}
			if (criteria.getWillDivide() != null) {
				query.append(" and " + alias + ".willDivide = :wlldiv ");
			}
			if (criteria.getTrpassAfdvt() != null) {
				query.append(" and " + alias + ".trpassAfdvt = :tpassafdvt ");
			}
			if (criteria.getFlagPole() != null) {
				query.append(" and " + alias + ".flagPole = :flgpole ");
			}
		}

		if (StringUtils.isNotBlank(criteria.getOrderCol())) {
			query.append(" order by " + alias + ".").append(criteria.getOrderCol()).append(" ").append(criteria.getOrderDir());
		}
		Query q = getSession().createQuery(query.toString());
		if (criteria.isEmptySearch()) {
			q.setMaxResults(40);
		}
		if (p != null) {
			if (p.getId() != null) {
				q.setParameter("id", p.getId());
			}
			if (p.getInputDate() != null) {
				String datestr = DATE_FORMAT.format(p.getInputDate());
				q.setParameter("inputdt", datestr);
			}
			if (p.getLastUpdate() != null) {
				String datestr = DATE_FORMAT.format(p.getLastUpdate());
				q.setParameter("lastupdt", datestr);
			}
			if (p.getTrspassDate() != null) {
				String datestr = DATE_FORMAT.format(p.getTrspassDate());
				q.setParameter("trspssdt", datestr);
			}
			if (p.getBuildingSize() != null) {
				q.setParameter("bsize", p.getBuildingSize());
			}
			if (p.getAvailableForLease() != null) {
				q.setParameter("avlease", p.getAvailableForLease());
			}
			if (p.getOccupiedSqFt() != null) {
				q.setParameter("occsqft", p.getOccupiedSqFt());
			}
			if (p.getOccupancyRate() != null) {
				q.setParameter("occrate", p.getOccupancyRate());
			}
			if (p.getLargestVacancy() != null) {
				q.setParameter("largvac", p.getLargestVacancy());
			}
			if (p.getNoUnits() != null) {
				q.setParameter("nounits", p.getNoUnits());
			}
			if (p.getVacantUnits() != null) {
				q.setParameter("vacunits", p.getVacantUnits());
			}
			if (p.getCommercialSpcFore() != null) {
				q.setParameter("commspcfor", p.getCommercialSpcFore());
			}
			if (p.getAcreage() != null) {
				q.setParameter("acreage", p.getAcreage());
			}
			if (StringUtils.isNotBlank(p.getHcad())) {
				q.setParameter("hcad", p.getHcad());
			}
		}
		return q;
	}
	
	public List<Property> findAll(PropertySearchCriteria criteria) {
		List<Property> properties = new ArrayList<Property>();		
		
		try {			
			Integer empId = criteria.getEmployeeId();
			if (empId != null && empId.intValue() > 0) {
				Query q = getQuery(criteria, "select distinct ap.property from AuditProperty ap left join fetch ap.property.propertyHcads ph where ap.employee.id = :empId and ap.property.id > -1 and ap.property.deleted = 0 " , "ap.property");
				properties = prepareQuery(q, criteria).setParameter("empId", empId).list();		
			} else {
				Query q = getQuery(criteria, "select p from Property p left join fetch p.propertyHcads ph where p.id > -1 and p.deleted = 0 ", "p");
				properties = prepareQuery(q, criteria).list();		
			}			
		} catch (Exception e) {
			log.error("", e);
		}
		return properties;
	}
	
	public long findAllCount(PropertySearchCriteria criteria) {
		long count = 0;
		try {
			Long l = null;
			Integer empId = criteria.getEmployeeId();
			if (empId != null && empId.intValue() > 0) {
				Query q = getQuery(criteria, "select count(distinct ap.property) from AuditProperty ap left join ap.property.propertyHcads ph where ap.employee.id = :empId and ap.property.id > -1 and ap.property.deleted = 0 ", "ap.property");
				l = (Long) q.setParameter("empId", empId).uniqueResult();		
			} else {
				Query q = getQuery(criteria, "select count(p) from Property p left join p.propertyHcads ph where p.id > -1 and p.deleted = 0 ", "p");
				l = (Long) q.uniqueResult();	
			}		
			if (l != null) {
				count = l.longValue();
			}			
		} catch (Exception e) {
			log.error("", e);
		}
		return count;
	}

	public List<Property> findWithTrespassAgreement(boolean westchaseOnly) {
		List<Property> properties = new ArrayList<Property>();
		String query = "select p from Property p where p.deleted = 0 and p.trspassDate is not null";
		if (westchaseOnly) {
			query += " and p.geoZipCode in (:wcZipCodeList) ";
		}
		try {
			Query q = getSession().createQuery(query);
			if (westchaseOnly) {
				q.setParameterList("wcZipCodeList", Constants.WESTCHASE_ZIP_CODES);
			}
			properties = q.list();
		} catch (Exception e) {
			log.error("", e);
		}
		return properties;
	}

	public List<ContactDTO> getFlagPoleContacts(boolean westchaseOnly) {
		List<ContactDTO> contacts = new ArrayList<ContactDTO>();
		String query = "select distinct new com.westchase.persistence.dto.report.ContactDTO(p.id, p.buildingName, p.geoNumber, p.geoAddress) from Property p where p.flagPole = 1 and p.deleted = 0 ";
		// following doesn't use left joins, so I can't use it
		// adding the subquery after the main query below (this should be fixed)
		//String query = "select distinct new com.westchase.persistence.dto.report.ContactDTO(p.id, p.buildingName, p.geoNumber, p.geoAddress, pbr.phoneBook) from Property p left outer join p.phoneBookRelations pbr left outer join pbr.phoneBook.phoneBookCategories pbc where p.flagPole = 1 and p.deleted = 0 and pbc.category.categoryCode = 'FP'";
		if (westchaseOnly) {
			query += " and p.geoZipCode in (:wcZipCodeList) ";
		}
		query += " order by p.id";
		try {
			Query q = getSession().createQuery(query);
			if (westchaseOnly) {
				q.setParameterList("wcZipCodeList", Constants.WESTCHASE_ZIP_CODES);
			}
			contacts = q.list();
			if (contacts != null && !contacts.isEmpty()) {
				for (ContactDTO contact : contacts) {
					String query2 = "select pbr.phoneBook from PhoneBookRelation pbr left outer join pbr.phoneBook.phoneBookCategories pbc where pbc.category.categoryCode = 'FP' and pbr.property.id = :propertyId";
					PhoneBook pb = (PhoneBook) getSession().createQuery(query2).setParameter("propertyId", contact.getPropertyId()).setMaxResults(1).uniqueResult();
					if (pb != null) {
						contact.setPhoneBook(pb);
					}
				}
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return contacts;
	}

	public List<CompanyPropertyDTO> findByCompany(Integer companyId) {
		List<CompanyPropertyDTO> properties = new ArrayList<CompanyPropertyDTO>();
		if (companyId != null && companyId.intValue() > 0) {
			String query = "select new com.westchase.persistence.dto.cms.CompanyPropertyDTO(cm.id, cm.property, cm.primary) from CompanyMapno cm where cm.company.id = :compid";
			try {
				properties = getSession().createQuery(query).setParameter("compid", companyId).list();
			} catch (Exception e) {
				log.error("", e);
			}
		}
		return properties;
	}

	public List<CompanyPropertyDTO> findByPhoneBook(Integer phoneBookId) {
		List<CompanyPropertyDTO> properties = new ArrayList<CompanyPropertyDTO>();
		if (phoneBookId != null && phoneBookId.intValue() > 0) {
			String query = "select new com.westchase.persistence.dto.cms.CompanyPropertyDTO(cm.id, cm.property, cm.primary) from PhoneBook pb left join pb.company.companyMapnos cm where pb.id = :pbid and cm.property.deleted = 0 order by cm.property.buildingName";
			try {
				properties = getSession().createQuery(query).setParameter("pbid", phoneBookId).list();
			} catch (Exception e) {
				log.error("", e);
			}
		}
		return properties;
	}

	public List<Property> findAllNotCurrentyRelated(Integer phoneBookId) {
		List<Property> properties = new ArrayList<Property>();
		String query = "select p from Property p";
		if (phoneBookId != null && phoneBookId.intValue() > 0) {
			query += " where p.deleted = 0 and p.id not in (select pbr.property.id from PhoneBookRelation pbr where pbr.phoneBook.id = :pbid) ";
		}
		query += " order by p.buildingName ";
		try {
			Query q = getSession().createQuery(query);
			if (phoneBookId != null && phoneBookId.intValue() > 0) {
				q.setParameter("pbid", phoneBookId);
			}
			properties = q.list();
		} catch (Exception e) {
			log.error("", e);
		}
		return properties;
	}

	public List<PhoneBookPropertyDTO> findOfficeBuildingProperties(int squareFootage, double occupancy, boolean westchaseOnly) {
		List<PhoneBookPropertyDTO> properties = new ArrayList<PhoneBookPropertyDTO>();
		String query = "select new com.westchase.persistence.dto.report.PhoneBookPropertyDTO(pbr.phoneBook, pbr.property) from PhoneBookRelation pbr left join pbr.phoneBook.phoneBookCategories pbc  where pbc.category.categoryCode = 'BM' and pbr.property.deleted = 0 ";
		if (squareFootage > 0) {
			query += " and pbr.property.buildingSize >= :sqft ";
		}
		if (occupancy > 0) {
			query += " and pbr.property.occupancyRate >= :occ ";
		}
		if (westchaseOnly) {
			query += " and substr(pbr.property.geoZipCode, 1, 5) in (:wcZipCodeList) ";
		}
		query += " order by pbr.property.id ";
		try {
			Query q = getSession().createQuery(query);
			if (squareFootage > 0) {
				q.setParameter("sqft", new Integer(squareFootage));
			}
			if (occupancy > 0) {
				q.setParameter("occ", new Double(occupancy));
			}
			if (westchaseOnly) {
				q.setParameterList("wcZipCodeList", Constants.WESTCHASE_ZIP_CODES);
			}
			properties = q.list();
		} catch (Exception e) {
			log.error("", e);
		}
		return properties;
	}

	public List<Property> findAllWithType() {
		List<Property> properties = new ArrayList<Property>();
		String query = "select p from Property p where p.propertyType is not null and p.latitude is not null and p.latitude != '' and p.longitude is not null and p.longitude != '' order by p.propertyType.name, p.buildingName";
		try {
			properties = getSession().createQuery(query).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return properties;
	}

	public List<Property> findAllWithType(int propertyTypeId) {
		List<Property> properties = new ArrayList<Property>();
		String query = "select p from Property p where p.propertyType.id = :ptId and p.latitude is not null and p.latitude != '' and p.longitude is not null and p.longitude != '' order by p.buildingName";
		try {
			properties = getSession().createQuery(query).setParameter("ptId", new Integer(propertyTypeId)).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return properties;
	}

	public List<PropertyDTO> listAllForPatrolDetail() {
		List<PropertyDTO> properties = null;
		String query = "select new com.westchase.persistence.dto.cms.PropertyDTO(p.id, p.buildingName, p.hcad, p.geoNumber, p.geoAddress) from Property p order by p.buildingName";
		try {
			properties = getSession().createQuery(query).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return properties;
	}


}
