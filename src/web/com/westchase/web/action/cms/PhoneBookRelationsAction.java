package com.westchase.web.action.cms;

import java.util.List;

import javax.naming.InitialContext;

import com.opensymphony.xwork2.Preparable;
import com.westchase.ejb.PhoneBookRelationService;
import com.westchase.ejb.PhoneBookService;
import com.westchase.persistence.model.PhoneBook;
import com.westchase.persistence.model.PhoneBookRelation;
import com.westchase.persistence.model.PhoneBookRelationType;
import com.westchase.persistence.model.Property;
import com.westchase.web.action.AbstractWestchaseAction;

/**
 * @author marc
 *
 */
public class PhoneBookRelationsAction extends AbstractWestchaseAction implements Preparable {

    
    private PhoneBookRelationService pbrServ;
    private PhoneBookService pbServ;
//    private PropertyService propServ;
    
    private PhoneBook currentPhoneBook;
	private List<PhoneBookRelation> phoneBookRelations;
	private List<PhoneBookRelationType> availablePhoneBookRelationTypes;
	private List<Property> availableProperties;
	
	private Integer phoneBookId;
	private Long relationId;
	
	private Integer propertyId;
	private int relationTypeId;
	
	
	public String execute() {
		return SUCCESS;
	}
	
	@Override
	public void prepare() throws Exception {
		refresh();
		try {
			InitialContext ctx = new InitialContext();
            pbrServ = (PhoneBookRelationService) ctx.lookup("westchase/PhoneBookRelationServiceBean/local");
//            propServ = (PropertyService) ctx.lookup("westchase/PropertyServiceBean/local");
		} catch (Exception e) {
			log.error("", e);
		}
		if (pbrServ != null) {
			availablePhoneBookRelationTypes = pbrServ.listRelationTypes();
			availableProperties = pbrServ.findAvailableProperties(phoneBookId);
		}
//		if (propServ != null) {
//			availableProperties = propServ.findAllWithOrder("buildingName");
//		}
	}
	
	private void refresh() {
		if (phoneBookId != null && phoneBookId.intValue() > 0) {
			try {
				InitialContext ctx = new InitialContext();
	            pbrServ = (PhoneBookRelationService) ctx.lookup("westchase/PhoneBookRelationServiceBean/local");
	            pbServ = (PhoneBookService) ctx.lookup("westchase/PhoneBookServiceBean/local");
			} catch (Exception e) {
				log.error("", e);
			}
			if (pbrServ != null) {
				this.phoneBookRelations = pbrServ.list(phoneBookId);
			}
			if (pbServ != null) {
				this.currentPhoneBook = pbServ.get(phoneBookId);
			}
		}
	}
	
	public String delete() {
		if (relationId != null && relationId.longValue() > 0) {
			try {
				InitialContext ctx = new InitialContext();
	            pbrServ = (PhoneBookRelationService) ctx.lookup("westchase/PhoneBookRelationServiceBean/local");
			} catch (Exception e) {
				log.error("", e);
			}
			if (pbrServ != null) {
				pbrServ.delete(relationId);
			}
		}
		refresh();
		return SUCCESS;
	}
	
	public String saveNew() {
		try {
			InitialContext ctx = new InitialContext();
            pbrServ = (PhoneBookRelationService) ctx.lookup("westchase/PhoneBookRelationServiceBean/local");
		} catch (Exception e) {
			log.error("", e);
		}
		if (pbrServ != null) {
			if (propertyId != null && propertyId.intValue() > 0 && relationTypeId > 0) {
				pbrServ.saveNew(phoneBookId, propertyId, relationTypeId);
			}
		}
		refresh();
		return SUCCESS;
	}

	public List<PhoneBookRelation> getPhoneBookRelations() {
		return phoneBookRelations;
	}

	public void setPhoneBookRelations(List<PhoneBookRelation> phoneBookRelations) {
		this.phoneBookRelations = phoneBookRelations;
	}

	public Integer getPhoneBookId() {
		return phoneBookId;
	}

	public void setPhoneBookId(Integer phoneBookId) {
		this.phoneBookId = phoneBookId;
	}

	public Long getRelationId() {
		return relationId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}

	public List<PhoneBookRelationType> getAvailablePhoneBookRelationTypes() {
		return availablePhoneBookRelationTypes;
	}

	public void setAvailablePhoneBookRelationTypes(List<PhoneBookRelationType> availablePhoneBookRelationTypes) {
		this.availablePhoneBookRelationTypes = availablePhoneBookRelationTypes;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public int getRelationTypeId() {
		return relationTypeId;
	}

	public void setRelationTypeId(int relationTypeId) {
		this.relationTypeId = relationTypeId;
	}

	public List<Property> getAvailableProperties() {
		return availableProperties;
	}

	public void setAvailableProperties(List<Property> availableProperties) {
		this.availableProperties = availableProperties;
	}

	public PhoneBook getCurrentPhoneBook() {
		return currentPhoneBook;
	}

	public void setCurrentPhoneBook(PhoneBook currentPhoneBook) {
		this.currentPhoneBook = currentPhoneBook;
	}
	

}
