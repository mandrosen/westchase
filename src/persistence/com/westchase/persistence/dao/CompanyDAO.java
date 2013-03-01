package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.westchase.persistence.criteria.CompanySearchCriteria;
import com.westchase.persistence.model.Company;

/**
 * @author marc
 *
 */
public class CompanyDAO extends BaseDAO<Company> {
	
	private static final Integer OUTSIDE_DISTRICT = new Integer(-999);
	
	private Query getQuery(CompanySearchCriteria criteria, String select, String alias) {
		StringBuffer query = new StringBuffer(select);
		Company c = criteria.getSearchObject();
		if (c != null) {
			if (c.getId() != null) {
				query.append(" and " + alias + ".id = :id ");
			}
			if (StringUtils.isNotBlank(c.getCenter())) {
				query.append(" and " + alias + ".center like concat('%','").append(c.getCenter()).append("','%')");				
			}
			if (StringUtils.isNotBlank(c.getCity())) {
				query.append(" and " + alias + ".city like concat('%','").append(c.getCity()).append("','%')");				
			}
			if (StringUtils.isNotBlank(c.getState())) {
				query.append(" and " + alias + ".state like concat('%','").append(c.getState()).append("','%')");				
			}
			if (StringUtils.isNotBlank(c.getZipCode())) {
				query.append(" and " + alias + ".zipCode like concat('%','").append(c.getZipCode()).append("','%')");				
			}
			if (StringUtils.isNotBlank(c.getCompany())) {
				query.append(" and " + alias + ".company like concat('%','").append(c.getCompany()).append("','%')");				
			}
			if (StringUtils.isNotBlank(c.getHcad())) {
				query.append(" and " + alias + ".hcad like concat('%','").append(c.getHcad()).append("','%')");				
			}
			if (StringUtils.isNotBlank(c.getMapNo())) {
				query.append(" and " + alias + ".mapNo like concat('%','").append(c.getMapNo()).append("','%')");				
			}
			if (StringUtils.isNotBlank(c.getNaics())) {
				query.append(" and " + alias + ".naics like concat('%','").append(c.getNaics()).append("','%')");				
			}
			if (StringUtils.isNotBlank(c.getStNumber())) {
				query.append(" and " + alias + ".stNumber like concat('%','").append(c.getStNumber()).append("','%')");				
			}
			if (StringUtils.isNotBlank(c.getStAddress())) {
				query.append(" and " + alias + ".stAddress like concat('%','").append(c.getStAddress()).append("','%')");				
			}
			if (StringUtils.isNotBlank(c.getRoomNo())) {
				query.append(" and " + alias + ".roomNo like concat('%','").append(c.getRoomNo()).append("','%')");				
			}
			if (StringUtils.isNotBlank(c.getWkPhone())) {
				query.append(" and " + alias + ".wkPhone like concat('%','").append(c.getWkPhone()).append("','%')");				
			}
			if (StringUtils.isNotBlank(c.getFaxPhone())) {
				query.append(" and " + alias + ".faxPhone like concat('%','").append(c.getFaxPhone()).append("','%')");				
			}
			if (c.getCompanyType() != null && c.getCompanyType().getId() != null) {
				query.append(" and " + alias + ".companyType.id = ").append(c.getCompanyType().getId().intValue());
			}
			if (StringUtils.isNotBlank(c.getLatitude())) {
				query.append(" and " + alias + ".latitude like concat('%','").append(c.getLatitude()).append("','%')");	
			}
			if (StringUtils.isNotBlank(c.getLongitude())) {
				query.append(" and " + alias + ".longitude like concat('%','").append(c.getLongitude()).append("','%')");	
			}
			if (StringUtils.isNotBlank(c.getClassification())) {
				query.append(" and " + alias + ".classification = '").append(c.getClassification()).append("'");				
			}
			if (StringUtils.isNotBlank(c.getSubClassification())) {
				query.append(" and " + alias + ".subClassification like concat('%','").append(c.getSubClassification()).append("','%')");				
			}
			if (StringUtils.isNotBlank(c.getOther())) {
				query.append(" and " + alias + ".other like concat('%','").append(c.getOther()).append("','%')");				
			}
			if (StringUtils.isNotBlank(c.getOwner())) {
				query.append(" and " + alias + ".owner like concat('%','").append(c.getOwner()).append("','%')");				
			}
			if (StringUtils.isNotBlank(c.getWebsite())) {
				query.append(" and " + alias + ".website like concat('%','").append(c.getWebsite()).append("','%')");				
			}
			if (StringUtils.isNotBlank(c.getNotes())) {
				query.append(" and " + alias + ".notes like concat('%','").append(c.getNotes()).append("','%')");				
			}
			if (c.getNoEmployees() != null) {
				query.append(" and " + alias + ".noEmployees = :noemp ");
			}
			if (c.getSquareFeet() != null) {
				query.append(" and " + alias + ".squareFeet = :sqft ");
			}
			if (criteria.getVendor() != null) {
				query.append(" and " + alias + ".vendor = :vendor ");
			}
		}
		if (criteria.isOrphanedOnly()) {
			query.append(" and size(" + alias + ".phoneBooks) = 0  ");
		}
		if (criteria.isOutsideDistrict()) {
			query.append(" and cm.property.id = -999 ");
		}

		int noEmps = -1;
		int minEmps = -1;
		int maxEmps = -1;
		String employeeRange = criteria.getEmployeeRange();
		int ltindex = StringUtils.indexOf(employeeRange, "<");
		int gtindex = StringUtils.indexOf(employeeRange, ">");
		if (ltindex == 0) {
			try {
				maxEmps = Integer.parseInt(StringUtils.substring(employeeRange, 1));
			} catch (Exception e) {
			}
		} else if (gtindex == 0) {
			try {
				minEmps = Integer.parseInt(StringUtils.substring(employeeRange, 1));
			} catch (Exception e) {
			}
		} else if (ltindex > 0 || gtindex > 0) {
			String[] rngSplit = StringUtils.split(employeeRange, "<>");
			if (rngSplit.length == 2) {
				try {
					minEmps = Integer.parseInt(rngSplit[0]);
					maxEmps = Integer.parseInt(rngSplit[1]);
				} catch (Exception e) {
				}
				if (minEmps > maxEmps) {
					int temp = minEmps;
					minEmps = maxEmps;
					maxEmps = temp;
				}
			}
		} else {
			try {
				noEmps = Integer.parseInt(employeeRange);
			} catch (Exception e) {
			}
		}			
		
		if (noEmps > -1) {
			query.append(" and " + alias + ".noEmployees = :noemp ");
		} else if (minEmps > -1) {
			query.append(" and " + alias + ".noEmployees >= :minemp ");
			if (maxEmps > -1) {
				query.append(" and " + alias + ".noEmployees <= :maxemp ");
			}
		} else if (maxEmps > -1) {
			query.append(" and " + alias + ".noEmployees <= :maxemp ");
		}
		

		if (StringUtils.isNotBlank(criteria.getOrderCol())) {
			query.append(" order by " + alias + ".").append(criteria.getOrderCol()).append(" ").append(criteria.getOrderDir());
		}
		Query q = getSession().createQuery(query.toString());
		if (c != null) {
			if (c.getId() != null) {
				q.setParameter("id", c.getId());
			}
			if (c.getNoEmployees() != null) {
				q.setParameter("noemp", c.getNoEmployees());
			}
			if (c.getSquareFeet() != null) {
				q.setParameter("sqft", c.getSquareFeet());
			}
			if (criteria.getVendor() != null) {
				q.setParameter("vendor", criteria.getVendor());
			}
		}
		if (noEmps > -1) {
			q.setParameter("noemp", new Integer(noEmps));
		}
		if (minEmps > -1) {
			q.setParameter("minemp", new Integer(minEmps));
		}
		if (maxEmps > -1) {
			q.setParameter("maxemp", new Integer(maxEmps));
		}

		return q;
	}
	
	public List<Company> findAll(CompanySearchCriteria criteria) {
		List<Company> companies = new ArrayList<Company>();
		try {			
			Integer empId = criteria.getEmployeeId();
			Integer mapNo = criteria.getMapNo();
			if (criteria.isOutsideDistrict()) {
				mapNo = OUTSIDE_DISTRICT;
			}
			if (empId != null && empId.intValue() > 0) {
				Query q = null;
				if (mapNo != null) {
					q = getQuery(criteria, "select distinct cm.company from CompanyMapno cm, AuditCompany ac where cm.company.id = ac.company.id and ac.employee.id = :empId and cm.property.id = :mapno", "cm.company");
					q.setParameter("mapno", mapNo);
				} else {
					q = getQuery(criteria, "select distinct ac.company from AuditCompany ac where ac.employee.id = :empId ", "ac.company");	
				}
				companies = prepareQuery(q, criteria).setParameter("empId", empId).list();	
			} else {
				Query q = null;
				if (mapNo != null) {
					q = getQuery(criteria, "select distinct cm.company from CompanyMapno cm where cm.property.id = :mapno", "cm.company");
					q.setParameter("mapno", mapNo);
				} else {
					q = getQuery(criteria, "select c from Company c where 1=1 ", "c");
				}
				companies = prepareQuery(q, criteria).list();		
			}			
		} catch (Exception e) {
			log.error("", e);
		}
		return companies;
	}
	
	public long findAllCount(CompanySearchCriteria criteria) {
		long count = 0;
		try {
			Long l = null;
			Integer empId = criteria.getEmployeeId();
			if (empId != null && empId.intValue() > 0) {
				Query q = getQuery(criteria, "select count(distinct ac.company) from AuditCompany ac where ac.employee.id = :empId ", "ac.company");
				l = (Long) q.setParameter("empId", empId).uniqueResult();		
			} else {
				Query q = getQuery(criteria, "select count(c) from Company c where 1=1 ", "c");
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

	public Company getByPhoneBook(Integer phoneBookId) {
		Company company = null;
		String query = "select pb.company from PhoneBook pb where pb.id = :pbid";
		try {
			company = (Company) getSession().createQuery(query).setParameter("pbid", phoneBookId).setMaxResults(1).uniqueResult();
		} catch (Exception e) {
			log.error("", e);
		}
		return company;
	}

	public List<Company> getByProperty(Integer propertyId) {
		List<Company> companies = new ArrayList<Company>();
		String query = "select cm.company from CompanyMapno cm where cm.property.id = :propid order by cm.company.company";
		try {
			companies = getSession().createQuery(query).setParameter("propid", propertyId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return companies;
	}

	public List<Company> findAllWithType() {
		List<Company> companies = new ArrayList<Company>();
		String query = "select c from Company c where c.companyType is not null and c.latitude is not null and c.latitude != '' and c.longitude is not null and c.longitude != '' order by c.companyType.name, c.company";
		try {
			companies = getSession().createQuery(query).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return companies;
	}

	public List<Company> findAllWithType(int companyTypeId) {
		List<Company> companies = new ArrayList<Company>();
		String query = "select c from Company c where c.companyType.id = :ctId and c.latitude is not null and c.latitude != '' and c.longitude is not null and c.longitude != '' order by c.company";
		try {
			companies = getSession().createQuery(query).setParameter("ctId", new Integer(companyTypeId)).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return companies;
	}
}
