package com.westchase.ejb;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.westchase.persistence.criteria.PhoneBookSearchCriteria;
import com.westchase.persistence.dao.PhoneBookCategoryDAO;
import com.westchase.persistence.dao.PhoneBookDAO;
import com.westchase.persistence.dao.PhoneBookPropertyDAO;
import com.westchase.persistence.dto.cms.PhoneBookPropertyDTO;
import com.westchase.persistence.model.Category;
import com.westchase.persistence.model.PhoneBook;
import com.westchase.persistence.model.PhoneBookCategory;
import com.westchase.persistence.model.PhoneBookProperty;
import com.westchase.persistence.model.Property;
import com.westchase.utils.FormatUtils;

/**
 * @author marc
 *
 */
@Stateless
@SecurityDomain("WestchaseRealm")
@Local(PhoneBookService.class)
public class PhoneBookServiceBean implements PhoneBookService {
	
//    @EJB
//    private EmployeeInt emp;
	
//	private PersonDAO personDAO;
	
	public PhoneBookServiceBean() {
		super();
	}
	
//	public PersonServiceBean(PersonDAO personDAO) {
//		this.personDAO = personDAO;
//	}

	@Override
	public List<PhoneBook> findAll(PhoneBookSearchCriteria criteria) {
		PhoneBookDAO dao = new PhoneBookDAO();
		return dao.findAll(criteria);
	}

	@Override
	public long findAllCount(PhoneBookSearchCriteria criteria) {
		PhoneBookDAO dao = new PhoneBookDAO();
		return dao.findAllCount(criteria);
	}

	@Override
	public PhoneBook get(Integer personId) {
		PhoneBookDAO dao = new PhoneBookDAO();
		return dao.get(personId);
	}

	@Override
	public void saveOrUpdate(PhoneBook person, List<String> categories, Map<Integer, String> properties) {
		if (person != null) {
			if (person.getId() == null) {
				person.setInputDate(new Date());
			}
			
			// Format phone numbers
			person.setHomePhone(FormatUtils.formatPhoneNumber(person.getHomePhone()));
			person.setHomeFax(FormatUtils.formatPhoneNumber(person.getHomeFax()));
			person.setFaxPhone(FormatUtils.formatPhoneNumber(person.getFaxPhone()));
			person.setWkPhone(FormatUtils.formatPhoneNumber(person.getWkPhone()));
			person.setMobilePhone(FormatUtils.formatPhoneNumber(person.getMobilePhone()));
			person.setDirectPhone(FormatUtils.formatPhoneNumber(person.getDirectPhone()));
			
			PhoneBookDAO dao = new PhoneBookDAO();
			dao.saveOrUpdate(person);
			
			if (person.getId() > 0) {
				
				PhoneBookPropertyDAO pbdao = new PhoneBookPropertyDAO();
				pbdao.deleteAllForPerson(person.getId());
	
				
				PhoneBookCategoryDAO pcdao = new PhoneBookCategoryDAO();
				pcdao.deleteAllForPerson(person.getId());
				
//		        Set<PhoneBookCategory> personCats = new HashSet<PhoneBookCategory>();
		        if (categories != null && categories.size() > 0) {
		        	for (String cat : categories) {
		        		PhoneBookCategory pc = new PhoneBookCategory();
		        		pc.setCategory(new Category(cat));
		        		pc.setPhoneBook(person);
		        		pcdao.save(pc);
		        	}
		        }
		        
		        if (properties != null && properties.size() > 0) {
		        	for (Map.Entry<Integer, String> prop : properties.entrySet()) {
		        		PhoneBookProperty pb = new PhoneBookProperty();
		        		pb.setPhoneBook(person);
		        		pb.setProperty(new Property(prop.getKey()));
		        		pb.setSuiteNumber(prop.getValue());
		        		pbdao.save(pb);
		        	}
		        }
			}
		}
	}

//	@Override
//	public List<PhoneBook> findAll(List<String> categoryCodes, boolean westchase, Integer propertyId) {
//		PhoneBookDAO dao = new PhoneBookDAO();
//		return dao.findAll(categoryCodes, westchase, propertyId);
//	}
	@Override
	public List<PhoneBook> findAll() {
		PhoneBookDAO dao = new PhoneBookDAO();
		return dao.findAll();
	}

	@Override
	public List<PhoneBook> findByCompany(Integer companyId) {
		PhoneBookDAO dao = new PhoneBookDAO();
		return dao.findByCompany(companyId);
	}

	@Override
	public List<PhoneBookPropertyDTO> findPhoneBookProperties(Integer phoneBookId, Integer companyId) {
		PhoneBookDAO dao = new PhoneBookDAO();
		return dao.findPhoneBookProperties(phoneBookId, companyId);
	}

	@Override
	public void delete(Integer id) {
		PhoneBookDAO dao = new PhoneBookDAO();
		dao.delete(id);
	}
	
}
