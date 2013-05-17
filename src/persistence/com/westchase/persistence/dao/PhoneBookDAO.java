package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.westchase.persistence.Constants;
import com.westchase.persistence.criteria.PhoneBookSearchCriteria;
import com.westchase.persistence.dto.cms.PhoneBookCategoryDTO;
import com.westchase.persistence.dto.cms.PhoneBookPropertyDTO;
import com.westchase.persistence.dto.cmu.LeasingAgentDTO;
import com.westchase.persistence.dto.report.ContactDTO;
import com.westchase.persistence.dto.report.PhoneBookCompanyDTO;
import com.westchase.persistence.model.PhoneBook;

/**
 * @author marc
 *
 */
public class PhoneBookDAO extends BaseDAO<PhoneBook>{
	
	private Query getQuery(PhoneBookSearchCriteria criteria, String select, String alias) {
		StringBuffer query = new StringBuffer(select);
		PhoneBook pb = criteria.getSearchObject();
		if (pb != null) {
			if (pb.getId() != null) {
				query.append(" and " + alias + ".id = :id ");
			}
			if (StringUtils.isNotBlank(pb.getTitle())) {
				query.append(" and " + alias + ".title like concat('%','").append(pb.getTitle()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getFirstName())) {
				query.append(" and " + alias + ".firstName like concat('%','").append(pb.getFirstName()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getMiddleInitial())) {
				query.append(" and " + alias + ".middleInitial like concat('%','").append(pb.getMiddleInitial()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getLastName())) {
				query.append(" and " + alias + ".lastName like concat('%','").append(pb.getLastName()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getSalutation())) {
				query.append(" and " + alias + ".salutation like concat('%','").append(pb.getSalutation()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getSuffix())) {
				query.append(" and " + alias + ".suffix like concat('%','").append(pb.getSuffix()).append("','%')");
			}
			if (pb.getCompany() != null && StringUtils.isNotBlank(pb.getCompany().getCompany())) {
				query.append(" and " + alias + ".company.company like concat('%','").append(pb.getCompany().getCompany()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getJobTitle())) {
				query.append(" and " + alias + ".jobTitle like concat('%','").append(pb.getJobTitle()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getDepartment())) {
				query.append(" and " + alias + ".department like concat('%','").append(pb.getDepartment()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getWkPhone())) {
				query.append(" and " + alias + ".wkPhone like concat('%','").append(pb.getWkPhone()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getWkext())) {
				query.append(" and " + alias + ".wkext like concat('%','").append(pb.getWkext()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getFaxPhone())) {
				query.append(" and " + alias + ".faxPhone like concat('%','").append(pb.getFaxPhone()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getMobilePhone())) {
				query.append(" and " + alias + ".mobilePhone like concat('%','").append(pb.getMobilePhone()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getGender())) {
				query.append(" and " + alias + ".gender like concat('%','").append(pb.getGender()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getEmail())) {
				query.append(" and " + alias + ".email like concat('%','").append(pb.getEmail()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getAltEmail())) {
				query.append(" and " + alias + ".altEmail like concat('%','").append(pb.getAltEmail()).append("','%')");
			}

			if (StringUtils.isNotBlank(pb.getHomeAddress())) {
				query.append(" and " + alias + ".homeAddress like concat('%','").append(pb.getHomeAddress()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getHomePhone())) {
				query.append(" and " + alias + ".homePhone like concat('%','").append(pb.getHomePhone()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getHomeFax())) {
				query.append(" and " + alias + ".homeFax like concat('%','").append(pb.getHomeFax()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getOther())) {
				query.append(" and " + alias + ".other like concat('%','").append(pb.getOther()).append("','%')");
			}
			if (StringUtils.isNotBlank(pb.getNotes())) {
				query.append(" and " + alias + ".notes like concat('%','").append(pb.getNotes()).append("','%')");
			}
			if (pb.getInputDate() != null) {
				query.append(" and date_format(" + alias + ".inputDate, '%Y-%m-%d') = :inputdt ");
			}
			if (pb.getLastupdate() != null) {
				query.append(" and date_format(" + alias + ".lastupdate, '%Y-%m-%d') = :lastupdt ");
			}
			if (criteria.getDontEmail() != null) {
				query.append(" and " + alias + ".dontEmail = :dontemail ");
			}
			if (criteria.getWestchaseToday() != null) {
				query.append(" and " + alias + ".westchaseToday = :westchasetoday ");
			}
			if (criteria.getInvestor() != null) {
				query.append(" and " + alias + ".investor = :investor ");
			}
		}
		if (criteria.isOrphanedOnly()) {
			query.append(" and " + alias + ".company is null  ");
		}
		if (StringUtils.isNotBlank(criteria.getOrderCol())) {
			query.append(" order by ").append(alias).append(".").append(criteria.getOrderCol()).append(" ").append(criteria.getOrderDir());
		}
		Query q = getSession().createQuery(query.toString());
		if (pb != null) {
			if (pb.getId() != null) {
				q.setParameter("id", pb.getId());
			}
			if (pb.getInputDate() != null) {
				String datestr = DATE_FORMAT.format(pb.getInputDate());
				q.setParameter("inputdt", datestr);
			}
			if (pb.getLastupdate() != null) {
				String datestr = DATE_FORMAT.format(pb.getLastupdate());
				q.setParameter("lastupdt", datestr);
			}
			if (criteria.getDontEmail() != null) {
				q.setParameter("dontemail", criteria.getDontEmail());
			}
			if (criteria.getWestchaseToday() != null) {
				q.setParameter("westchasetoday", criteria.getWestchaseToday());
			}
			if (criteria.getInvestor() != null) {
				q.setParameter("investor", criteria.getInvestor());
			}
		}		
		return q;
	}


	public long findAllCount(PhoneBookSearchCriteria criteria) {
		long count = 0;
		try {
			Long l = null;
			Integer empId = criteria.getEmployeeId();
			if (empId != null && empId.intValue() > 0) {
				Query q = getQuery(criteria, "select count(distinct ap.phoneBook) from AuditPhoneBook ap where ap.employee.id = :empId ", "ap.phoneBook");
	
				l = (Long) q.setParameter("empId", empId).uniqueResult();
			} else {
				Query q = getQuery(criteria, "select count(p) from PhoneBook p where 1=1 ", "p");
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
	
	public List<PhoneBook> findAll(PhoneBookSearchCriteria criteria) {
		List<PhoneBook> phonebooks = new ArrayList<PhoneBook>();
		try {
			Integer empId = criteria.getEmployeeId();
			if (empId != null && empId.intValue() > 0) {
				Query q = getQuery(criteria, "select distinct ap.phoneBook from AuditPhoneBook ap where ap.employee.id = :empId ", "ap.phoneBook");
				phonebooks = prepareQuery(q, criteria).setParameter("empId", empId).list();		
			} else {
				Query q = getQuery(criteria, "select p from PhoneBook p where 1=1 ", "p");
				phonebooks = prepareQuery(q, criteria).list();		
			}
		} catch (Exception e) {
			log.error("", e);
		}	
		return phonebooks;
	}

//	public List<PhoneBook> findAll(List<String> categoryCodes, boolean westchase, Integer propertyId) {
//		List<PhoneBook> phonebooks = new ArrayList<PhoneBook>();
//		String query = "select p from PhoneBook p";
//		try {
//			phonebooks = getSession().createQuery(query).list();
//		} catch (Exception e) {
//			log.error("", e);
//		}
//		return phonebooks;
//	}
	

//	public List<CompanyPhoneBookDTO> findByCategoryCodes(List<String> categoryCodes) {
//		List<CompanyPhoneBookDTO> results = new ArrayList<CompanyPhoneBookDTO>();
//		StringBuffer query = new StringBuffer("select new com.westchase.persistence.dto.CompanyPhoneBookDTO(p.title, p.firstName, p.lastName, p.jobTitle, p.company.company, p.company.stNumber, p.company.stAddress, p.company.roomNo, p.company.city, p.company.state, p.company.zipCode, p.email, p.wkPhone, p.faxPhone, p.company.mapNo, pbc.category.categoryCode) from PhoneBook p left join p.phoneBookCategories pbc");
//		if (categoryCodes != null && !categoryCodes.isEmpty()) {
//			for (int i = 0; i < categoryCodes.size(); i++) {
//				if (i == 0) {
//					query.append("where ( ");
//				} else {
//					query.append("or ");
//				}
//				query.append("p.phoneBookCategories.category.categoryCode = :cat" + i);
//			}
//			query.append(")");
//		}
//		query.append(" order by p.company.mapNo");
//		try {
//			Query q = getSession().createQuery(query.toString());
//			if (categoryCodes != null && !categoryCodes.isEmpty()) {
//				for (int i = 0; i < categoryCodes.size(); i++) {
//					q.setParameter("cat" + i, categoryCodes.get(i));
//				}
//			}
//			results = q.list();
//		} catch (Exception e) {
//			log.error("", e);
//		}
//		return results;		
//	}	

	public List<PhoneBook> getCityOfHoustonEmployees() {
		//SELECT tblCompany.Company, tblPhoneBook.Title, tblPhoneBook.FirstName, tblPhoneBook.[Middle Initial], tblPhoneBook.LastName, tblPhoneBook.Salutation, tblPhoneBook.Suffix, tblPhoneBook.JobTitle, tblCompany.StNumber, tblCompany.StAddress, tblCompany.RoomNo, tblCompany.City, tblCompany.State, tblCompany.ZipCode, tblCompany.WkPhone_Area AS Expr1, tblCompany.WkPhone, tblCompany.FaxPhone_Area AS Expr2, tblCompany.FaxPhone, tblCompany.NoEmployees, tblCompany.Classification, tblCompany.SubClassification, tblCompany.[NAICS (SIC)], tblCompany.HCAD, tblCompany.MapNo, tblCompany.Other, tblCompany.Owner, tblCompany.Vendor, tblCompany.Center, tblCompany.[Square Feet], tblCompany.InputDate, tblCompany.LastUpdate, tblCompany.Website, tblPhoneBook.Department, tblPhoneBook.WkPhone, tblPhoneBook.Wkext, tblPhoneBook.FaxPhone, tblPhoneBook.[Mobile Phone], tblPhoneBook.Email, tblPhoneBook.[Don'tEmail?], tblPhoneBook.HomeAddress, tblPhoneBook.HomePhone, tblPhoneBook.HomeFax, tblPhoneBook.Investor, tblPhoneBook.WestchaseToday, tblPhoneBook.Other, tblPhoneBook.InputDate, tblPhoneBook.LastUpdate, tblPhoneBook.CompanyId, tblPhoneBook.PersonId
        //FROM tblCompany INNER JOIN tblPhoneBook ON tblCompany.CompanyId = tblPhoneBook.CompanyId
        //WHERE (((tblCompany.Company) Like "City of Houston*"));
		List<PhoneBook> phonebooks = new ArrayList<PhoneBook>();
		String query = "select p from PhoneBook p where p.company.company like 'City of Houston%'";
		try {
			phonebooks = getSession().createQuery(query).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return phonebooks;		
	}

	public List<ContactDTO> getContactsByCategoryCode(List<String> categoryCodes, boolean email, boolean westchaseOnly, int employeeCount) {
		//SELECT DISTINCTROW tblPhoneBook.Email, tblPhoneBook.LastName, tblPhoneBook.FirstName, tblPhoneBook.Salutation, tblPhoneBook.Company, tblPhoneBook.PersonId
		//FROM tblPhoneBook INNER JOIN tblPhonebookCategory ON tblPhoneBook.PersonId = tblPhonebookCategory.PersonId
		//WHERE (((tblPhoneBook.Email) Not Like "n/a") AND ((tblPhonebookCategory.CategoryCode)="am" Or (tblPhonebookCategory.CategoryCode)="bm" Or (tblPhonebookCategory.CategoryCode)="hc" Or (tblPhonebookCategory.CategoryCode)="ic" Or (tblPhonebookCategory.CategoryCode)="sc") AND ((tblPhoneBook.[Don'tEmail?])=0))
		//ORDER BY tblPhoneBook.LastName, tblPhoneBook.FirstName;
		List<ContactDTO> contacts = new ArrayList<ContactDTO>();
		StringBuffer query = null;
		String phoneBookAlias = null;
		if (email) {
			query = new StringBuffer("select distinct new com.westchase.persistence.dto.report.ContactDTO(p.email, p.title, p.lastName, p.firstName, p.salutation, p.company.company, p.id, pbc.category.categoryCode) from PhoneBook p left join p.phoneBookCategories pbc ");
			phoneBookAlias = "p";
		} else {
			query = new StringBuffer("select distinct new com.westchase.persistence.dto.report.ContactDTO(pbc.category.categoryCode, p, p.company) from PhoneBook p left join p.phoneBookCategories pbc ");
			phoneBookAlias = "p";
		}
		String conj = "where ";
		if (email) {
			query.append(conj).append(" p.email is not null and p.email != '' and p.email not like 'n/a' and p.dontEmail = 0 ");
			conj = " and ";
		}
		
		if (categoryCodes != null && !categoryCodes.isEmpty()) {
//			for (int i = 0; i < categoryCodes.size(); i++) {
//				if (i == 0) {
//					query.append(conj).append(" ( ");
//				} else {
//					query.append(" or ");
//				}
//				query.append(" pbc.category.categoryCode = :cat" + i);
//			}
//			query.append(" )");
			query.append(conj).append(" pbc.category.categoryCode in (:catlist) ");
			conj = " and ";
		}
		if (westchaseOnly) {
			query.append(conj).append(" substr(p.company.zipCode, 1, 5) in (:wcZipCodeList) ");
			conj = " and ";
		}
		if (employeeCount > 0) {
			query.append(conj).append(" p.company.noEmployees > :employeeCount ");
		}
		if (email) {
			query.append(" group by " + phoneBookAlias + ".email order by " + phoneBookAlias + ".lastName, " + phoneBookAlias + ".firstName");
		} else {
			query.append(" group by " + phoneBookAlias + ".id order by " + phoneBookAlias + ".lastName, " + phoneBookAlias + ".firstName");
		}
		try {
			Query q = getSession().createQuery(query.toString());
			if (categoryCodes != null && !categoryCodes.isEmpty()) {
//				for (int i = 0; i < categoryCodes.size(); i++) {
//					q.setParameter("cat" + i, categoryCodes.get(i));
//				}
//				StringBuffer catlist = new StringBuffer();
//				for (int i = 0; i < categoryCodes.size(); i++) {
//					if (i > 0) catlist.append(",");
//					catlist.append("'").append(categoryCodes.get(i)).append("'");
//				}
////				q.setParameter("catlist", categoryCodes);
//				q.setParameter("catlist", catlist.toString());
				q.setParameterList("catlist", categoryCodes);
			}
			if (westchaseOnly) {
				q.setParameterList("wcZipCodeList", Constants.WESTCHASE_ZIP_CODES);
			}
			if (employeeCount > 0) {
				q.setParameter("employeeCount", new Integer(employeeCount));
			}
			contacts = q.list();
		} catch (Exception e) {
			log.error("", e);
		}
		return contacts;		
	}

	public List<ContactDTO> getWCBusinessContactsByNAICS(List<String> naicsCodes, boolean westchaseOnly) {
		//SELECT DISTINCTROW tblPhoneBook.LastName, tblPhoneBook.FirstName, tblPhoneBook.Title, tblPhoneBook.JobTitle, tblPhoneBook.Company, tblCompany.StNumber, tblCompany.StAddress, tblCompany.RoomNo, tblCompany.ZipCode, tblPhoneBook.Email, tblPhoneBook.WkPhone, tblCategories.CategoryCode, tblCompany.Classification, tblCompany.[NAICS (SIC)]
        //FROM tblCategories INNER JOIN ((tblCompany INNER JOIN tblPhoneBook ON tblCompany.CompanyId = tblPhoneBook.CompanyId) INNER JOIN tblPhonebookCategory ON tblPhoneBook.PersonId = tblPhonebookCategory.PersonId) ON tblCategories.CategoryCode = tblPhonebookCategory.CategoryCode
        //WHERE (((tblCompany.Classification) Like "W*") AND ((tblCompany.[NAICS (SIC)])="521"))
        //ORDER BY tblPhoneBook.Company;
		List<ContactDTO> contacts = new ArrayList<ContactDTO>();
		StringBuffer query = new StringBuffer("select distinct new com.westchase.persistence.dto.report.ContactDTO(pbp.phoneBook.lastName, pbp.phoneBook.firstName, pbp.phoneBook.title, pbp.phoneBook.jobTitle, pbp.phoneBook.company.company, pbp.property.geoNumber, pbp.property.geoAddress, pbp.suiteNumber, pbp.property.geoZipCode, pbp.phoneBook.email, pbp.phoneBook.wkPhone, pbc.category.categoryCode, pbp.phoneBook.company.classification, pbp.phoneBook.company.naics) from PhoneBookProperty pbp left join pbp.phoneBook.phoneBookCategories pbc where pbp.phoneBook.company.classification like 'W%' ");
		
		
		if (naicsCodes != null && !naicsCodes.isEmpty()) {
//			for (int i = 0; i < naicsCodes.size(); i++) {
//				if (i == 0) {
//					query.append(" and ( ");
//				} else {
//					query.append(" or ");
//				}
//				query.append(" pbp.phoneBook.company.naics = :naics" + i);
//			}
//			query.append(" )");
			query.append(" and pbp.phoneBook.company.naics in (:naicsCodes) ");
		}
		if (westchaseOnly) {
			query.append(" and substr(pbp.property.geoZipCode, 1, 5) in (:wcZipCodeList) ");
		}
		
		query.append(" group by pbp.phoneBook.id order by pbp.phoneBook.company.company");
		try {
			Query q = getSession().createQuery(query.toString());
			if (naicsCodes != null && !naicsCodes.isEmpty()) {
//				for (int i = 0; i < naicsCodes.size(); i++) {
//					q.setParameter("naics" + i, naicsCodes.get(i));
//				}
				q.setParameterList("naicsCodes", naicsCodes);
			}
			if (westchaseOnly) {
				q.setParameterList("wcZipCodeList", Constants.WESTCHASE_ZIP_CODES);
			}
			contacts = q.list();
		} catch (Exception e) {
			log.error("", e);
		}
		return contacts;
	}

	public List<PhoneBookCompanyDTO> findByName(String name, boolean westchaseOnly) {
		List<PhoneBookCompanyDTO> phonebooks = new ArrayList<PhoneBookCompanyDTO>();
		StringBuffer query = new StringBuffer("select new com.westchase.persistence.dto.report.PhoneBookCompanyDTO(p, p.company, group_concat(pbc.category.categoryCode)) from PhoneBook p left join p.phoneBookCategories pbc where p.company.company like '%" + name + "%' ");
		if (westchaseOnly) {
			query.append(" and substr(p.company.zipCode, 1, 5) in (:wcZipCodeList) ");
		}
		query.append(" group by p.id ");
		try {
			Query q = getSession().createQuery(query.toString());
			if (westchaseOnly) {
				q.setParameterList("wcZipCodeList", Constants.WESTCHASE_ZIP_CODES);
			}
			phonebooks = q.list();
		} catch (Exception e) {
			log.error("", e);
		}
		return phonebooks;
	}


	public List<ContactDTO> findEmails(List<Integer> phoneBookIds) {
		StringBuffer buf = new StringBuffer();
		List<ContactDTO> contacts = new ArrayList<ContactDTO>();
		StringBuffer query = new StringBuffer("select distinct new com.westchase.persistence.dto.report.ContactDTO(p.id, pbc.category.categoryCode, p.salutation, p.lastName, p.firstName, p.title, p.jobTitle, p.company.company, p.company.stNumber, p.company.stAddress, p.company.roomNo, p.company.city, p.company.state, p.company.zipCode, p.email, 1) from PhoneBook p left join p.phoneBookCategories pbc  where p.id in (:pbids) group by p.email order by p.lastName, p.firstName");
		try {
			contacts = getSession().createQuery(query.toString()).setParameterList("pbids", phoneBookIds).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return contacts;
	}
	
	public List<ContactDTO> findEmails(List<String> categoryCodes, List<String> naicsCodes, Integer block,
			String street, Boolean westchase, Boolean dontEmail) {
		List<ContactDTO> contacts = new ArrayList<ContactDTO>();
		StringBuffer query = new StringBuffer("select distinct new com.westchase.persistence.dto.report.ContactDTO(p.id, pbc.category.categoryCode, p.salutation, p.lastName, p.firstName, p.title, p.jobTitle, p.company.company, p.company.stNumber, p.company.stAddress, p.company.roomNo, p.company.city, p.company.state, p.company.zipCode, p.email, 1) from PhoneBook p left join p.phoneBookCategories pbc  where p.email is not null and p.email not like 'n/a' ");
		if (dontEmail != null) {
			if (dontEmail.booleanValue()) {
				query.append(" and p.dontEmail = 1 ");
			} else {
				query.append(" and p.dontEmail = 0 ");
			}
		}
		if (westchase != null) {
			if (westchase.booleanValue()) {
				query.append(" and p.westchaseToday = 1 ");
			} else {
				query.append(" and p.westchaseToday = 0 ");
			}
		}
		
		if (categoryCodes != null && !categoryCodes.isEmpty()) {
			for (int i = 0; i < categoryCodes.size(); i++) {
				if (i == 0) {
					query.append(" and ( ");
				} else {
					query.append(" or ");
				}
				query.append(" pbc.category.categoryCode = :cat" + i);
			}
			query.append(" )");
//			query.append(conj).append("pbc.category.categoryCode in (:catlist)");
		}
		
		if (naicsCodes != null && !naicsCodes.isEmpty()) {
			for (int i = 0; i < naicsCodes.size(); i++) {
				if (i == 0) {
					query.append(" and ( ");
				} else {
					query.append(" or ");
				}
				query.append(" p.company.naics = :naics" + i);
			}
			query.append(" )");
//			query.append(conj).append("pbc.category.categoryCode in (:catlist)");
		}
		

		if (StringUtils.isNotBlank(street)) {
			query.append(" and p.company.stAddress like concat('%','").append(street).append("','%')");
		}
		if (block != null) {
			query.append(" and cast_as_int(p.company.stNumber) between :blocklow and :blockhigh");
		}
		query.append(" group by p.email order by p.lastName, p.firstName");
		try {
			Query q = getSession().createQuery(query.toString());
			if (categoryCodes != null && !categoryCodes.isEmpty()) {
				for (int i = 0; i < categoryCodes.size(); i++) {
					q.setParameter("cat" + i, categoryCodes.get(i));
				}				
//				StringBuffer catlist = new StringBuffer();
//				for (int i = 0; i < categoryCodes.size(); i++) {
//					if (i > 0) catlist.append(",");
//					catlist.append("'").append(categoryCodes.get(i)).append("'");
//				}
////				q.setParameter("catlist", categoryCodes);
//				q.setParameter("catlist", catlist.toString());
			}
			if (naicsCodes != null && !naicsCodes.isEmpty()) {
				for (int i = 0; i < naicsCodes.size(); i++) {
					q.setParameter("naics" + i, naicsCodes.get(i));
				}
			}
			if (block != null) {
				q.setParameter("blocklow", block);
				q.setParameter("blockhigh", block.intValue() + 100);
			}
			contacts = q.list();
		} catch (Exception e) {
			log.error("", e);
		}
		return contacts;
	}


	public List<PhoneBookCategoryDTO> findByCompany(Integer companyId) {
		List<PhoneBookCategoryDTO> phonebooks = new ArrayList<PhoneBookCategoryDTO>();
		String query = "select new com.westchase.persistence.dto.cms.PhoneBookCategoryDTO(p, group_concat(pbc.category.categoryCode)) from PhoneBook p left join p.phoneBookCategories pbc where p.company.id = :compid group by p.id order by p.lastName, p.firstName";
		try {
			phonebooks = getSession().createQuery(query).setParameter("compid", companyId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return phonebooks;
	}


	public List<String> getDistinctEmails(List<Integer> phoneBookIds) {
		List<String> emails = new ArrayList<String>();
		String query = "select distinct p.email from PhoneBook p where p.id in :pids";
		try {
			emails = getSession().createQuery(query).setParameter("pids", phoneBookIds).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return emails;
	}


	public List<PhoneBookPropertyDTO> findPhoneBookProperties(Integer phoneBookId, Integer companyId) {
		List<PhoneBookPropertyDTO> props = new ArrayList<PhoneBookPropertyDTO>();
		
		// find the selected properties
		String query = "select new com.westchase.persistence.dto.cms.PhoneBookPropertyDTO(pbp.property, pbp.suiteNumber) from PhoneBookProperty pbp where pbp.phoneBook.id = :pbid";
		try {
			props = getSession().createQuery(query).setParameter("pbid", phoneBookId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		
		// find the unselected properties
		// TODO: fix this query!
		String query2 = "select new com.westchase.persistence.dto.cms.PhoneBookPropertyDTO(cp.property) from CompanyMapno cp where cp.company.id = :compid and cp.property.id not in (select pbp.property.id from PhoneBookProperty pbp where pbp.phoneBook.id = :pbid)";
		try {
			List<PhoneBookPropertyDTO> unsels = getSession().createQuery(query2).setParameter("compid", companyId).setParameter("pbid", phoneBookId).list();
			props.addAll(unsels);
		} catch (Exception e) {
			log.error("", e);
		}		
		return props;
	}


	public LeasingAgentDTO findPhoneBookByPropertyAndCategory(Integer propertyId, String categoryCode) {
		LeasingAgentDTO agent = null;
		String query = "select new com.westchase.persistence.dto.cmu.LeasingAgentDTO(concat(pb.firstName, ' ', pb.lastName), cm.company.company, pb.email, pb.wkPhone, pb.faxPhone) from CompanyMapno cm inner join cm.company.phoneBooks pb left join pb.phoneBookCategories pbc where cm.property.id = :propertyId and pbc.category.categoryCode = :categoryCode";
		try {
			agent = (LeasingAgentDTO) getSession().createQuery(query).setParameter("propertyId", propertyId).setParameter("categoryCode", categoryCode).setMaxResults(1).uniqueResult();
		} catch (Exception e) {
			log.error("", e);
		}
		return agent;
	}
	
}
