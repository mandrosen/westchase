package com.westchase.ejb;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.jboss.ejb3.annotation.SecurityDomain;

import com.westchase.persistence.dao.AuditCompanyDAO;
import com.westchase.persistence.dao.AuditDAO;
import com.westchase.persistence.dao.AuditPhoneBookDAO;
import com.westchase.persistence.dao.AuditPropertyDAO;
import com.westchase.persistence.model.Audit;
import com.westchase.persistence.model.AuditCompany;
import com.westchase.persistence.model.AuditPhoneBook;
import com.westchase.persistence.model.AuditProperty;
import com.westchase.persistence.model.Citizen;
import com.westchase.persistence.model.CmuLease;
import com.westchase.persistence.model.CmuQuarter;
import com.westchase.persistence.model.Company;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.Message;
import com.westchase.persistence.model.Officer;
import com.westchase.persistence.model.PatrolActivity;
import com.westchase.persistence.model.PatrolActivityDetail;
import com.westchase.persistence.model.PhoneBook;
import com.westchase.persistence.model.Property;
import com.westchase.persistence.model.PropertyHcad;
import com.westchase.persistence.model.Todo;
import com.westchase.persistence.model.Wcuser;

/**
 * @author marc
 *
 */
@Stateless
@SecurityDomain("WestchaseRealm")
@Local(AuditService.class)
public class AuditServiceBean implements AuditService {
	private final static String DELETED = "deleted";
	
	@Override
	public void save(int employeeId, String object, Integer objectId, String desc) {
		if (objectId == null) return;
		Audit audit = new Audit();
		audit.setEmployee(new Employee(employeeId));
		audit.setObject(object);
		audit.setObjectId(new Long(objectId));
		audit.setUpdateDate(new Date());
		audit.setUpdateDescription(desc);
		AuditDAO dao = new AuditDAO();
		dao.save(audit);
	}
	@Override
	public void save(int employeeId, String object, Long objectId, String desc) {
		if (objectId == null) return;
		Audit audit = new Audit();
		audit.setEmployee(new Employee(employeeId));
		audit.setObject(object);
		audit.setObjectId(objectId);
		audit.setUpdateDate(new Date());
		audit.setUpdateDescription(desc);
		AuditDAO dao = new AuditDAO();
		dao.save(audit);
	}

	@Override
	public void save(int employeeId, Todo todo) {
		StringBuffer desc = new StringBuffer(ToStringBuilder.reflectionToString(todo));
		save(employeeId, todo.getClass().getName(), todo.getId(), desc.toString());
	}

	@Override
	public void save(int employeeId, Employee employee, Wcuser user) {
		StringBuffer desc = new StringBuffer(ToStringBuilder.reflectionToString(employee));
		desc.append(ToStringBuilder.reflectionToString(user));
		save(employeeId, employee.getClass().getName(), employee.getId(), desc.toString());
	}


	@Override
	public void save(int employeeId, PhoneBook phoneBook, List<String> categoryCodes, Map<Integer, String> properties) {
    	StringBuffer desc = new StringBuffer(ToStringBuilder.reflectionToString(phoneBook));
    	desc.append("categoryCodes=");
    	desc.append(StringUtils.join(categoryCodes.toArray()));
    	desc.append("propertyIds=");
    	
        if (properties != null && properties.size() > 0) {
        	for (Map.Entry<Integer, String> prop : properties.entrySet()) {
        		desc.append(prop.getKey()).append("=").append(prop.getValue()).append(",");
        	}
        }
    	
//    	save(employeeId, phoneBook.getClass().getName(), phoneBook.getId(), desc.toString());
        AuditPhoneBook aud = new AuditPhoneBook();
        aud.setPhoneBook(phoneBook);
        aud.setEmployee(new Employee(employeeId));
        aud.setUpdateDescription(desc.toString());
        aud.setUpdateDate(new Date());
        AuditPhoneBookDAO dao = new AuditPhoneBookDAO();
        dao.save(aud);
	}

	@Override
	public void save(int employeeId, CmuLease cmuLease) {
    	StringBuffer desc = new StringBuffer(ToStringBuilder.reflectionToString(cmuLease));
    	save(employeeId, cmuLease.getClass().getName(), cmuLease.getId(), desc.toString());
	}

	@Override
	public void save(int employeeId, Company company) {
    	StringBuffer desc = new StringBuffer(ToStringBuilder.reflectionToString(company));
//    	save(employeeId, company.getClass().getName(), company.getId(), desc.toString());
        AuditCompany aud = new AuditCompany();
        aud.setCompany(company);
        aud.setEmployee(new Employee(employeeId));
        aud.setUpdateDescription(desc.toString());
        aud.setUpdateDate(new Date());
        AuditCompanyDAO dao = new AuditCompanyDAO();
        dao.save(aud);    	
	}

	@Override
	public void save(int employeeId, Property property) {
    	StringBuffer desc = new StringBuffer(ToStringBuilder.reflectionToString(property));
//    	save(employeeId, property.getClass().getName(), property.getId(), desc.toString());
        AuditProperty aud = new AuditProperty();
        aud.setProperty(property);
        aud.setEmployee(new Employee(employeeId));
        aud.setUpdateDescription(desc.toString());
        aud.setUpdateDate(new Date());
        AuditPropertyDAO dao = new AuditPropertyDAO();
        dao.save(aud); 
	}

	@Override
	public void save(int employeeId, CmuQuarter quarter) {
    	StringBuffer desc = new StringBuffer(ToStringBuilder.reflectionToString(quarter));
    	save(employeeId, quarter.getClass().getName(), quarter.getId(), desc.toString());
	}

	@Override
	public void save(int employeeId, Message message) {
    	StringBuffer desc = new StringBuffer(ToStringBuilder.reflectionToString(message));
    	save(employeeId, message.getClass().getName(), message.getId(), desc.toString());
	}

	@Override
	public void save(int employeeId, PatrolActivity patrolActivity, List<Integer> hotspotIdListEast, List<Integer> hotspotIdListWest) {
    	StringBuffer desc = new StringBuffer(ToStringBuilder.reflectionToString(patrolActivity));
    	desc.append(" hotspotIdListEast: [");
    	desc.append(printListOfInt(hotspotIdListEast));
    	desc.append(" ], hotspotIdListWest: [");
    	desc.append(printListOfInt(hotspotIdListWest));    	
    	desc.append(" ]");
    	save(employeeId, patrolActivity.getClass().getName(), patrolActivity.getId(), desc.toString());
	}

	private String printListOfInt(List<Integer> idList) {
		StringBuilder str = new StringBuilder();
		if (idList != null && !idList.isEmpty()) {
			for (Integer id : idList) {
				if (str.length() > 0) str.append(",");
				str.append(id);
			}
		}
		return str.toString();
	}
	@Override
	public void save(int employeeId, PatrolActivityDetail patrolActivityDetail) {
    	StringBuffer desc = new StringBuffer(ToStringBuilder.reflectionToString(patrolActivityDetail));
    	save(employeeId, patrolActivityDetail.getClass().getName(), patrolActivityDetail.getId(), desc.toString());
	}

	@Override
	public void save(int employeeId, Officer officer) {
    	StringBuffer desc = new StringBuffer(ToStringBuilder.reflectionToString(officer));
    	save(employeeId, officer.getClass().getName(), officer.getId(), desc.toString());
	}

	@Override
	public void save(int employeeId, Citizen citizen) {
    	StringBuffer desc = new StringBuffer(ToStringBuilder.reflectionToString(citizen));
    	save(employeeId, citizen.getClass().getName(), citizen.getId(), desc.toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(int employeeId, Class clazz, Integer id) {
		StringBuffer desc = new StringBuffer("deleted");
    	save(employeeId, clazz.getName(), id, desc.toString());
	}
	
	public void delete(int employeeId, Class clazz, Long id) {
		StringBuffer desc = new StringBuffer("deleted");
    	save(employeeId, clazz.getName(), id, desc.toString());
	}

	@Override
	public void completeTodo(int employeeId, Integer todoId) {
		StringBuffer desc = new StringBuffer("completed");
    	save(employeeId, Todo.class.getName(), todoId, desc.toString());
	}

	@Override
	public void deleteCompany(Integer employeeId, Integer id) {
        AuditCompany aud = new AuditCompany();
        aud.setCompany(new Company(id));
        aud.setEmployee(new Employee(employeeId));
        aud.setUpdateDescription(DELETED);
        aud.setUpdateDate(new Date());
        AuditCompanyDAO dao = new AuditCompanyDAO();
        dao.save(aud);    	
	}

	@Override
	public void deletePhoneBook(Integer employeeId, Integer id) {
        AuditPhoneBook aud = new AuditPhoneBook();
        aud.setPhoneBook(new PhoneBook(id));
        aud.setEmployee(new Employee(employeeId));
        aud.setUpdateDescription(DELETED);
        aud.setUpdateDate(new Date());
        AuditPhoneBookDAO dao = new AuditPhoneBookDAO();
        dao.save(aud);    	
	}
	@Override
	public void deletePatrolActivity(Integer employeeId, Long id) {
		delete(employeeId, PatrolActivity.class, id);
	}
	@Override
	public void deletePatrolActivityDetail(Integer employeeId, Long id) {
		delete(employeeId, PatrolActivityDetail.class, id);
	}
	@Override
	public void save(int employeeId, PropertyHcad propertyHcad) {
    	StringBuffer desc = new StringBuffer(ToStringBuilder.reflectionToString(propertyHcad));
    	save(employeeId, propertyHcad.getClass().getName(), propertyHcad.getId(), desc.toString());
	}
	@Override
	public void deletePropertyHcad(Integer employeeId, Integer id) {
		delete(employeeId, PropertyHcad.class, id);
		
	}

}
